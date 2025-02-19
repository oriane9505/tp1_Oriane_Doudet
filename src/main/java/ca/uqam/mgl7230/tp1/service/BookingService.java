package ca.uqam.mgl7230.tp1.service;

import ca.uqam.mgl7230.tp1.model.flight.FlightInformation;
import ca.uqam.mgl7230.tp1.model.passenger.Passenger;
import ca.uqam.mgl7230.tp1.model.passenger.PassengerClass;
import ca.uqam.mgl7230.tp1.model.passenger.PassengerKeyConstants;

import java.util.EnumMap;
import java.util.Map;

public class BookingService {

    private final FlightPassengerService flightPassengerService;
    private final PassengerService passengerService;

    public BookingService(FlightPassengerService flightPassengerService,
                          PassengerService passengerService) {
        this.flightPassengerService = flightPassengerService;
        this.passengerService = passengerService;
    }

    public Passenger book(Passenger passenger, FlightInformation flightInformation) {
        Map<PassengerKeyConstants, Object> passengerDataMap = getPassengerKeyConstantsObjectMap(passenger);
        if (flightPassengerService.numberOfTotalSeatsAvailable() != 0) {
            if (PassengerClass.FIRST_CLASS.name().equalsIgnoreCase(passenger.getType().name())
                    && (flightPassengerService.numberOfFirstClassSeatsAvailable() == 0)) {
                System.out.println("First Class is Full. Trying Business...");
                passengerDataMap.put(PassengerKeyConstants.PASSENGER_CLASS, PassengerClass.BUSINESS_CLASS);
                passenger = passengerService.createPassenger(flightInformation, passengerDataMap);
            }
            if (PassengerClass.BUSINESS_CLASS.name().equalsIgnoreCase(passenger.getType().name())
                    && (flightPassengerService.numberOfBusinessClassSeatsAvailable() == 0)) {
                System.out.println("Business Class is Full. Trying Economy...");
                passengerDataMap.put(PassengerKeyConstants.PASSENGER_CLASS, PassengerClass.ECONOMY_CLASS);
                passenger = passengerService.createPassenger(flightInformation, passengerDataMap);
            }
            if (PassengerClass.ECONOMY_CLASS.name().equalsIgnoreCase(passenger.getType().name())
                    && (flightPassengerService.numberOfEconomyClassSeatsAvailable() == 0)) {
                System.out.println("Economy is Full. Try another type...");
                return null;
            }
            flightPassengerService.addPassenger(passenger);
            System.out.println("Passenger added successfully");
            return passenger;
        } else {
            System.out.println("Flight is Full...");
            return null;
        }
    }

    private Map<PassengerKeyConstants, Object> getPassengerKeyConstantsObjectMap(Passenger passenger) {
        Map<PassengerKeyConstants, Object> passengerDataMap = new EnumMap<>(PassengerKeyConstants.class);
        passengerDataMap.put(PassengerKeyConstants.PASSENGER_PASSPORT, passenger.getPassport());
        passengerDataMap.put(PassengerKeyConstants.PASSENGER_NAME, passenger.getName());
        passengerDataMap.put(PassengerKeyConstants.PASSENGER_AGE, passenger.getAge());
        return passengerDataMap;
    }
}
