package eudat.b2sharev2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Config of the client
 * 
 * @author Nuno Freire (nfreire@gmail.com)
 * @since 16/05/2016
 */
public class Config {
	
    private static String apiBaseUrl;
    private static String accessToken;
    private static String communityId;

    static {
        Properties props = new Properties();
        try {
            props.load(Config.class.getResourceAsStream("/b2share-api.properties"));
            apiBaseUrl = props.getProperty("b2sharev2.api.url");
            accessToken = props.getProperty("b2sharev2.api.accessToken");
            communityId = props.getProperty("b2sharev2.api.community");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Config() {
	}

	public static String getApiBaseUrl() {
        return apiBaseUrl;
    }

	public static String getAccessToken() {
        return accessToken;
	}
	
	public static String getCommunityId() {
		return communityId;
	}

}
