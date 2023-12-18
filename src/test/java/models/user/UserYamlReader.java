package models.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import configuration.model.Config;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserYamlReader {
    public static List<User> loadUsersFromConfigFile() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            Config config = mapper.readValue(new File("src/test/resources/configuration.yml"), Config.class);
            return config.getUsers();
        } catch (IOException e) {
            throw new RuntimeException("Error reading YAML file: " + e.getMessage());
        }

    }
}
