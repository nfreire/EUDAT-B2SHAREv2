
package eudat.b2sharev2.model.linguistics;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LingResourceType {

    TEXT("Text"),
    IMAGE("Image"),
    VIDEO("Video"),
    AUDIO("Audio"),
    TIME_SERIES("Time-Series"),
    OTHER("Other"),
    TREEBANK("treebank");
    private final String value;
    private final static Map<String, LingResourceType> CONSTANTS = new HashMap<String, LingResourceType>();

    static {
        for (LingResourceType c: values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    private LingResourceType(String value) {
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
    public static LingResourceType fromValue(String value) {
        LingResourceType constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }

}
