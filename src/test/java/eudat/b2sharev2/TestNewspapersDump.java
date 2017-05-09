package eudat.b2sharev2;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Document;

import com.fasterxml.jackson.databind.JsonNode;

import eu.europena.research.EdmMetadataConverter;
import eu.europena.research.XmlUtil;
import eu.europena.research.newspapers.DataProvider;
import eu.europena.research.newspapers.DumpRepository;
import eu.europena.research.newspapers.Issue;
import eu.europena.research.newspapers.Title;
import eudat.b2sharev2.model.Record;

public class TestNewspapersDump {
	DumpRepository repository;
	
	B2ShareHttpApiClient b2shareCli;

	
	public TestNewspapersDump(DumpRepository repository, B2ShareHttpApiClient b2shareCli) {
		this.repository = repository;
		this.b2shareCli = b2shareCli;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		B2ShareHttpApiClient b2shareCli=new B2ShareHttpApiClient();
		
		DumpRepository repository=new DumpRepository("src/test/newspapers_dump");
		new TestNewspapersDump(repository, b2shareCli).run();
	}

	private void run() {
		List<DataProvider> listDataProviders = repository.listDataProviders();
		for(DataProvider dp: listDataProviders) {
			System.out.println(dp.getLabel());
			List<Title> listTitles = dp.listTitles();
			for(Title t: listTitles) {
				try {
					System.out.println(t.getLabel());
					upload2B2share(t);
					List<Issue> listIssues = t.listIssues();
					for(Issue i: listIssues) {
						System.out.println(i.getLabel());
						upload2B2share(i);
					}
				} catch (Exception e) {
					System.err.println("title failed: "+t.getLabel());
					e.printStackTrace();
				}
			}
		}
	}

	private void upload2B2share(Issue i) throws Exception {
		//load EDM;
		File edmFile=i.getEdmXml();
		Document edmDom = XmlUtil.parseDomFromFile(edmFile);
		
		//Create B2SHARE record metadata
		Record b2shareRec=EdmMetadataConverter.toB2shareMetadata(edmDom);
		
		//Create B2SHARE draft record
		DraftRecord draft = b2shareCli.createDraftRecord(b2shareRec);

		System.out.println(b2shareCli.getJsonMapper().writeValueAsString(draft.getJson()));
		
		b2shareCli.uploadFile(draft, edmFile, null);
		b2shareCli.uploadFile(draft, i.getEdmJson(), null);
		b2shareCli.uploadFile(draft, i.getFulltextAltoZipArchive(), null);

		//TODO:
		//Convert to CMDI
//		uploadToB2share:
//			cdmi
		
		
		//publish rec
		JsonNode published = b2shareCli.publish(draft);
		System.out.println(b2shareCli.getJsonMapper().writeValueAsString(published));
		
	}

	private static void upload2B2share(Title t) throws Exception {
	}
}
