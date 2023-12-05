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
    private final User.UserBuilder userBuilder;
    Faker faker = new Faker();
    private final List<User> registeredUsers;
    public UserFactory(User.UserBuilder userBuilder, List<User> registeredUsers) {
        this.userBuilder = userBuilder;
        this.registeredUsers = registeredUsers;
    }
    public User.UserBuilder getRandomUser(){
        return userBuilder
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setEmail(faker.internet().emailAddress())
                .setPassword(faker.internet().password());
    };

    public User.UserBuilder getAlreadyRegisteredUser() {
        if (registeredUsers.isEmpty()) {
            throw new RuntimeException("No registered users found");
        }

        Random random = new Random();
        User selectedUser = registeredUsers.get(random.nextInt(registeredUsers.size()));

        return userBuilder
                .setFirstName(selectedUser.getFirstName())
                .setLastName(selectedUser.getLastName())
                .setEmail(selectedUser.getEmail())
                .setPassword(selectedUser.getPassword());
    }

    public static List<User> loadUsersFromConfigFile(String filePath) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            Config config = mapper.readValue(new File(filePath), Config.class);
            return config.getUsers();
        } catch (IOException e) {
            throw new RuntimeException("Error reading YAML file: " + e.getMessage());
        }

}
