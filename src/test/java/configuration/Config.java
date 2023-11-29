package configuration;

import lombok.Getter;
import models.User;

import java.util.Map;


@Getter
public class Config {
    private Map<BrowserType, Browser> browsers;
    private User user;

    public Config() {
    }

    public Map<BrowserType, Browser> getBrowsers() {
        return browsers;
    }


}
