package week4.flightbooking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommercialAirplane extends Airplane {
    private static final double ECONOMY_SEAT_MULTIPLIER = 1.75;
    private static final double BUSINESS_SEAT_MULTIPLIER = 1.98;
    private static final double ECONOMY_TAKEN_MULTIPLIER = 2.02;
    private static final double BUSINESS_TAKEN_MULTIPLIER = 2.87;
    private static final double LUGGAGE_WEIGHT_MULTIPLIER = 0.3;

    private final int economyCapacity;
    private final int businessCapacity;
    private final List<Person> economyPassengers;
    private final List<Person> businessPassengers;

    public CommercialAirplane(String code, double fuelLevel, int economyCapacity, int businessCapacity) {
        super(code, fuelLevel);
        this.economyCapacity = economyCapacity;
        this.businessCapacity = businessCapacity;
        this.economyPassengers = new ArrayList<>();
        this.businessPassengers = new ArrayList<>();
    }

    @Override
    public double calculateFuelUsage(int distance) {
        int economyTaken = economyPassengers.size();
        int businessTaken = businessPassengers.size();
        double luggageWeight = getTotalLuggageWeight();

        return ((economyCapacity * ECONOMY_SEAT_MULTIPLIER) + (businessCapacity * BUSINESS_SEAT_MULTIPLIER)) * distance
                + (economyTaken * ECONOMY_TAKEN_MULTIPLIER)
                + (businessTaken * BUSINESS_TAKEN_MULTIPLIER)
                + (luggageWeight * LUGGAGE_WEIGHT_MULTIPLIER);
    }

    @Override
    public int getEmptySeats() {
        return (economyCapacity - economyPassengers.size()) + (businessCapacity - businessPassengers.size());
    }

    @Override
    public void reserveSeat(Person passenger) throws FlightBookingException {
        if (economyPassengers.size() < economyCapacity) {
            economyPassengers.add(passenger);
        } else if (businessPassengers.size() < businessCapacity) {
            businessPassengers.add(passenger);
        } else {
            throw new NoSeatsAvailableException("No seats available on commercial flight " + getCode());
        }
    }

    @Override
    public List<Person> getPassengers() {
        List<Person> all = new ArrayList<>(economyPassengers);
        all.addAll(businessPassengers);
        return Collections.unmodifiableList(all);
    }
}
