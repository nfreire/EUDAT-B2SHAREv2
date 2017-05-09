package eudat.b2sharev2;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import eudat.b2sharev2.model.Record;
import javassist.bytecode.ByteArray;

/**
 * A Client to the REST API of the language normalization service
 * 
 * @author Nuno Freire (nfreire@gmail.com)
 * @since 16/05/2016
 */
public class B2ShareHttpApiClient {
	private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(B2ShareHttpApiClient.class);
	
	
    private Client client;
    private ObjectMapper jsonMapper=new ObjectMapper();
 
    public B2ShareHttpApiClient() throws Exception{
    	client=HttpsUtil.initSslTrustingHostVerifierForHttpClient();
    	client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
    	jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
	}
    

	public JsonNode listAllCommunities() throws Exception {
        WebTarget target = client.target(Config.getApiBaseUrl()).path(
                "communities").queryParam("access_token", Config.getAccessToken());
        
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == 200) {
        	String normalizedEdmReport = response.readEntity(String.class);
        	JsonNode report = jsonMapper.readTree(normalizedEdmReport);
            return report;
        } else
            throw handleInvalidResponse(target, "GET", "" , response);
    }
	
	
	
	public JsonNode getCommunitySchema(String communityId) throws Exception {
		WebTarget target = client.target(Config.getApiBaseUrl()).path(
				"communities").path(communityId).path("schemas").path("last").queryParam("access_token", Config.getAccessToken());
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		if (response.getStatus() == 200) {
			String normalizedEdmReport = response.readEntity(String.class);
			JsonNode report = jsonMapper.readTree(normalizedEdmReport);
			return report;
		} else
			throw handleInvalidResponse(target, "GET", "" , response);
	}

	

	public DraftRecord createDraftRecord(Record record) throws Exception {
		WebTarget target = client.target(Config.getApiBaseUrl()).path(
				"records/").queryParam("access_token", Config.getAccessToken());
		Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.json(record));
		if (response.getStatus() == 201) {
			String normalizedEdmReport = response.readEntity(String.class);
			JsonNode report = jsonMapper.readTree(normalizedEdmReport);
			return new DraftRecord(report);
		} else
			throw handleInvalidResponse(target, "POST", "" , response);
	}

	public JsonNode uploadFile(DraftRecord record, File file, String mimeType) throws Exception {
		WebTarget target = client.target(Config.getApiBaseUrl()).path(
				"/files").path(record.getFilesBucketId()).path(file.getName()).queryParam("access_token", Config.getAccessToken());
		FileInputStream fileInputStream=null;
		Response response;
		try {
			fileInputStream = new FileInputStream(file);
			response = target.request(MediaType.APPLICATION_JSON).put(Entity.entity(fileInputStream, mimeType==null ? MediaType.APPLICATION_OCTET_STREAM : mimeType));
		}finally {
			if(fileInputStream!=null)
				fileInputStream.close();
		}
		if (response.getStatus() == 200) {
			String normalizedEdmReport = response.readEntity(String.class);
			JsonNode report = jsonMapper.readTree(normalizedEdmReport);
			return report;
		} else
			throw handleInvalidResponse(target, "PUT", "" , response);
	}
	public JsonNode uploadFile(DraftRecord record, String filename, byte[] content) throws Exception {
		WebTarget target = client.target(Config.getApiBaseUrl()).path(
				"/files").path(record.getFilesBucketId()).path(filename).queryParam("access_token", Config.getAccessToken());
		ByteArrayInputStream fileInputStream=null;
		Response response;
		try {
			fileInputStream = new ByteArrayInputStream(content);
			response = target.request(MediaType.APPLICATION_JSON).put(Entity.entity(fileInputStream, MediaType.APPLICATION_OCTET_STREAM));
		}finally {
			if(fileInputStream!=null)
				fileInputStream.close();
		}
		if (response.getStatus() == 200) {
			String normalizedEdmReport = response.readEntity(String.class);
			JsonNode report = jsonMapper.readTree(normalizedEdmReport);
			return report;
		} else
			throw handleInvalidResponse(target, "PUT", "" , response);
	}

	public JsonNode publish(DraftRecord draft) throws Exception {
		WebTarget target = client.target(Config.getApiBaseUrl()).path(
				"/records/").path(draft.getRecordId()).path("draft").queryParam("access_token", Config.getAccessToken());
		Response response;
		response = target.request(MediaType.APPLICATION_JSON).header("Content-Type", "application/json-patch+json").method("PATCH", Entity.entity("[{\"op\": \"add\", \"path\":\"/publication_state\", \"value\": \"submitted\"}]", "application/json-patch+json"));
		if (response.getStatus() == 200) {
			String normalizedEdmReport = response.readEntity(String.class);
			JsonNode report = jsonMapper.readTree(normalizedEdmReport);
			draft.setPublishResponse(report);
			return report;
		} else
			throw handleInvalidResponse(target, "PATCH", "" , response);
//		curl -X PATCH -H 'Content-Type:application/json-patch+json' -d '[{"op": "add", "path":"/publication_state", "value": "submitted"}]' https://$HOSTNAME/api/records/$RECORD_ID/draft?access_token=$ACCESS_TOKEN
	}
	
	
	public List<Record> searchDrafts() throws Exception {
		WebTarget target = client.target(Config.getApiBaseUrl()).path(
				"records/").queryParam("drafts","").queryParam("access_token", Config.getAccessToken());
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		System.out.println(target.getUri());
		if (response.getStatus() == 200) {
			String normalizedEdmReport = response.readEntity(String.class);
			JsonNode report = jsonMapper.readTree(normalizedEdmReport);
			ArrayList<Record> results=new ArrayList<>();
			JsonNode hits = report.path("hits").path("hits");
			if(hits.isArray()) {
				for (Iterator iterator = hits.iterator(); iterator.hasNext();) {
					JsonNode recordNode = (JsonNode) iterator.next();
					results.add(jsonMapper.treeToValue(recordNode, Record.class));
				}
			} 
			
			return results;
		} else
			throw handleInvalidResponse(target, "POST", "" , response);
	}
	
    private Exception handleInvalidResponse(WebTarget trg, String method, String message,
            Response response) {
        String fullMessage = method + " " + trg.getUri() + "\n " +
                                    (message == null ? "" : message) + "\nHTTPstatus: " +
                                    response.getStatus() + "\n" + response.readEntity(String.class);
        log.debug(fullMessage);
		return new RuntimeException(fullMessage);
    }


	public Client getApiClient() {
		return client;
	}

    public ObjectMapper getJsonMapper() {
		return jsonMapper;
	}




}
