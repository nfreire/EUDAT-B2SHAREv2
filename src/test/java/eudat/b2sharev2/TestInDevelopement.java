package eudat.b2sharev2;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import eudat.b2sharev2.model.CommunitySpecific;
import eudat.b2sharev2.model.Record;
import eudat.b2sharev2.model.ResourceType;
import eudat.b2sharev2.model.Record.PublicationState;
import eudat.b2sharev2.model.linguistics.LingResourceType;
import eudat.b2sharev2.model.linguistics.LinguisticsRecord;
import eudat.b2sharev2.model.Title;

public class TestInDevelopement {

	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		B2ShareHttpApiClient b2shareCli=new B2ShareHttpApiClient();
		
		JsonNode comunities = b2shareCli.listAllCommunities();
		
		System.out.println(comunities);

		JsonNode comunitySchema = b2shareCli.getCommunitySchema(Config.getCommunityId());
		
		Iterator<String> fieldNames = comunitySchema.fieldNames();
		for (Iterator<String> iterator = comunitySchema.fieldNames(); iterator.hasNext();) {
			String fld = iterator.next();
			System.out.println(fld);
		}
		System.out.println(b2shareCli.getJsonMapper().writeValueAsString( comunitySchema.get("json_schema")));

		
		{
			LinguisticsRecord record = new LinguisticsRecord();
			Title title = new Title();
			title.setTitle("Test record n."+new GregorianCalendar().getTimeInMillis());
			record.setTitles(new HashSet<>());
			record.getTitles().add(title);
			record.setOpenAccess(true);
			record.setResourceTypes(new HashSet<>());
			ResourceType resourceType = new ResourceType();
			resourceType.setResourceTypeGeneral(ResourceType.ResourceTypeGeneral.TEXT);
			record.getResourceTypes().add(resourceType);
			record.getCommunityFields().setLanguageCode("eng");
			record.getCommunityFields().getLingResourceType().add(LingResourceType.TEXT);
//			record.getCommunitySpecific().setAdditionalProperty("ling_resource_type", "Text");

			System.out.println(b2shareCli.getJsonMapper().writeValueAsString( record));
			
			DraftRecord response = b2shareCli.createDraftRecord(record);

			System.out.println(b2shareCli.getJsonMapper().writeValueAsString( response.getJson()));
			System.out.println("Files bucket URL: "+response.getFilesLink());
			
			JsonNode fileReport = b2shareCli.uploadFile(response, new File("src/test/newspapers_dump"
					+ "/Staatsbibliothek_zu_Berlin_Preu%C3%9Fischer_Kulturbesitz"
					+ "/Deutsches_Nachrichtenb%C3%BCro/newspaper_title_metadata.edm.xml"), null);
			System.out.println(b2shareCli.getJsonMapper().writeValueAsString(fileReport));
			
			
			JsonNode publish = b2shareCli.publish(response);
			System.out.println(b2shareCli.getJsonMapper().writeValueAsString( publish));
		}
		
		
		
//		{
//			List<Record> searchDrafts = b2shareCli.searchDrafts();
//			System.out.println(b2shareCli.getJsonMapper().writeValueAsString( searchDrafts.get(0) ));
//			
//		}
	}
}
