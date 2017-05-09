package eu.europena.research.newspapers.b2share;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.databind.JsonNode;

import eu.europena.research.EdmCmdiConverter;
import eu.europena.research.EdmMetadataConverter; 
import eu.europena.research.XPathUtil;
import eu.europena.research.XmlNsUtil;
import eu.europena.research.XmlUtil;
import eu.europena.research.newspapers.DataProvider;
import eu.europena.research.newspapers.DumpRepository;
import eu.europena.research.newspapers.Issue;
import eu.europena.research.newspapers.Title;
import eudat.b2sharev2.B2ShareHttpApiClient;
import eudat.b2sharev2.DraftRecord;
import eudat.b2sharev2.model.Record;

public class B2shareUploader {
	private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(B2shareUploader.class); 
	
	DumpRepository repository;
	
	B2ShareHttpApiClient b2shareCli;
	B2shareLoadingObserver observer;
	
	public B2shareUploader(DumpRepository repository, B2ShareHttpApiClient b2shareCli) {
		this.repository = repository;
		this.b2shareCli = b2shareCli;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		B2ShareHttpApiClient b2shareCli=new B2ShareHttpApiClient();
		
		B2shareLoadingObserver observer=new B2shareLoadingObserver();
		
		DumpRepository repository=new DumpRepository("src/test/newspapers_dump");
		B2shareUploader b2shareUploader = new B2shareUploader(repository, b2shareCli);
		b2shareUploader.setObserver(observer);
		b2shareUploader.run();
	}

	private void setObserver(B2shareLoadingObserver observer) {
		this.observer=observer;
	}

	private void run() {
		List<DataProvider> listDataProviders = repository.listDataProviders();
		for(DataProvider dp: listDataProviders) {
			observer.start(dp);
			List<Title> listTitles = dp.listTitles();
			for(Title t: listTitles) {
				observer.start(t);
				DraftRecord titleDraft = null;
				try {
					observer.startDraft(t);
					titleDraft = upload2B2shareAsDraft(t);
					observer.endDraft(t, titleDraft);
					List<String> issuesPids=new ArrayList<>();
					List<Issue> listIssues = t.listIssues();
					for(Issue i: listIssues) {
						try {
							observer.start(i);
							DraftRecord draftOfIssue = upload2B2share(i, titleDraft);
							issuesPids.add(draftOfIssue.getRecordPid());
							observer.end(i, draftOfIssue);
						} catch (Exception e) {
							log.error("Issue failed: "+t.getLabel()+" -"+i.getLabel(), e);
						}
					}
					observer.startPublish(titleDraft, t, issuesPids);
					upload2B2sharePublish(titleDraft, t, issuesPids);
				} catch (Exception e) {
					log.error("title failed: "+t.getLabel(), e);
				}
				observer.end(t, titleDraft);
			}
			observer.end(dp);
		}
	}

	private DraftRecord upload2B2share(Issue i, DraftRecord titleDraft) throws Exception {
		//load EDM;
		File edmFile=i.getEdmXml();
		Document edmDom = XmlUtil.parseDomFromFile(edmFile);
		
		Element choEl=getProvidedChoElement(edmDom, edmFile);

		Element formatEl=edmDom.createElementNS(XmlNsUtil.DCTERMS, "dcterms:isPartOf");
		formatEl.appendChild(edmDom.createTextNode(titleDraft.getRecordPid()));
		choEl.appendChild(formatEl);
		
		//Create B2SHARE record metadata
		Record b2shareRec=EdmMetadataConverter.toB2shareMetadata(edmDom);
		
		//Create B2SHARE draft record
		DraftRecord draft = b2shareCli.createDraftRecord(b2shareRec);

//		System.out.println(b2shareCli.getJsonMapper().writeValueAsString(draft.getJson()));
//		System.out.println(XmlUtil.writeDomToString(edmDom));
		
//		b2shareCli.uploadFile(draft, edmFile);
		b2shareCli.uploadFile(draft, edmFile.getName(), XmlUtil.writeDomToString(edmDom).getBytes("UTF-8"));
//		b2shareCli.uploadFile(draft, i.getEdmJson());
		b2shareCli.uploadFile(draft, i.getFulltextAltoZipArchive(), "application/zip");

		Document cmdiDom = EdmCmdiConverter.toCmdiMetadata(edmDom);
//		System.out.println(XmlUtil.writeDomToString(cmdiDom));
		b2shareCli.uploadFile(draft, i.getCmdiXml().getName(), XmlUtil.writeDomToString(cmdiDom).getBytes("UTF-8"));
		//TODO:
		//Convert to CMDI
//		uploadToB2share:
//			cdmi
		
		//publish rec
		JsonNode published = b2shareCli.publish(draft);
//		System.out.println(b2shareCli.getJsonMapper().writeValueAsString(published));

		return draft;
	}

