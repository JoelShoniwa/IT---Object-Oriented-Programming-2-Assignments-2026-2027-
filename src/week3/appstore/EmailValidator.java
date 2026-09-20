package week3.appstore;

import java.util.regex.Pattern;

public final class EmailValidator {
    // Static constant with clear naming
    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    // Private constructor prevents instantiation
    private EmailValidator() {}

    public static boolean isValid(String email) {
        return email != null && EMAIL_REGEX.matcher(email).matches();
    }
}