package eudat.b2sharev2;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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
