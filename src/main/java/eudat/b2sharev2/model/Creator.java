
package eudat.b2sharev2.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "creator_name"
})
public class Creator {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("creator_name")
    private String creatorName;

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("creator_name")
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("creator_name")
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

}
