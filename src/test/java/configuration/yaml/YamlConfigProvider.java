package configuration.yaml;

import configuration.browser.Browser;
import configuration.model.Config;
import models.user.User;

import java.util.List;
import java.util.Optional;

public class YamlConfigProvider {
    private static final YamlConfigReader yamlReader = new YamlConfigReader(Config.class);

    public static Browser getActiveBrowserFromConfigFile() {
        Config config = yamlReader.loadConfig();

        Optional<Browser> activeBrowser = config.getBrowsers().entrySet().stream()
                .map(entry -> entry.getValue().setBrowserType(entry.getKey()))
                .filter(Browser::isActive)
                .findFirst();

        return activeBrowser.orElseThrow(() -> new IllegalArgumentException("No active browser configuration found"));
    }

    public static List<User> loadUsersFromConfigFile() {
        return yamlReader.loadConfig().getUsers();
    }
}
