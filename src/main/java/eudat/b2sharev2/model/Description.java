
package eudat.b2sharev2.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "description",
    "description_type"
})
public class Description {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("description")
    private String description;
    /**
     * Type
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("description_type")
    private Description.DescriptionType descriptionType;

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Type
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("description_type")
    public Description.DescriptionType getDescriptionType() {
        return descriptionType;
    }

    /**
     * Type
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("description_type")
    public void setDescriptionType(Description.DescriptionType descriptionType) {
        this.descriptionType = descriptionType;
    }

    public enum DescriptionType {

        ABSTRACT("Abstract"),
        METHODS("Methods"),
        SERIES_INFORMATION("SeriesInformation"),
        TABLE_OF_CONTENTS("TableOfContents"),
        TECHNICAL_INFO("TechnicalInfo"),
        OTHER("Other");
        private final String value;
        private final static Map<String, Description.DescriptionType> CONSTANTS = new HashMap<String, Description.DescriptionType>();

        static {
            for (Description.DescriptionType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private DescriptionType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static Description.DescriptionType fromValue(String value) {
            Description.DescriptionType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
