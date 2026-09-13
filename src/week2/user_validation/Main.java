package week2.user_validation;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        UserStorage storage = new UserStorage();
        UserValidation validator = new UserValidation(storage);

        // Configure pipeline rules
        validator.addRule(new UniqueUsernameRule(storage));
        validator.addRule(new EmailRule());
        validator.addRule(new AgeRule(18));
        validator.addRule(new PasswordRule(false, true, true, true, true));

        // 1. Valid user test
        User validUser = new User("JoelS", "StrongP@ss1", "joel@example.com", LocalDate.of(2000, 5, 15));
        try {
            validator.registerUser(validUser);
            System.out.println("Registration success: " + validUser.getUsername());
        } catch (ValidationException e) {
            System.err.println("Registration failed: " + e.getMessage());
        }

        // 2. Duplicate username test
        User duplicateUser = new User("JoelS", "OtherP@ss2", "other@example.com", LocalDate.of(1999, 1, 1));
        try {
            validator.registerUser(duplicateUser);
            System.out.println("Registration success: " + duplicateUser.getUsername());
        } catch (ValidationException e) {
            System.err.println("Registration failed: " + e.getMessage());
        }
    }
}