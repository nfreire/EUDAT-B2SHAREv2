
package eudat.b2sharev2.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "alternate_identifier",
    "alternate_identifier_type"
})
public class AlternateIdentifier {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("alternate_identifier")
    private String alternateIdentifier;
    /**
     * Type
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("alternate_identifier_type")
    private String alternateIdentifierType;

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("alternate_identifier")
    public String getAlternateIdentifier() {
        return alternateIdentifier;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("alternate_identifier")
    public void setAlternateIdentifier(String alternateIdentifier) {
        this.alternateIdentifier = alternateIdentifier;
    }

    /**
     * Type
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("alternate_identifier_type")
    public String getAlternateIdentifierType() {
        return alternateIdentifierType;
    }

    /**
     * Type
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("alternate_identifier_type")
    public void setAlternateIdentifierType(String alternateIdentifierType) {
        this.alternateIdentifierType = alternateIdentifierType;
    }

}
