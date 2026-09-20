package week3.appstore;

import java.time.LocalDate;
import java.time.Period;

public class User {
    private final String name;
    private final String email;
    private final LocalDate dateOfBirth;

    public User(String name, String rawEmail, LocalDate dateOfBirth) {
        this.name = name;
        // If invalid, null must be entered
        this.email = EmailValidator.isValid(rawEmail) ? rawEmail : null;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        if (dateOfBirth == null) {
            return 0;
        }
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}