
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
    "contributor_name",
    "contributor_type"
})
public class Contributor {

    /**
     * Name
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("contributor_name")
    private String contributorName;
    /**
     * Type
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("contributor_type")
    private Contributor.ContributorType contributorType;

    /**
     * Name
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("contributor_name")
    public String getContributorName() {
        return contributorName;
    }

    /**
     * Name
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("contributor_name")
    public void setContributorName(String contributorName) {
        this.contributorName = contributorName;
    }

    /**
     * Type
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("contributor_type")
    public Contributor.ContributorType getContributorType() {
        return contributorType;
    }

    /**
     * Type
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("contributor_type")
    public void setContributorType(Contributor.ContributorType contributorType) {
        this.contributorType = contributorType;
    }

    public enum ContributorType {

        CONTACT_PERSON("ContactPerson"),
        DATA_COLLECTOR("DataCollector"),
        DATA_CURATOR("DataCurator"),
        DATA_MANAGER("DataManager"),
        DISTRIBUTOR("Distributor"),
        EDITOR("Editor"),
        HOSTING_INSTITUTION("HostingInstitution"),
        PRODUCER("Producer"),
        PROJECT_LEADER("ProjectLeader"),
        PROJECT_MANAGER("ProjectManager"),
        PROJECT_MEMBER("ProjectMember"),
        REGISTRATION_AGENCY("RegistrationAgency"),
        REGISTRATION_AUTHORITY("RegistrationAuthority"),
        RELATED_PERSON("RelatedPerson"),
        RESEARCHER("Researcher"),
        RESEARCH_GROUP("ResearchGroup"),
        RIGHTS_HOLDER("RightsHolder"),
        SPONSOR("Sponsor"),
        SUPERVISOR("Supervisor"),
        WORK_PACKAGE_LEADER("WorkPackageLeader"),
        OTHER("Other");
        private final String value;
        private final static Map<String, Contributor.ContributorType> CONSTANTS = new HashMap<String, Contributor.ContributorType>();

        static {
            for (Contributor.ContributorType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private ContributorType(String value) {
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
        public static Contributor.ContributorType fromValue(String value) {
            Contributor.ContributorType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