	private DraftRecord upload2B2shareAsDraft(Title t) throws Exception {
		//load EDM;
		File edmFile=t.getEdmXml();
		Document edmDom = XmlUtil.parseDomFromFile(edmFile);
		
		//Create B2SHARE record metadata
		Record b2shareRec=EdmMetadataConverter.toB2shareMetadata(edmDom);
		if(b2shareRec.getPublicationDate() != null) {
			int spaceIdx=b2shareRec.getPublicationDate().indexOf(' ');
			if(spaceIdx>0)
				b2shareRec.setPublicationDate(b2shareRec.getPublicationDate().substring(0, spaceIdx));
		}
		//Create B2SHARE draft record
		DraftRecord draft = b2shareCli.createDraftRecord(b2shareRec);

//		System.out.println(b2shareCli.getJsonMapper().writeValueAsString(draft.getJson()));
		
//		b2shareCli.uploadFile(draft, edmFile);
//		b2shareCli.uploadFile(draft, i.getEdmJson());
//		b2shareCli.uploadFile(draft, i.getFulltextAltoZipArchive());

		//TODO:
		//Convert to CMDI
//		uploadToB2share:
//			cdmi
		
		
		//publish rec
//		JsonNode published = b2shareCli.publish(draft);
//		System.out.println(b2shareCli.getJsonMapper().writeValueAsString(published));
		
		return draft;
	}
	
	private void upload2B2sharePublish(DraftRecord draft, Title t, List<String> b2sharePartsPids) throws Exception {
		//load EDM;
		File edmFile=t.getEdmXml();
		Document edmDom = XmlUtil.parseDomFromFile(edmFile);
		
		Node choEl = getProvidedChoElement(edmDom, edmFile);
		//add hasPart for all b2sharePartsPids
		for(String partPid: b2sharePartsPids) {
			Element formatEl=edmDom.createElementNS(XmlNsUtil.DCTERMS, "dcterms:hasPart");
			formatEl.appendChild(edmDom.createTextNode(partPid));
			choEl.appendChild(formatEl);
		}

//		System.out.println(b2shareCli.getJsonMapper().writeValueAsString(draft.gestJson()));
//		System.out.println(XmlUtil.writeDomToString(edmDom));
		
		b2shareCli.uploadFile(draft, edmFile.getName(), XmlUtil.writeDomToString(edmDom).getBytes("UTF-8"));
//		b2shareCli.uploadFile(draft, t.getEdmJson());
		b2shareCli.uploadFile(draft, t.getFulltextAltoZipArchive(), "application/zip");

		Document cmdiDom = EdmCmdiConverter.toCmdiMetadata(edmDom);
//		System.out.println(XmlUtil.writeDomToString(cmdiDom));
		b2shareCli.uploadFile(draft, t.getCmdiXml().getName(), XmlUtil.writeDomToString(cmdiDom).getBytes("UTF-8"));
		
		//publish rec
		JsonNode published = b2shareCli.publish(draft);
//		System.out.println(b2shareCli.getJsonMapper().writeValueAsString(published));
		log.debug(b2shareCli.getJsonMapper().writeValueAsString(published));
	}

	private Element getProvidedChoElement(Document edmDom, File edmFile) throws Exception {
		Element choEl = null;
		try {
			NodeList cho = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:ProvidedCHO", edmDom);
			if (cho.getLength()<=0) 
				throw new Exception("Invalid EDM. No CHO:"+edmFile!=null ? edmFile.getPath() : "");
			choEl = (Element)cho.item(0);
		} catch (XPathExpressionException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return choEl;
	}
}
