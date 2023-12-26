package configuration.yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import configuration.model.Config;

import java.io.File;
import java.io.IOException;

public class YamlConfigReader {

    private final String filePath;
    private final Class<Config> configClass;

    public YamlConfigReader(Class<Config> configClass) {
        this.filePath = "src/test/resources/configuration.yml";
        this.configClass = configClass;
    }

    public Config loadConfig() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.readValue(new File(filePath), configClass);
        } catch (IOException e) {
            throw new RuntimeException("Error reading YAML file: " + e.getMessage());
        }
    }
}



