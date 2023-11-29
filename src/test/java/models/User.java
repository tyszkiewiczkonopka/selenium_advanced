package models;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User() {

    }

    public User(UserBuilder userBuilder){
        this.firstName = userBuilder.firstName;
        this.lastName = userBuilder.lastName;
        this.email = userBuilder.email;
        this.password = userBuilder.password;
    }



    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    @Override
    public String toString() {
        return "User: " + this.firstName + ", " + this.lastName + ", " + this.email + ", " + this.password;
    }

    public static class UserBuilder{
        private String firstName;
        private String lastName;
        private String email;
        private String password;

        public UserBuilder() {
        }


        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
