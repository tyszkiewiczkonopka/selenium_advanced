package configuration.driver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import configuration.browser.Browser;
import configuration.model.Config;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

// TODO: combine below reader and UserYamlReader into a single one
public class DriverYamlReader {
    private static final String CONFIGURATION_FILE_PATH = "src/test/resources/configuration.yml";

    public static Browser getActiveBrowserFromConfigFile() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            Config config = mapper.readValue(new File(CONFIGURATION_FILE_PATH), Config.class);
            Optional<Browser> activeBrowser = config.getBrowsers().entrySet().stream()
                    .map((singleBrowser) -> {
                        return singleBrowser.getValue().setBrowserType(singleBrowser.getKey());
                    })
                    .filter(Browser::isActive)
                    .findFirst();

            return activeBrowser.orElseThrow(() -> new IllegalArgumentException("No active browser configuration found"));

        } catch (IOException e) {
            throw new RuntimeException("Error reading YAML file: " + e.getMessage());
        }
    }
}
