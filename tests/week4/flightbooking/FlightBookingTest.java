package week4.flightbooking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class FlightBookingTest {
    private PrivateAirplane privatePlane;
    private CommercialAirplane commercialPlane;
    private Flight flightJfkAms;

    @BeforeEach
    void setUp() {
        privatePlane = new PrivateAirplane("PR-001", 100000.0, 10);
        commercialPlane = new CommercialAirplane("KLM124", 35235.0, 100, 20);
        flightJfkAms = new Flight(Airport.JFK, Airport.AMS, LocalDateTime.of(2023, 9, 26, 12, 54), commercialPlane);
    }

    @Test
    void testLuggageRuleCarryOnLimit() {
        Person person = new Person("Alice");
        assertDoesNotThrow(() -> person.addLuggage(new Luggage(8.0, LuggageType.CARRY_ON)));

        // Cannot add second carry-on
        assertThrows(InvalidLuggageException.class, () ->
                person.addLuggage(new Luggage(5.0, LuggageType.CARRY_ON)));

        // Multiple hold luggage is permitted
        assertDoesNotThrow(() -> person.addLuggage(new Luggage(20.0, LuggageType.HOLD)));
        assertDoesNotThrow(() -> person.addLuggage(new Luggage(23.0, LuggageType.HOLD)));
    }

    @Test
    void testPrivatePlaneRejectsHoldLuggage() throws InvalidLuggageException {
        Person passenger = new Person("Bob");
        passenger.addLuggage(new Luggage(15.0, LuggageType.HOLD));

        assertThrows(InvalidLuggageException.class, () -> privatePlane.reserveSeat(passenger));
    }

    @Test
    void testCommercialAirplaneSeatReservationFallback() throws FlightBookingException {
        CommercialAirplane smallPlane = new CommercialAirplane("CMP-01", 50000.0, 1, 1);
        Person p1 = new Person("P1");
        Person p2 = new Person("P2");
        Person p3 = new Person("P3");

        smallPlane.reserveSeat(p1); // Economy
        smallPlane.reserveSeat(p2); // Business fallback
        assertEquals(0, smallPlane.getEmptySeats());

        assertThrows(NoSeatsAvailableException.class, () -> smallPlane.reserveSeat(p3));
    }

    @Test
    void testDepartThrowsInsufficientFuel() {
        Airplane emptyFuelPlane = new PrivateAirplane("PR-DRY", 10.0, 8);
        Flight lowFuelFlight = new Flight(Airport.JFK, Airport.AMS, LocalDateTime.now(), emptyFuelPlane);

        assertThrows(InsufficientFuelException.class, lowFuelFlight::depart);
        assertEquals(FlightStatus.AWAITING_DEPARTURE, lowFuelFlight.getStatus());
    }

    @Test
    void testSuccessfulDepartureTransitionsStatus() throws InsufficientFuelException {
        Airplane loadedPlane = new PrivateAirplane("PR-FULL", 150000.0, 8);
        Flight validFlight = new Flight(Airport.JFK, Airport.AMS, LocalDateTime.now(), loadedPlane);

        validFlight.depart();
        assertEquals(FlightStatus.DEPARTED, validFlight.getStatus());
    }

    @Test
    void testFlight24FormattedOutput() {
        assertEquals("F: JFK -> AMS. Departure 26-09-2023 12:54.", flightJfkAms.toFlight24String());

        CommercialAirplane plane = new CommercialAirplane("KLM124", 35235.0, 20, 4);
        assertEquals("P: KLM124. 35235 liter fuel. 24 empty seats.", plane.toFlight24String());
    }
}