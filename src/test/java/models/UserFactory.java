package models;

import com.github.javafaker.Faker;

public class UserFactory {
    private final User.UserBuilder userBuilder;
    Faker faker = new Faker();
    public UserFactory(User.UserBuilder userBuilder) {
        this.userBuilder = userBuilder;
    }
    public User.UserBuilder getRandomUser(){
        return userBuilder
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setEmail(faker.internet().emailAddress())
                .setPassword(faker.internet().password());
    };
//    public User.UserBuilder getAlreadyRegisteredUser(){
//        return //powinna zwrócić użytkownika, który już ma konto – po prostu załóżcie własnego usera (możecie jego dane zahardcodować w pliku yaml i z niego pobierać)
//
//    }
}
