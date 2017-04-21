
package eudat.b2sharev2.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "$schema",
    "_deposit",
    "_files",
    "_oai",
    "_pid",
    "alternate_identifiers",
    "community",
    "community_specific",
    "contact_email",
    "contributors",
    "creators",
    "descriptions",
    "disciplines",
    "embargo_date",
    "keywords",
    "language",
    "license",
    "open_access",
    "publication_date",
    "publication_state",
    "publisher",
    "resource_types",
    "titles",
    "version"
})
public class Record {

    @JsonProperty("$schema")
    private String $schema;
    @JsonProperty("_deposit")
    private Deposit deposit;
    @JsonProperty("_files")
    private List<Object> files = null;
    @JsonProperty("_oai")
    private Oai oai;
    /**
     * Persistent Identifiers
     * <p>
     * Array of persistent identifiers pointing to this record.
     * 
     */
    @JsonProperty("_pid")
    @JsonPropertyDescription("Array of persistent identifiers pointing to this record.")
    private Object pid;
    /**
     * Alternate identifiers
     * <p>
     * Any kind of other reference such as a URN, URI or an ISBN number.
     * 
     */
    @JsonProperty("alternate_identifiers")
    @JsonDeserialize(as = java.util.LinkedHashSet.class)
    @JsonPropertyDescription("Any kind of other reference such as a URN, URI or an ISBN number.")
    private Set<AlternateIdentifier> alternateIdentifiers = null;
    /**
     * Community
     * <p>
     * The community to which the record has been submitted.
     * (Required)
     * 
     */
    @JsonProperty("community")
    @JsonPropertyDescription("The community to which the record has been submitted.")
    private String community;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("community_specific")
    private CommunitySpecific communitySpecific;
    /**
     * Contact Email
     * <p>
     * Contact email information for this record.
     * 
     */
    @JsonProperty("contact_email")
    @JsonPropertyDescription("Contact email information for this record.")
    private String contactEmail;
    /**
     * Contributors
     * <p>
     * The list of all other contributors. Please mention all persons that were relevant in the creation of the resource.
     * 
     */
    @JsonProperty("contributors")
    @JsonDeserialize(as = java.util.LinkedHashSet.class)
    @JsonPropertyDescription("The list of all other contributors. Please mention all persons that were relevant in the creation of the resource.")
    private Set<Contributor> contributors = null;
    /**
     * Creators
     * <p>
     * The full name of the creators. The personal name format should be: family, given (e.g.: Smith, John).
     * 
     */
    @JsonProperty("creators")
    @JsonDeserialize(as = java.util.LinkedHashSet.class)
    @JsonPropertyDescription("The full name of the creators. The personal name format should be: family, given (e.g.: Smith, John).")
    private Set<Creator> creators = null;
    /**
     * Descriptions
     * <p>
     * A more elaborate description of the resource. Focus on a content description that makes it easy for others to find, and to interpret its relevance.
     * 
     */
    @JsonProperty("descriptions")
    @JsonDeserialize(as = java.util.LinkedHashSet.class)
    @JsonPropertyDescription("A more elaborate description of the resource. Focus on a content description that makes it easy for others to find, and to interpret its relevance.")
    private Set<Description> descriptions = null;
    /**
     * Disciplines
     * <p>
     * The scientific disciplines linked with the resource.
     * 
     */
    @JsonProperty("disciplines")
    @JsonDeserialize(as = java.util.LinkedHashSet.class)
    @JsonPropertyDescription("The scientific disciplines linked with the resource.")
    private Set<String> disciplines = null;
    /**
     * Embargo Date
     * <p>
     * The date marking the end of the embargo period. The record will be marked as open access on the specified date at midnight. Please note that the record metadata is always publicly accessible, and only the data files can have private access.
     * 
     */
    @JsonProperty("embargo_date")
    @JsonPropertyDescription("The date marking the end of the embargo period. The record will be marked as open access on the specified date at midnight. Please note that the record metadata is always publicly accessible, and only the data files can have private access.")
    private Date embargoDate;
    /**
     * Keywords
     * <p>
     * A list of keywords or key phrases describing the resource.
     * 
     */
    @JsonProperty("keywords")
    @JsonDeserialize(as = java.util.LinkedHashSet.class)
    @JsonPropertyDescription("A list of keywords or key phrases describing the resource.")
    private Set<String> keywords = null;
    /**
     * Language
     * <p>
     * The primary language of the resource. Please use ISO_639-3 language codes.
     * 
     */
    @JsonProperty("language")
    @JsonPropertyDescription("The primary language of the resource. Please use ISO_639-3 language codes.")
    private String language;
    /**
     * License
     * <p>
     * Specify the license under which this data set is available to the users (e.g. GPL, Apache v2 or Commercial). Please use the License Selector for help and additional information.
     * 
     */
    @JsonProperty("license")
    @JsonPropertyDescription("Specify the license under which this data set is available to the users (e.g. GPL, Apache v2 or Commercial). Please use the License Selector for help and additional information.")
    private License license;
    /**
     * Open Access
     * <p>
     * Indicate whether the record's files are publicly accessible or not. In case of restricted access the uploaded files will only be accessible by the record's owner and the community administrators. Please note that the record's metadata is always publicly accessible. 
     * (Required)
     * 
     */
    @JsonProperty("open_access")
    @JsonPropertyDescription("Indicate whether the record's files are publicly accessible or not. In case of restricted access the uploaded files will only be accessible by the record's owner and the community administrators. Please note that the record's metadata is always publicly accessible. ")
    private Boolean openAccess;
    /**
     * Publication Date
     * <p>
     * The date when the data was or will be made publicly available (e.g. 1971-07-13)
     * 
     */
    @JsonProperty("publication_date")
    @JsonPropertyDescription("The date when the data was or will be made publicly available (e.g. 1971-07-13)")
    private String publicationDate;
    /**
     * Publication State
     * <p>
     * State of the publication workflow.
     * (Required)
     * 
     */
    @JsonProperty("publication_state")
    @JsonPropertyDescription("State of the publication workflow.")
    private Record.PublicationState publicationState;
    /**
     * Publisher
     * <p>
     * The entity responsible for making the resource available, either a person, an organization, or a service.
     * 
     */
    @JsonProperty("publisher")
    @JsonPropertyDescription("The entity responsible for making the resource available, either a person, an organization, or a service.")
    private String publisher;
    /**
     * Resource Type
     * <p>
     * The type(s) of the resource.
     * 
     */
    @JsonProperty("resource_types")
    @JsonDeserialize(as = java.util.LinkedHashSet.class)
    @JsonPropertyDescription("The type(s) of the resource.")
    private Set<ResourceType> resourceTypes = null;
    /**
     * Titles
     * <p>
     * The title(s) of the uploaded resource, or a name by which the resource is known.
     * (Required)
     * 
     */
    @JsonProperty("titles")
    @JsonDeserialize(as = java.util.LinkedHashSet.class)
    @JsonPropertyDescription("The title(s) of the uploaded resource, or a name by which the resource is known.")
    private Set<Title> titles = null;
    /**
     * Version
     * <p>
     * Denote the version of the resource.
     * 
     */
    @JsonProperty("version")
    @JsonPropertyDescription("Denote the version of the resource.")
    private String version;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("$schema")
    public String get$schema() {
        return $schema;
    }

