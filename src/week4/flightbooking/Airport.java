package week4.flightbooking;

public enum Airport {
    JFK,
    AMS,
    MEX,
    LAX;

    public int getDistance(Airport destination) {
        if (this == destination) return 0;
        if ((this == JFK && destination == AMS) || (this == AMS && destination == JFK)) return 5848;
        if ((this == JFK && destination == MEX) || (this == MEX && destination == JFK)) return 3366;
        if ((this == JFK && destination == LAX) || (this == LAX && destination == JFK)) return 3975;
        if ((this == AMS && destination == MEX) || (this == MEX && destination == AMS)) return 9206;
        if ((this == AMS && destination == LAX) || (this == LAX && destination == AMS)) return 8956;
        if ((this == MEX && destination == LAX) || (this == LAX && destination == MEX)) return 2500;
        throw new IllegalArgumentException("Unknown distance between " + this + " and " + destination);
    }
}