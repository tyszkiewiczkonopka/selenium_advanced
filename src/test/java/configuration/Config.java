package configuration;

import lombok.Getter;
import models.User;

import java.util.List;
import java.util.Map;


@Getter
public class Config {
    private Map<BrowserType, Browser> browsers;
    private List<User> users;

    public Config() {
    }

    public Map<BrowserType, Browser> getBrowsers() {
        return browsers;
    }
    public List<User> getUsers() {
        return users;
    }


}
