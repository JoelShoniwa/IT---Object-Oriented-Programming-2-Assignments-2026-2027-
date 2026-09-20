package week3.appstore;

import java.time.LocalDateTime;

public class Purchase {
    private final User user;
    private final App app;
    private final LocalDateTime timestamp;
    private final double pricePaid;

    public Purchase(User user, App app) {
        this.user = user;
        this.app = app;
        this.timestamp = LocalDateTime.now();
        this.pricePaid = app.getPrice();
    }

    public User getUser() {
        return user;
    }

    public App getApp() {
        return app;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public double getPricePaid() {
        return pricePaid;
    }
}