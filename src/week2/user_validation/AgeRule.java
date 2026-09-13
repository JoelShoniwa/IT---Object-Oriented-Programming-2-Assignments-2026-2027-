package week2.user_validation;

import java.time.LocalDate;
import java.time.Period;

public class AgeRule implements ValidationRule {
    private int minAge;

    public AgeRule(int minAge) {
        this.minAge = minAge;
    }

    @Override
    public void validate(User user) throws ValidationException {
        if (user.getDateOfBirth() == null) {
            throw new ValidationException("Date of birth is required.");
        }

        int calculatedAge = Period.between(user.getDateOfBirth(), LocalDate.now()).getYears();
        if (calculatedAge < minAge) {
            throw new ValidationException("User does not meet minimum age of " + minAge + ". Current age: " + calculatedAge);
        }
    }
}