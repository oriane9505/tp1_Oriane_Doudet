package ca.uqam.mgl7230.tp1.utils;

import ca.uqam.mgl7230.tp1.model.flight.FlightInformation;

public class DistanceCalculator {

    public int calculate(FlightInformation flightInformation) {
        double lat1 = Math.toRadians(flightInformation.latSource());
        double lon1 = Math.toRadians(flightInformation.lonSource());
        double lat2 = Math.toRadians(flightInformation.latDestination());
        double lon2 = Math.toRadians(flightInformation.lonDestination());

        return (int) (Math.acos(Math.sin(lat1) * Math.sin(lat2) +
                        Math.cos(lat1) * Math.cos(lat2) *
                                Math.cos(lon2 - lon1)) * 6371);
    }
}
