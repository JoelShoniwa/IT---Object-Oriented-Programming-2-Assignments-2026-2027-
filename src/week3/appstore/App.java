package week3.appstore;

public class App {
    private static final int VIOLENCE_MIN_AGE = 16;
    private static final int NUDITY_MIN_AGE = 18;

    private final String name;
    private final double price;
    private final boolean containsViolence;
    private final boolean containsNudity;

    public App(String name, double price, boolean containsViolence, boolean containsNudity) {
        this.name = name;
        this.price = price;
        this.containsViolence = containsViolence;
        this.containsNudity = containsNudity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean containsViolence() {
        return containsViolence;
    }

    public boolean containsNudity() {
        return containsNudity;
    }

    public int getMinimumAge() {
        if (containsNudity) {
            return NUDITY_MIN_AGE;
        }
        if (containsViolence) {
            return VIOLENCE_MIN_AGE;
        }
        return 0;
    }
}