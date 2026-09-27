package week4.flightbooking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight implements Flight24Exportable {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private final Airport departureAirport;
    private final Airport arrivalAirport;
    private final LocalDateTime departureTime;
    private final Airplane airplane;
    private FlightStatus status;

    public Flight(Airport departureAirport, Airport arrivalAirport, LocalDateTime departureTime, Airplane airplane) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.airplane = airplane;
        this.status = FlightStatus.AWAITING_DEPARTURE;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void bookPassenger(Person passenger) throws FlightBookingException {
        if (status == FlightStatus.DEPARTED || status == FlightStatus.LANDED) {
            throw new FlightBookingException("Cannot book ticket: Flight has already departed.");
        }
        airplane.reserveSeat(passenger);
    }

    public void depart() throws InsufficientFuelException {
        int distance = departureAirport.getDistance(arrivalAirport);
        double fuelRequired = airplane.calculateFuelUsage(distance);

        if (airplane.getFuelLevel() < fuelRequired) {
            throw new InsufficientFuelException(String.format(
                    "Insufficient fuel to depart. Current: %.1f L, Required: %.1f L",
                    airplane.getFuelLevel(), fuelRequired));
        }

        this.status = FlightStatus.DEPARTED;
    }

    @Override
    public String toFlight24String() {
        return String.format("F: %s -> %s. Departure %s.",
                departureAirport, arrivalAirport, departureTime.format(FORMATTER));
    }
}