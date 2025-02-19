package ca.uqam.mgl7230.tp1.service.prompt;

import ca.uqam.mgl7230.tp1.exception.PassengerTypeNotFoundException;
import ca.uqam.mgl7230.tp1.model.passenger.PassengerClass;
import ca.uqam.mgl7230.tp1.model.passenger.PassengerKeyConstants;

import java.util.Map;
import java.util.Scanner;

public class PassengerPromptService {

    public Map<PassengerKeyConstants, Object> getPassengerData(Scanner scanner) {
        System.out.println("Enter Passenger passport: ");
        String passengerPassport = scanner.nextLine();
        System.out.println("Enter Passenger name: ");
        String passengerName = scanner.nextLine();
        System.out.println("Enter Passenger age: ");
        boolean validEntry = false;
        int passengerAge = -1;
        while (!validEntry) {
            try {
                passengerAge = Integer.parseInt(scanner.nextLine());
                if (passengerAge >= 0) {
                    validEntry = true;
                } else {
                    System.out.println("Passenger age must be a positive integer");
                }
            } catch (NumberFormatException e) {
                System.out.println("Passenger age must be a positive integer");
            }
        }
        System.out.println("Enter a passenger type: write first for First Class, " +
                "business for Business Class or " +
                "economy for Economy Class");
        validEntry = false;
        PassengerClass passengerClass = null;
        while (!validEntry) {
            try {
                passengerClass = getPassengerClass(scanner.nextLine());
                validEntry = true;
            } catch (PassengerTypeNotFoundException e) {
                System.out.println("Passenger type does not exist");
            }
        }

        return Map.of(PassengerKeyConstants.PASSENGER_PASSPORT, passengerPassport,
                PassengerKeyConstants.PASSENGER_NAME, passengerName,
                PassengerKeyConstants.PASSENGER_AGE, passengerAge,
                PassengerKeyConstants.PASSENGER_CLASS, passengerClass);
    }

    private static PassengerClass getPassengerClass(String passengerType) {
        switch (passengerType) {
            case "first" -> {
                return PassengerClass.FIRST_CLASS;
            }
            case "business" -> {
                return PassengerClass.BUSINESS_CLASS;
            }
            case "economy" -> {
                return PassengerClass.ECONOMY_CLASS;
            }
            default -> throw new PassengerTypeNotFoundException();
        }
    }
}
