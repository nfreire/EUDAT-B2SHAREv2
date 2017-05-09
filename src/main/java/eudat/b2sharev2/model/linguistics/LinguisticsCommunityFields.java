
package eudat.b2sharev2.model.linguistics;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


/**
 * CLARIN Metadata
 * <p>
 * This is the blueprint of the metadata block specific for the clarin community
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "language_code",
    "ling_resource_type",
    "project_name",
    "quality",
    "region"
})
public class LinguisticsCommunityFields {

    /**
     * Language Code
     * <p>
     * This element can be used to add an ISO language code from ISO-639-3 to uniquely identify the language a document is written in
     * (Required)
     * 
     */
    @JsonProperty("language_code")
    @JsonPropertyDescription("This element can be used to add an ISO language code from ISO-639-3 to uniquely identify the language a document is written in")
    private String languageCode = "eng";
    /**
     * Resource Type
     * <p>
     * This element allows the depositor to specify the type of the resource (Text, Audio, Video, Time-Series, Photo, etc.)
     * (Required)
     * 
     */
    @JsonProperty("ling_resource_type")
    @JsonDeserialize(as = java.util.LinkedHashSet.class)
    @JsonPropertyDescription("This element allows the depositor to specify the type of the resource (Text, Audio, Video, Time-Series, Photo, etc.)")
    private Set<LingResourceType> lingResourceType = null;
    /**
     * Project Name
     * <p>
     * This element allows the depositor to specify the projects which were at the source of the creation of the resource
     * 
     */
    @JsonProperty("project_name")
    @JsonPropertyDescription("This element allows the depositor to specify the projects which were at the source of the creation of the resource")
    private String projectName;
    /**
     * Quality
     * <p>
     * This element allows depositors to indicate the quality of the resource allowing potential users to immediately see whether the resource is of use for them.
     * 
     */
    @JsonProperty("quality")
    @JsonPropertyDescription("This element allows depositors to indicate the quality of the resource allowing potential users to immediately see whether the resource is of use for them.")
    private String quality;
    /**
     * Country/Region
     * <p>
     * This element allows users to specify a country and/or a region to allow depositors to specify where the language the document is in is spoken
     * 
     */
    @JsonProperty("region")
    @JsonPropertyDescription("This element allows users to specify a country and/or a region to allow depositors to specify where the language the document is in is spoken")
    private String region;

    /**
     * Language Code
     * <p>
     * This element can be used to add an ISO language code from ISO-639-3 to uniquely identify the language a document is written in
     * (Required)
     * 
     */
    @JsonProperty("language_code")
    public String getLanguageCode() {
        return languageCode;
    }

    /**
     * Language Code
     * <p>
     * This element can be used to add an ISO language code from ISO-639-3 to uniquely identify the language a document is written in
     * (Required)
     * 
     */
    @JsonProperty("language_code")
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    /**
     * Resource Type
     * <p>
     * This element allows the depositor to specify the type of the resource (Text, Audio, Video, Time-Series, Photo, etc.)
     * (Required)
     * 
     */
    @JsonProperty("ling_resource_type")
    public Set<LingResourceType> getLingResourceType() {
        return lingResourceType;
    }

    /**
     * Resource Type
     * <p>
     * This element allows the depositor to specify the type of the resource (Text, Audio, Video, Time-Series, Photo, etc.)
     * (Required)
     * 
     */
    @JsonProperty("ling_resource_type")
    public void setLingResourceType(Set<LingResourceType> lingResourceType) {
        this.lingResourceType = lingResourceType;
    }

    /**
     * Project Name
     * <p>
     * This element allows the depositor to specify the projects which were at the source of the creation of the resource
     * 
     */
    @JsonProperty("project_name")
    public String getProjectName() {
        return projectName;
    }

    /**
     * Project Name
     * <p>
     * This element allows the depositor to specify the projects which were at the source of the creation of the resource
     * 
     */
    @JsonProperty("project_name")
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Quality
     * <p>
     * This element allows depositors to indicate the quality of the resource allowing potential users to immediately see whether the resource is of use for them.
     * 
     */
    @JsonProperty("quality")
    public String getQuality() {
        return quality;
    }

    /**
     * Quality
     * <p>
     * This element allows depositors to indicate the quality of the resource allowing potential users to immediately see whether the resource is of use for them.
     * 
     */
    @JsonProperty("quality")
    public void setQuality(String quality) {
        this.quality = quality;
    }

    /**
     * Country/Region
     * <p>
     * This element allows users to specify a country and/or a region to allow depositors to specify where the language the document is in is spoken
     * 
     */
    @JsonProperty("region")
    public String getRegion() {
        return region;
    }

    /**
     * Country/Region
     * <p>
     * This element allows users to specify a country and/or a region to allow depositors to specify where the language the document is in is spoken
     * 
     */
    @JsonProperty("region")
    public void setRegion(String region) {
        this.region = region;
    }

}
