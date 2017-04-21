package eudat.b2sharev2;

import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;

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
	}
}
