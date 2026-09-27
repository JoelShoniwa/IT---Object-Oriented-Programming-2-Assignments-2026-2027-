package week4.flightbooking;

public class Luggage {
    private final double weight;
    private final LuggageType type;

    public Luggage(double weight, LuggageType type) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Luggage weight must be positive.");
        }
        this.weight = weight;
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public LuggageType getType() {
        return type;
    }
}