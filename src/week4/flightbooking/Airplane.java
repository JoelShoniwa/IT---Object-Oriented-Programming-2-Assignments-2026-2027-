package week4.flightbooking;

import java.util.List;

public abstract class Airplane implements Flight24Exportable {
    private final String code;
    private double fuelLevel;

    protected Airplane(String code, double fuelLevel) {
        this.code = code;
        this.fuelLevel = fuelLevel;
    }

    public String getCode() {
        return code;
    }

    public double getFuelLevel() {
        return fuelLevel;
    }

    public abstract double calculateFuelUsage(int distance);

    public abstract int getEmptySeats();

    public abstract void reserveSeat(Person passenger) throws FlightBookingException;

    public abstract List<Person> getPassengers();

    public double getTotalLuggageWeight() {
        return getPassengers().stream()
                .mapToDouble(Person::getTotalLuggageWeight)
                .sum();
    }

    @Override
    public String toFlight24String() {
        return String.format("P: %s. %.0f liter fuel. %d empty seats.", code, fuelLevel, getEmptySeats());
    }
}