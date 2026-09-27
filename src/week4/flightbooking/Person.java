package week4.flightbooking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Person {
    private final String name;
    private final List<Luggage> luggage;

    public Person(String name) {
        this.name = name;
        this.luggage = new ArrayList<>();
    }

    public void addLuggage(Luggage piece) throws InvalidLuggageException {
        if (piece.getType() == LuggageType.CARRY_ON) {
            long carryOnCount = luggage.stream()
                    .filter(l -> l.getType() == LuggageType.CARRY_ON)
                    .count();
            if (carryOnCount >= 1) {
                throw new InvalidLuggageException("Passenger " + name + " can carry at most one piece of carry-on baggage.");
            }
        }
        this.luggage.add(piece);
    }

    public String getName() {
        return name;
    }

    public List<Luggage> getLuggage() {
        return Collections.unmodifiableList(luggage);
    }

    public double getTotalLuggageWeight() {
        return luggage.stream().mapToDouble(Luggage::getWeight).sum();
    }
}