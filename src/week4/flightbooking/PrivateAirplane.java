package week4.flightbooking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrivateAirplane extends Airplane {
    private static final double DISTANCE_MULTIPLIER = 1.31;
    private static final double SEAT_TAKEN_MULTIPLIER = 1.87;
    private static final double LUGGAGE_WEIGHT_MULTIPLIER = 0.4;

    private final int seatCapacity;
    private final List<Person> passengers;

    public PrivateAirplane(String code, double fuelLevel, int seatCapacity) {
        super(code, fuelLevel);
        this.seatCapacity = seatCapacity;
        this.passengers = new ArrayList<>();
    }

    @Override
    public double calculateFuelUsage(int distance) {
        int seatsTaken = passengers.size();
        double luggageWeight = getTotalLuggageWeight();
        return (seatCapacity * DISTANCE_MULTIPLIER * distance)
                + (seatsTaken * SEAT_TAKEN_MULTIPLIER)
                + (luggageWeight * LUGGAGE_WEIGHT_MULTIPLIER);
    }

    @Override
    public int getEmptySeats() {
        return seatCapacity - passengers.size();
    }

    @Override
    public void reserveSeat(Person passenger) throws FlightBookingException {
        if (getEmptySeats() <= 0) {
            throw new NoSeatsAvailableException("No seats available on private airplane " + getCode());
        }
        for (Luggage lug : passenger.getLuggage()) {
            if (lug.getType() == LuggageType.HOLD) {
                throw new InvalidLuggageException("Private airplane " + getCode() + " has no space for hold luggage.");
            }
        }
        passengers.add(passenger);
    }

    @Override
    public List<Person> getPassengers() {
        return Collections.unmodifiableList(passengers);
    }
}