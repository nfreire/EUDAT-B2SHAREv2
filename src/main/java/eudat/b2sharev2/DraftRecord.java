package eudat.b2sharev2;

import com.fasterxml.jackson.databind.JsonNode;

public class DraftRecord {
	JsonNode createDraftRecordResponse;

	public DraftRecord(JsonNode createDraftRecordResponse) {
		this.createDraftRecordResponse = createDraftRecordResponse;
	}

	public String getFilesLink() {
		JsonNode jsonNode = createDraftRecordResponse.get("links").get("files");
		return jsonNode.asText();
	}
	public String getFilesBucketId() {
		String filesLink = getFilesLink();
		return filesLink.substring(filesLink.lastIndexOf('/')+1);
	}

	public JsonNode getJson() {
		return createDraftRecordResponse;
	}

	public String getRecordId() {
		return createDraftRecordResponse.get("id").asText();
	}
	
	public void setPublishResponse(JsonNode json) {
		createDraftRecordResponse=json;
	}

	public String getRecordPid() {
//		JsonNode jsonNode = createDraftRecordResponse.get("metadata").get("DOI");
		JsonNode jsonNode = createDraftRecordResponse.get("links").get("publication");
		return jsonNode.asText();
	}
}
