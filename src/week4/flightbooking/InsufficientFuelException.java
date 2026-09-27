package week4.flightbooking;

public class InsufficientFuelException extends FlightBookingException {
    public InsufficientFuelException(String message) {
        super(message);
    }
}