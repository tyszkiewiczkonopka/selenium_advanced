package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.javafaker.Faker;
import configuration.Config;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class UserFactory {
    private final List<User> registeredUsers;
    Faker faker = new Faker();

    public UserFactory() {
        this.registeredUsers = loadUsersFromConfigFile();
    }

    public static List<User> loadUsersFromConfigFile() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            Config config = mapper.readValue(new File("src/test/resources/configuration.yml"), Config.class);
            return config.getUsers();
        } catch (IOException e) {
            throw new RuntimeException("Error reading YAML file: " + e.getMessage());
        }

    }


    public User getRandomUser() {
        return new User.UserBuilder()
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setEmail(faker.internet().emailAddress())
                .setPassword(faker.internet().password())
                .build();
    }

    public User getAlreadyRegisteredUser() {
        if (registeredUsers.isEmpty()) {
            throw new RuntimeException("No registered users found");
        }

        Random random = new Random();
        User selectedUser = registeredUsers.get(random.nextInt(registeredUsers.size()));

        return new User.UserBuilder()
                .setFirstName(selectedUser.getFirstName())
                .setLastName(selectedUser.getLastName())
                .setEmail(selectedUser.getEmail())
                .setPassword(selectedUser.getPassword())
                .build();
    }
}
