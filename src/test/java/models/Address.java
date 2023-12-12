package models;

public class Address {

    private String address;
    private String city;
    private String postcode;
    private String country;

    public Address() {

    }

    public Address(AddressBuilder addressBuilder) {
        this.address = addressBuilder.address;
        this.city = addressBuilder.city;
        this.postcode = addressBuilder.postcode;
        this.country = addressBuilder.country;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCountry() {
        return country;
    }

    public String toString() {
        return "Address: " + this.address + ", " + this.city + ", " + this.postcode;
    }

    public static class AddressBuilder {
        private String address;
        private String city;
        private String postcode;
        private String country;

        public AddressBuilder() {
        }


        public AddressBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public AddressBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder setPostcode(String postcode) {
            if (postcode.matches("\\d{2}-\\d{3}")) {
                this.postcode = postcode;
            } else {
                throw new IllegalArgumentException("Invalid postcode format. Should look like 'NN-NNN'.");
            }
            return this;
        }

        public AddressBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }


}
