package ca.uqam.mgl7230.tp1.service;

import ca.uqam.mgl7230.tp1.model.flight.FlightInformation;
import ca.uqam.mgl7230.tp1.model.passenger.*;
import ca.uqam.mgl7230.tp1.utils.DistanceCalculator;

import java.util.Map;

public class PassengerService {

    private DistanceCalculator distanceCalculator;

    public PassengerService(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    public Passenger createPassenger(FlightInformation flightInformation,
                                     Map<PassengerKeyConstants, Object> passengerData) {
        String passengerPassport = (String) passengerData.get(PassengerKeyConstants.PASSENGER_PASSPORT);
        String passengerName = (String) passengerData.get(PassengerKeyConstants.PASSENGER_NAME);
        int passengerAge = (int) passengerData.get(PassengerKeyConstants.PASSENGER_AGE);
        Passenger passenger = null;
        boolean passengerFound = false;
        while (!passengerFound) {
            switch (passengerData.get(PassengerKeyConstants.PASSENGER_CLASS)) {
                case PassengerClass.FIRST_CLASS -> {
                    passenger = new FirstClassPassenger(passengerPassport, passengerName, passengerAge,
                            distanceCalculator.calculate(flightInformation));
                    passengerFound = true;
                }
                case PassengerClass.BUSINESS_CLASS -> {
                    passenger = new BusinessClassPassenger(passengerPassport, passengerName, passengerAge,
                            distanceCalculator.calculate(flightInformation));
                    passengerFound = true;
                }
                case PassengerClass.ECONOMY_CLASS -> {
                    passenger = new EconomyClassPassenger(passengerPassport, passengerName, passengerAge,
                            distanceCalculator.calculate(flightInformation));
                    passengerFound = true;
                }
                default -> {
                    System.out.println("Passenger type not existent, please try again");
                    return null;
                }
            }
        }
        return passenger;
    }
}
