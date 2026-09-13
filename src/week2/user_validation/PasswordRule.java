package week2.user_validation;

public class PasswordRule implements ValidationRule {
    private boolean allowSpaces;
    private boolean requireSpecial;
    private boolean requireNumber;
    private boolean requireLower;
    private boolean requireUpper;

    public PasswordRule(boolean allowSpaces, boolean requireSpecial, boolean requireNumber,
                        boolean requireLower, boolean requireUpper) {
        this.allowSpaces = allowSpaces;
        this.requireSpecial = requireSpecial;
        this.requireNumber = requireNumber;
        this.requireLower = requireLower;
        this.requireUpper = requireUpper;
    }

    @Override
    public void validate(User user) throws ValidationException {
        String password = user.getPassword();

        if (password == null || password.isEmpty()) {
            throw new ValidationException("Password cannot be empty.");
        }

        if (!allowSpaces && password.contains(" ")) {
            throw new ValidationException("Password cannot contain spaces.");
        }

        if (requireSpecial && !password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
            throw new ValidationException("Password must contain at least one special character.");
        }

        if (requireNumber && !password.matches(".*\\d.*")) {
            throw new ValidationException("Password must contain at least one digit.");
        }

        if (requireLower && !password.matches(".*[a-z].*")) {
            throw new ValidationException("Password must contain at least one lowercase letter.");
        }

        if (requireUpper && !password.matches(".*[A-Z].*")) {
            throw new ValidationException("Password must contain at least one uppercase letter.");
        }
    }
}