package ca.uqam.mgl7230.tp1.model.flight;

import ca.uqam.mgl7230.tp1.model.plane.PlaneType;

public record FlightInformation(String flightNumber, Double latSource, Double lonSource, Double latDestination,
                                Double lonDestination, PlaneType planeType) {

}
