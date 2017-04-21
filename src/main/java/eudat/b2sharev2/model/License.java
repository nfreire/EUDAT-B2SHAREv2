
package eudat.b2sharev2.model;

import java.net.URI;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * License
 * <p>
 * Specify the license under which this data set is available to the users (e.g. GPL, Apache v2 or Commercial). Please use the License Selector for help and additional information.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "license",
    "license_uri"
})
public class License {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("license")
    private String license;
    /**
     * URL
     * <p>
     * 
     * 
     */
    @JsonProperty("license_uri")
    private URI licenseUri;

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("license")
    public String getLicense() {
        return license;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("license")
    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * URL
     * <p>
     * 
     * 
     */
    @JsonProperty("license_uri")
    public URI getLicenseUri() {
        return licenseUri;
    }

    /**
     * URL
     * <p>
     * 
     * 
     */
    @JsonProperty("license_uri")
    public void setLicenseUri(URI licenseUri) {
        this.licenseUri = licenseUri;
    }

}
