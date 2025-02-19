package ca.uqam.mgl7230.tp1.service;

import ca.uqam.mgl7230.tp1.adapter.flight.FlightCatalog;
import ca.uqam.mgl7230.tp1.adapter.plane.PlaneCatalog;
import ca.uqam.mgl7230.tp1.model.passenger.Passenger;
import ca.uqam.mgl7230.tp1.model.passenger.PassengerClass;

public class FlightPassengerService {

    private int totalAmountFirstClassSeats;
    private int totalAmountBusinessClassSeats;
    private int totalAmountEconomyClassSeats;

    public FlightPassengerService(PlaneCatalog planeCatalog, FlightCatalog flightCatalog, String flightNumber) {
        this.totalAmountFirstClassSeats = planeCatalog.getNumberSeatsFirstClass(
                flightCatalog.getFlightInformation(flightNumber).planeType());
        this.totalAmountBusinessClassSeats = planeCatalog.getNumberSeatsBusinessClass(
                flightCatalog.getFlightInformation(flightNumber).planeType());
        this.totalAmountEconomyClassSeats = planeCatalog.getNumberSeatsEconomyClass(
                flightCatalog.getFlightInformation(flightNumber).planeType());
    }

    public void addPassenger(Passenger passenger) {
        PassengerClass type = passenger.getType();
        if (type == PassengerClass.FIRST_CLASS && totalAmountFirstClassSeats > 0) {
            totalAmountFirstClassSeats -= 1;
        }
        else if (type == PassengerClass.BUSINESS_CLASS && totalAmountBusinessClassSeats > 0) {
            totalAmountBusinessClassSeats -= 1;
        }
        else if (type == PassengerClass.ECONOMY_CLASS && totalAmountEconomyClassSeats > 0){
            totalAmountEconomyClassSeats -= 1;
        }
    }

    public int numberOfFirstClassSeatsAvailable() {
        return totalAmountFirstClassSeats;
    }

    public int numberOfBusinessClassSeatsAvailable() {
        return totalAmountBusinessClassSeats;
    }

    public int numberOfEconomyClassSeatsAvailable() {
        return totalAmountEconomyClassSeats;
    }

    public int numberOfTotalSeatsAvailable() {
        return totalAmountFirstClassSeats + totalAmountBusinessClassSeats + totalAmountEconomyClassSeats;
    }

}
