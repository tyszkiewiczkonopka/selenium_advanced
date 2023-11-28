package configuration;

import lombok.Getter;
import models.User;

import java.util.Map;


@Getter
public class Config {
    private Map<String, Browser> browsers;
    private User.UserBuilder user;

    public Config() {
    }
    public Map<String, Browser> getBrowsers() {
        return browsers;
    }




}
