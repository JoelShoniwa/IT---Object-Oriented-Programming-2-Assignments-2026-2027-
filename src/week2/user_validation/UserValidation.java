package week2.user_validation;

import java.util.ArrayList;
import java.util.List;

public class UserValidation {
    private List<ValidationRule> rules;
    private UserStorage storage;

    public UserValidation(UserStorage storage) {
        this.storage = storage;
        this.rules = new ArrayList<>();
    }

    public void addRule(ValidationRule rule) {
        this.rules.add(rule);
    }

    public void registerUser(User user) throws ValidationException {
        for (ValidationRule rule : rules) {
            rule.validate(user);
        }
        storage.save(user);
    }
}