    @JsonProperty("$schema")
    public void set$schema(String $schema) {
        this.$schema = $schema;
    }

    @JsonProperty("_deposit")
    public Deposit getDeposit() {
        return deposit;
    }

    @JsonProperty("_deposit")
    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }

    @JsonProperty("_files")
    public List<Object> getFiles() {
        return files;
    }

    @JsonProperty("_files")
    public void setFiles(List<Object> files) {
        this.files = files;
    }

    @JsonProperty("_oai")
    public Oai getOai() {
        return oai;
    }

    @JsonProperty("_oai")
    public void setOai(Oai oai) {
        this.oai = oai;
    }

    /**
     * Persistent Identifiers
     * <p>
     * Array of persistent identifiers pointing to this record.
     * 
     */
    @JsonProperty("_pid")
    public Object getPid() {
        return pid;
    }

    /**
     * Persistent Identifiers
     * <p>
     * Array of persistent identifiers pointing to this record.
     * 
     */
    @JsonProperty("_pid")
    public void setPid(Object pid) {
        this.pid = pid;
    }

    /**
     * Alternate identifiers
     * <p>
     * Any kind of other reference such as a URN, URI or an ISBN number.
     * 
     */
    @JsonProperty("alternate_identifiers")
    public Set<AlternateIdentifier> getAlternateIdentifiers() {
        return alternateIdentifiers;
    }

    /**
     * Alternate identifiers
     * <p>
     * Any kind of other reference such as a URN, URI or an ISBN number.
     * 
     */
    @JsonProperty("alternate_identifiers")
    public void setAlternateIdentifiers(Set<AlternateIdentifier> alternateIdentifiers) {
        this.alternateIdentifiers = alternateIdentifiers;
    }

    /**
     * Community
     * <p>
     * The community to which the record has been submitted.
     * (Required)
     * 
     */
    @JsonProperty("community")
    public String getCommunity() {
        return community;
    }

    /**
     * Community
     * <p>
     * The community to which the record has been submitted.
     * (Required)
     * 
     */
    @JsonProperty("community")
    public void setCommunity(String community) {
        this.community = community;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("community_specific")
    public CommunitySpecific getCommunitySpecific() {
        return communitySpecific;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("community_specific")
    public void setCommunitySpecific(CommunitySpecific communitySpecific) {
        this.communitySpecific = communitySpecific;
    }

    /**
     * Contact Email
     * <p>
     * Contact email information for this record.
     * 
     */
    @JsonProperty("contact_email")
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Contact Email
     * <p>
     * Contact email information for this record.
     * 
     */
    @JsonProperty("contact_email")
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * Contributors
     * <p>
     * The list of all other contributors. Please mention all persons that were relevant in the creation of the resource.
     * 
     */
    @JsonProperty("contributors")
    public Set<Contributor> getContributors() {
        return contributors;
    }

    /**
     * Contributors
     * <p>
     * The list of all other contributors. Please mention all persons that were relevant in the creation of the resource.
     * 
     */
    @JsonProperty("contributors")
    public void setContributors(Set<Contributor> contributors) {
        this.contributors = contributors;
    }

    /**
     * Creators
     * <p>
     * The full name of the creators. The personal name format should be: family, given (e.g.: Smith, John).
     * 
     */
    @JsonProperty("creators")
    public Set<Creator> getCreators() {
        return creators;
    }

    /**
     * Creators
     * <p>
     * The full name of the creators. The personal name format should be: family, given (e.g.: Smith, John).
     * 
     */
    @JsonProperty("creators")
    public void setCreators(Set<Creator> creators) {
        this.creators = creators;
    }

    /**
     * Descriptions
     * <p>
     * A more elaborate description of the resource. Focus on a content description that makes it easy for others to find, and to interpret its relevance.
     * 
     */
    @JsonProperty("descriptions")
    public Set<Description> getDescriptions() {
        return descriptions;
    }

    /**
     * Descriptions
     * <p>
     * A more elaborate description of the resource. Focus on a content description that makes it easy for others to find, and to interpret its relevance.
     * 
     */
    @JsonProperty("descriptions")
    public void setDescriptions(Set<Description> descriptions) {
        this.descriptions = descriptions;
    }

    /**
     * Disciplines
     * <p>
     * The scientific disciplines linked with the resource.
     * 
     */
    @JsonProperty("disciplines")
    public Set<String> getDisciplines() {
        return disciplines;
    }

    /**
     * Disciplines
     * <p>
     * The scientific disciplines linked with the resource.
     * 
     */
    @JsonProperty("disciplines")
    public void setDisciplines(Set<String> disciplines) {
        this.disciplines = disciplines;
    }

    /**
     * Embargo Date
     * <p>
     * The date marking the end of the embargo period. The record will be marked as open access on the specified date at midnight. Please note that the record metadata is always publicly accessible, and only the data files can have private access.
     * 
     */
    @JsonProperty("embargo_date")
    public Date getEmbargoDate() {
        return embargoDate;
    }

    /**
     * Embargo Date
     * <p>
     * The date marking the end of the embargo period. The record will be marked as open access on the specified date at midnight. Please note that the record metadata is always publicly accessible, and only the data files can have private access.
     * 
     */
    @JsonProperty("embargo_date")
    public void setEmbargoDate(Date embargoDate) {
        this.embargoDate = embargoDate;
    }

    /**
     * Keywords
     * <p>
     * A list of keywords or key phrases describing the resource.
     * 
     */
    @JsonProperty("keywords")
    public Set<String> getKeywords() {
        return keywords;
    }

    /**
     * Keywords
     * <p>
     * A list of keywords or key phrases describing the resource.
     * 
     */
    @JsonProperty("keywords")
    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Language
     * <p>
     * The primary language of the resource. Please use ISO_639-3 language codes.
     * 
     */
    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    /**
     * Language
     * <p>
     * The primary language of the resource. Please use ISO_639-3 language codes.
     * 
     */
    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * License
     * <p>
     * Specify the license under which this data set is available to the users (e.g. GPL, Apache v2 or Commercial). Please use the License Selector for help and additional information.
     * 
     */
    @JsonProperty("license")
    public License getLicense() {
        return license;
    }

    /**
     * License
     * <p>
     * Specify the license under which this data set is available to the users (e.g. GPL, Apache v2 or Commercial). Please use the License Selector for help and additional information.
     * 
     */
    @JsonProperty("license")
    public void setLicense(License license) {
        this.license = license;
    }

    /**
     * Open Access
     * <p>
     * Indicate whether the record's files are publicly accessible or not. In case of restricted access the uploaded files will only be accessible by the record's owner and the community administrators. Please note that the record's metadata is always publicly accessible. 
     * (Required)
     * 
     */
    @JsonProperty("open_access")
    public Boolean getOpenAccess() {
        return openAccess;
    }

    /**
     * Open Access
     * <p>
     * Indicate whether the record's files are publicly accessible or not. In case of restricted access the uploaded files will only be accessible by the record's owner and the community administrators. Please note that the record's metadata is always publicly accessible. 
     * (Required)
     * 
     */
    @JsonProperty("open_access")
    public void setOpenAccess(Boolean openAccess) {
        this.openAccess = openAccess;
    }

    /**
     * Publication Date
     * <p>
     * The date when the data was or will be made publicly available (e.g. 1971-07-13)
     * 
     */
    @JsonProperty("publication_date")
    public String getPublicationDate() {
        return publicationDate;
    }

    /**
     * Publication Date
     * <p>
     * The date when the data was or will be made publicly available (e.g. 1971-07-13)
     * 
     */
    @JsonProperty("publication_date")
    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * Publication State
     * <p>
     * State of the publication workflow.
     * (Required)
     * 
     */
    @JsonProperty("publication_state")
    public Record.PublicationState getPublicationState() {
        return publicationState;
    }

    /**
     * Publication State
     * <p>
     * State of the publication workflow.
     * (Required)
     * 
     */
    @JsonProperty("publication_state")
    public void setPublicationState(Record.PublicationState publicationState) {
        this.publicationState = publicationState;
    }

    /**
     * Publisher
     * <p>
     * The entity responsible for making the resource available, either a person, an organization, or a service.
     * 
     */
    @JsonProperty("publisher")
    public String getPublisher() {
        return publisher;
    }

    /**
     * Publisher
     * <p>
     * The entity responsible for making the resource available, either a person, an organization, or a service.
     * 
     */
    @JsonProperty("publisher")
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Resource Type
     * <p>
     * The type(s) of the resource.
     * 
     */
    @JsonProperty("resource_types")
    public Set<ResourceType> getResourceTypes() {
        return resourceTypes;
    }

    /**
     * Resource Type
     * <p>
     * The type(s) of the resource.
     * 
     */
    @JsonProperty("resource_types")
    public void setResourceTypes(Set<ResourceType> resourceTypes) {
        this.resourceTypes = resourceTypes;
    }

    /**
     * Titles
     * <p>
     * The title(s) of the uploaded resource, or a name by which the resource is known.
     * (Required)
     * 
     */
    @JsonProperty("titles")
    public Set<Title> getTitles() {
        return titles;
    }

    /**
     * Titles
     * <p>
     * The title(s) of the uploaded resource, or a name by which the resource is known.
     * (Required)
     * 
     */
    @JsonProperty("titles")
    public void setTitles(Set<Title> titles) {
        this.titles = titles;
    }

    /**
     * Version
     * <p>
     * Denote the version of the resource.
     * 
     */
    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    /**
     * Version
     * <p>
     * Denote the version of the resource.
     * 
     */
    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public enum PublicationState {

        DRAFT("draft"),
        SUBMITTED("submitted"),
        PUBLISHED("published");
        private final String value;
        private final static Map<String, Record.PublicationState> CONSTANTS = new HashMap<String, Record.PublicationState>();

        static {
            for (Record.PublicationState c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private PublicationState(String value) {
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
        public static Record.PublicationState fromValue(String value) {
            Record.PublicationState constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
