package configuration.model;

import configuration.browser.Browser;
import configuration.browser.BrowserType;
import lombok.Getter;
import models.user.User;

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
