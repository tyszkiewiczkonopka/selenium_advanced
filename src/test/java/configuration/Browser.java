package configuration;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;


public class Browser {
    final Map<String, Object> properties = new HashMap<>();
    private BrowserType browserType;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    @JsonAnySetter
    void setProperties(String key, Object value) {
        properties.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return properties;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

    public Browser setBrowserType(BrowserType browserType) {
        this.browserType = browserType;
        return this;
    }
}

