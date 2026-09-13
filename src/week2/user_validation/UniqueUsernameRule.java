package week2.user_validation;

public class UniqueUsernameRule implements ValidationRule {
    private UserStorage storage;

    public UniqueUsernameRule(UserStorage storage) {
        this.storage = storage;
    }

    @Override
    public void validate(User user) throws ValidationException {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new ValidationException("Username cannot be empty.");
        }

        if (storage.existsByUsername(user.getUsername())) {
            throw new ValidationException("Username '" + user.getUsername() + "' is already taken.");
        }
    }
}