package week2.user_validation;

import java.util.regex.Pattern;

public class EmailRule implements ValidationRule {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @Override
    public void validate(User user) throws ValidationException {
        String email = user.getEmail();
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException("Invalid email address: " + email);
        }
    }
}