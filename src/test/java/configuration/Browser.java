package configuration;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


public enum Browser {
    CHROME, FIREFOX, IE, EDGE;
//
//    private String browserName;
//    private boolean startMaximized;
//    private boolean headless;
//    private boolean disableExtensions;
//    private boolean ignoreZoomSetting;
//    private boolean active;

    private boolean active;

    public boolean isActive() {
        return active;
    }

    final Map<String, Object> properties = new HashMap<>();
    @JsonAnySetter
    void setProperties(String key, Object value){properties.put(key, value);}

    @JsonAnyGetter
    public Map<String,Object> getProperties(){return properties;}


}

