package week4.flightbooking;

public class NoSeatsAvailableException extends FlightBookingException {
    public NoSeatsAvailableException(String message) {
        super(message);
    }
}