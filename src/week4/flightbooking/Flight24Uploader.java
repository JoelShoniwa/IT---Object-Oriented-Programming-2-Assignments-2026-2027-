package week4.flightbooking;

import java.util.ArrayList;
import java.util.List;

public class Flight24Uploader {
    private final List<Flight> flights;
    private final List<Airplane> airplanes;

    public Flight24Uploader() {
        this.flights = new ArrayList<>();
        this.airplanes = new ArrayList<>();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void addAirplane(Airplane plane) {
        airplanes.add(plane);
    }

    public String upload() {
        StringBuilder sb = new StringBuilder();
        for (Flight f : flights) {
            sb.append(f.toFlight24String()).append(System.lineSeparator());
        }
        for (Airplane p : airplanes) {
            sb.append(p.toFlight24String()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}