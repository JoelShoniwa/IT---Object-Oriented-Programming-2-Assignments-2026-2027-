package week2.user_validation;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private Map<String, User> users;

    public UserStorage() {
        this.users = new HashMap<>();
    }

    public void save(User user) {
        users.put(user.getUsername().toLowerCase(), user);
    }

    public boolean existsByUsername(String username) {
        return users.containsKey(username.toLowerCase());
    }

    public Collection<User> getAll() {
        return Collections.unmodifiableCollection(users.values());
    }
}