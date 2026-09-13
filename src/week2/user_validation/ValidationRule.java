package week2.user_validation;

public interface ValidationRule {
    void validate(User user) throws ValidationException;
}