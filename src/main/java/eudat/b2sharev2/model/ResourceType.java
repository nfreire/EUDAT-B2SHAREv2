
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
    "resource_type",
    "resource_type_general"
})
public class ResourceType {

    /**
     * Description
     * <p>
     * 
     * 
     */
    @JsonProperty("resource_type")
    private String resourceType;
    /**
     * Category
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("resource_type_general")
    private ResourceType.ResourceTypeGeneral resourceTypeGeneral;

    /**
     * Description
     * <p>
     * 
     * 
     */
    @JsonProperty("resource_type")
    public String getResourceType() {
        return resourceType;
    }

    /**
     * Description
     * <p>
     * 
     * 
     */
    @JsonProperty("resource_type")
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * Category
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("resource_type_general")
    public ResourceType.ResourceTypeGeneral getResourceTypeGeneral() {
        return resourceTypeGeneral;
    }

    /**
     * Category
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("resource_type_general")
    public void setResourceTypeGeneral(ResourceType.ResourceTypeGeneral resourceTypeGeneral) {
        this.resourceTypeGeneral = resourceTypeGeneral;
    }

    public enum ResourceTypeGeneral {

        AUDIOVISUAL("Audiovisual"),
        COLLECTION("Collection"),
        DATASET("Dataset"),
        EVENT("Event"),
        IMAGE("Image"),
        INTERACTIVE_RESOURCE("InteractiveResource"),
        MODEL("Model"),
        PHYSICAL_OBJECT("PhysicalObject"),
        SERVICE("Service"),
        SOFTWARE("Software"),
        SOUND("Sound"),
        TEXT("Text"),
        WORKFLOW("Workflow"),
        OTHER("Other");
        private final String value;
        private final static Map<String, ResourceType.ResourceTypeGeneral> CONSTANTS = new HashMap<String, ResourceType.ResourceTypeGeneral>();

        static {
            for (ResourceType.ResourceTypeGeneral c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private ResourceTypeGeneral(String value) {
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
        public static ResourceType.ResourceTypeGeneral fromValue(String value) {
            ResourceType.ResourceTypeGeneral constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
