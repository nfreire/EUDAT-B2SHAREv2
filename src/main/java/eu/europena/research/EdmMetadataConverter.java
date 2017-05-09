package eu.europena.research;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.GregorianCalendar;
import java.util.HashSet;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import eu.europeana.language.nal.EuropeanLanguagesNal;
import eu.europeana.language.nal.NalLanguage;
import eudat.b2sharev2.model.AlternateIdentifier;
import eudat.b2sharev2.model.Contributor;
import eudat.b2sharev2.model.Creator;
import eudat.b2sharev2.model.Description;
import eudat.b2sharev2.model.License;
import eudat.b2sharev2.model.Description.DescriptionType;
import eudat.b2sharev2.model.Record.PublicationState;
import eudat.b2sharev2.model.Record;
import eudat.b2sharev2.model.ResourceType;
import eudat.b2sharev2.model.ResourceType.ResourceTypeGeneral;
import eudat.b2sharev2.model.Title;
import eudat.b2sharev2.model.linguistics.LingResourceType;
import eudat.b2sharev2.model.linguistics.LinguisticsRecord;

public class EdmMetadataConverter {

	public static EuropeanLanguagesNal languagesNal=new EuropeanLanguagesNal();

	public static Record toB2shareMetadata(Document edmDom) {
		
		try {
			LinguisticsRecord record = new LinguisticsRecord();
			record.setOpenAccess(true);

			NodeList titles = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "(//edm:ProvidedCHO/dc:title | //edm:ProvidedCHO/dcterms:alternative)", edmDom);
			for(int i=0; i<titles.getLength() ; i++) {
				String val = XmlUtil.getElementText(((Element)titles.item(i))).trim();
				if(val!=null) {
					Title title = new Title();
					title.setTitle(val);
					record.getTitles().add(title);
					break;
				}
			}
			
			NodeList creators = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:ProvidedCHO/dc:creator", edmDom);
			for(int i=0; i<creators.getLength() ; i++) {
				String resourceIri = XmlUtil.getElementText(((Element)creators.item(i))).trim();
				String creatorVal=null;
				if(resourceIri!=null) {
					NodeList agents = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:Agent[@rdf:about='"+resourceIri+"']/skos:prefLabel", edmDom);
					if(agents.getLength()>0) {
						creatorVal = XmlUtil.getElementText(((Element)agents.item(0))).trim();
					}
				} 
				if(creatorVal==null || StringUtils.isEmpty(creatorVal)) {
					creatorVal = XmlUtil.getElementText(((Element)creators.item(i))).trim();
				}
				if(creatorVal!=null && !StringUtils.isEmpty(creatorVal)) {
					Creator creator = new Creator();
					creator.setCreatorName(creatorVal);
					record.getCreators().add(creator);
				}
			}
			{
				NodeList contributoors = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:ProvidedCHO/dc:contributor", edmDom);
				for(int i=0; i<contributoors.getLength() ; i++) {
					String resourceIri = XmlUtil.getElementText(((Element)contributoors.item(i))).trim();
					String creatorVal=null;
					if(resourceIri!=null) {
						NodeList agents = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:Agent[@rdf:about='"+resourceIri+"']/skos:prefLabel", edmDom);
						if(agents.getLength()>0) {
							creatorVal = XmlUtil.getElementText(((Element)agents.item(0))).trim();
						}
					} 
					if(creatorVal==null || StringUtils.isEmpty(creatorVal)) {
						creatorVal = XmlUtil.getElementText(((Element)contributoors.item(i))).trim();
					}
					if(creatorVal!=null && !StringUtils.isEmpty(creatorVal)) {
						Contributor contrib = new Contributor();
						contrib.setContributorName(creatorVal);
						record.getContributors().add(contrib);
					}
				}
			}
			{
				NodeList contributoors = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:ProvidedCHO/dc:publisher", edmDom);
				for(int i=0; i<contributoors.getLength() ; i++) {
					String resourceIri = XmlUtil.getElementText(((Element)contributoors.item(i))).trim();
					String creatorVal=null;
					if(resourceIri!=null) {
						NodeList agents = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:Agent[@rdf:about='"+resourceIri+"']/skos:prefLabel", edmDom);
						if(agents.getLength()>0) {
							creatorVal = XmlUtil.getElementText(((Element)agents.item(0))).trim();
						}
					} 
					if(creatorVal==null || StringUtils.isEmpty(creatorVal)) {
						creatorVal = XmlUtil.getElementText(((Element)contributoors.item(i))).trim();
					}
					if(creatorVal!=null && !StringUtils.isEmpty(creatorVal)) {
						record.setPublisher(creatorVal);
					}
				}
			}
			{
				NodeList descriptions = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:ProvidedCHO/dc:description", edmDom);
				for(int i=0; i<descriptions.getLength() ; i++) {
					String desc= XmlUtil.getElementText(((Element)descriptions.item(i))).trim();
					if(desc!=null && !StringUtils.isEmpty(desc)) {
						Description description = new Description();
						description.setDescription(desc);
						description.setDescriptionType(DescriptionType.OTHER);
						record.getDescriptions().add(description);
					}
				}
			}
			{
				NodeList contributoors = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:ProvidedCHO/dc:subject", edmDom);
				for(int i=0; i<contributoors.getLength() ; i++) {
					String resourceIri = XmlUtil.getElementText(((Element)contributoors.item(i))).trim();
					String creatorVal=null;
					if(resourceIri!=null) {
						NodeList agents = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:Agent[@rdf:about='"+resourceIri+"']/skos:prefLabel", edmDom);
						if(agents.getLength()>0) {
							creatorVal = XmlUtil.getElementText(((Element)agents.item(0))).trim();
						} else {
							NodeList concepts = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:Concept[@rdf:about='"+resourceIri+"']/skos:prefLabel", edmDom);
							if(concepts.getLength()>0) {
								creatorVal = XmlUtil.getElementText(((Element)concepts.item(0))).trim();
							} else {
								NodeList places = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:Place[@rdf:about='"+resourceIri+"']/skos:prefLabel", edmDom);
								if(concepts.getLength()>0) {
									creatorVal = XmlUtil.getElementText(((Element)places.item(0))).trim();
								}
							}
						}
					} 
					if(creatorVal==null || StringUtils.isEmpty(creatorVal)) {
						creatorVal = XmlUtil.getElementText(((Element)contributoors.item(i))).trim();
					}
					if(creatorVal!=null && !StringUtils.isEmpty(creatorVal)) {
						record.getKeywords().add(creatorVal);
					}
				}
			}
			{
				NodeList descriptions = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:ProvidedCHO/dcterms:tableOfContents", edmDom);
				for(int i=0; i<descriptions.getLength() ; i++) {
					String desc= XmlUtil.getElementText(((Element)descriptions.item(i))).trim();
					if(desc!=null && !StringUtils.isEmpty(desc)) {
						Description description = new Description();
						description.setDescription(desc);
						description.setDescriptionType(DescriptionType.TABLE_OF_CONTENTS);
						record.getDescriptions().add(description);
					}
				}
			}
			{
				NodeList descriptions = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:ProvidedCHO/dcterms:abstract", edmDom);
				for(int i=0; i<descriptions.getLength() ; i++) {
					String desc= XmlUtil.getElementText(((Element)descriptions.item(i))).trim();
					if(desc!=null && !StringUtils.isEmpty(desc)) {
						Description description = new Description();
						description.setDescription(desc);
						description.setDescriptionType(DescriptionType.ABSTRACT);
						record.getDescriptions().add(description);
					}
				}
			}
			{
				NodeList descriptions = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:ProvidedCHO/dc:identifier", edmDom);
				for(int i=0; i<descriptions.getLength() ; i++) {
					String desc= XmlUtil.getElementText(((Element)descriptions.item(i))).trim();
					if(desc!=null && !StringUtils.isEmpty(desc)) {
						AlternateIdentifier altId = new AlternateIdentifier();
						altId.setAlternateIdentifier(desc);
						altId.setAlternateIdentifierType("");
						record.getAlternateIdentifiers().add(altId);
					}
				}
			}
			{
				NodeList descriptions = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:ProvidedCHO/dcterms:issued", edmDom);
				for(int i=0; i<descriptions.getLength() ; i++) {
					String desc= XmlUtil.getElementText(((Element)descriptions.item(i))).trim();
					if(desc!=null && !StringUtils.isEmpty(desc)) {
						record.setPublicationDate( desc);
					}
				}
			}
			{
				NodeList descriptions = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:ProvidedCHO/edm:rights", edmDom);
				for(int i=0; i<descriptions.getLength() ; i++) {
					String desc= XmlUtil.getElementText(((Element)descriptions.item(i))).trim();
					License license=null;
					if(desc!=null && !StringUtils.isEmpty(desc)) {
						try {
							license=new License();
							license.setLicenseUri(new URI(desc));
							record.setLicense(license);
							break;
						} catch (URISyntaxException e) {
							//invalid URI, do not map
						}
					}
					String resourceIri = XmlUtil.getElementText(((Element)descriptions.item(i))).trim();
					if(!StringUtils.isEmpty(resourceIri)) {
						try {
							license=new License();
							record.setLicense(license);
							URI licUri=new URI(resourceIri);
							license.setLicenseUri(licUri);
						} catch (URISyntaxException e) {
							//invalid URI, do not map
						}
					}
				}
			}
			if(record.getLicense()==null) {
				NodeList descriptions = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:ProvidedCHO/dc:rights", edmDom);
				for(int i=0; i<descriptions.getLength() ; i++) {
					String desc= XmlUtil.getElementText(((Element)descriptions.item(i))).trim();
					License license=null;
					if(desc!=null && !StringUtils.isEmpty(desc)) {
						license=new License();
						license.setLicense(desc);
						record.setLicense(license);
					}
					String resourceIri = XmlUtil.getElementText(((Element)descriptions.item(i))).trim();
					if(!StringUtils.isEmpty(resourceIri)) {
						if(license==null) {
							license=new License();
							record.setLicense(license);
						}
						try {
							URI licUri=new URI(resourceIri);
							license.setLicenseUri(licUri);
						} catch (URISyntaxException e) {
							//invalid URI, do not map
						}
					}
				}
			}
			
			NodeList dcLangs = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:ProvidedCHO/dc:language", edmDom);
			for(int i=0; i<dcLangs.getLength() ; i++) {
				String lVal = XmlUtil.getElementText(((Element)dcLangs.item(i))).trim();
				NalLanguage nalLang = languagesNal.lookupIsoCode(lVal);
				if(nalLang!=null) {
					record.getCommunityFields().setLanguageCode("eng");
					break;
				}
			}

			NodeList edmTypes = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:ProvidedCHO/edm:type", edmDom);
			for(int i=0; i<edmTypes.getLength() ; i++) {
				String val = XmlUtil.getElementText(((Element)edmTypes.item(i))).trim();
				LingResourceType lingType=convertEdmType(val);
				if(lingType!=null) {
					record.getCommunityFields().getLingResourceType().add(lingType);
					ResourceType resourceType = new ResourceType();
					resourceType.setResourceTypeGeneral(convertEdmTypeToResourceTypeGeneral(val));
					record.getResourceTypes().add(resourceType);
				}
			}
			record.getCommunityFields().setProjectName("Europeana Newspapers");
			
			NodeList dcFormats = XPathUtil.queryDom(XmlNsUtil.xpathEdmPrefixMap, "//edm:ProvidedCHO/dc:format", edmDom);
			for(int i=0; i<dcFormats.getLength() ; i++) {
				String val = XmlUtil.getElementText(((Element)dcFormats.item(i))).trim();
				if(val!=null && val.startsWith("[OCR confidence]")) {
					record.getCommunityFields().setQuality(val);
					break;
				}
			}
			
			return record;
		} catch (XPathExpressionException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private static LingResourceType convertEdmType(String edmVal) {
		if(edmVal.equals("TEXT"))
			return LingResourceType.TEXT;
		if(edmVal.equals("IMAGE"))
			return LingResourceType.IMAGE;
		if(edmVal.equals("SOUND"))
			return LingResourceType.AUDIO;
		if(edmVal.equals("3D"))
			return LingResourceType.OTHER;
		if(edmVal.equals("VIDEO"))
			return LingResourceType.VIDEO;
		return null;
	}
	
	private static ResourceType.ResourceTypeGeneral convertEdmTypeToResourceTypeGeneral(String edmVal) {
		if(edmVal.equals("TEXT"))
			return ResourceType.ResourceTypeGeneral.TEXT;
		if(edmVal.equals("IMAGE"))
			return ResourceType.ResourceTypeGeneral.IMAGE;
		if(edmVal.equals("SOUND"))
			return ResourceType.ResourceTypeGeneral.SOUND;
		if(edmVal.equals("3D"))
			return ResourceType.ResourceTypeGeneral.OTHER;
		if(edmVal.equals("VIDEO"))
			return ResourceType.ResourceTypeGeneral.AUDIOVISUAL;
		return null;
	}

	
	
}
