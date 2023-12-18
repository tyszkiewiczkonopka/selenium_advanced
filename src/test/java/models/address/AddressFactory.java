package models.address;

import com.github.javafaker.Faker;
import models.address.Address;

import java.util.Locale;

public class AddressFactory {
    Faker fakerWithLocale = new Faker(new Locale("pl_PL"));

    public AddressFactory() {

    }

    public Address getRandomAddress() {
        return new Address.AddressBuilder()
                .setAddress(fakerWithLocale.address().streetAddress())
                .setCity(fakerWithLocale.address().city())
                .setPostcode(fakerWithLocale.address().zipCode())
                .build();
    }
}
