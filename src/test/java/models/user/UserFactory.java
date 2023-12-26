package models.user;

import com.github.javafaker.Faker;

import java.util.List;
import java.util.Random;

import static configuration.yaml.YamlConfigProvider.loadUsersFromConfigFile;

public class UserFactory {
    private final List<User> registeredUsers;
    Faker faker = new Faker();

    public UserFactory() {
        this.registeredUsers = loadUsersFromConfigFile();
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
