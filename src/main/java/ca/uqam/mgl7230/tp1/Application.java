package ca.uqam.mgl7230.tp1;

import ca.uqam.mgl7230.tp1.adapter.flight.FlightCatalog;
import ca.uqam.mgl7230.tp1.adapter.flight.FlightCatalogImpl;
import ca.uqam.mgl7230.tp1.adapter.persist.SavePassengerInFlight;
import ca.uqam.mgl7230.tp1.adapter.plane.PlaneCatalog;
import ca.uqam.mgl7230.tp1.adapter.plane.PlaneCatalogImpl;
import ca.uqam.mgl7230.tp1.config.FileWriterProvider;
import ca.uqam.mgl7230.tp1.model.passenger.Passenger;
import ca.uqam.mgl7230.tp1.model.passenger.PassengerKeyConstants;
import ca.uqam.mgl7230.tp1.service.BookingService;
import ca.uqam.mgl7230.tp1.service.FlightPassengerService;
import ca.uqam.mgl7230.tp1.service.PassengerService;
import ca.uqam.mgl7230.tp1.service.prompt.FlightPromptService;
import ca.uqam.mgl7230.tp1.service.prompt.PassengerPromptService;
import ca.uqam.mgl7230.tp1.utils.DistanceCalculator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws IOException {
        System.out.println("Service started...");
        Initialize result = initialize();
        boolean shouldContinue = true;
        while (shouldContinue) {
            PassengerService passengerService = new PassengerService(result.distanceCalculator());
            PassengerPromptService passengerPromptService = new PassengerPromptService();
            Map<PassengerKeyConstants, Object> passengerData =
                    passengerPromptService.getPassengerData(result.scanner());
            Passenger passenger = passengerService.createPassenger(
                    result.flightCatalog().getFlightInformation(result.flightNumber()), passengerData);
            BookingService bookingService = new BookingService(result.flightPassengerService(), passengerService);
            passenger = bookingService.book(passenger, result.flightCatalog().getFlightInformation(result.flightNumber()));
            if (passenger != null) {
                result.savePassengerInFlight().save(result.file(), passenger, result.flightNumber());
            }
            System.out.println("Seats available: " + result.flightPassengerService().numberOfTotalSeatsAvailable());
            System.out.println("Continue adding passengers to this flight? yes or no");
            String continueChoice = result.scanner().nextLine();
            if ("no".equalsIgnoreCase(continueChoice)) {
                shouldContinue = false;
                result.scanner().close();
                result.file().close();
            }
        }
    }

    private static Initialize initialize() throws IOException {
        DistanceCalculator distanceCalculator = new DistanceCalculator();
        Scanner scanner = new Scanner(System.in);
        PlaneCatalog planeCatalog = new PlaneCatalogImpl();
        FlightCatalog flightCatalog = new FlightCatalogImpl();
        FileWriterProvider fileWriterProvider = new FileWriterProvider();
        FileWriter file = fileWriterProvider.createFile("passengerData.csv");
        file.flush();
        SavePassengerInFlight savePassengerInFlight = new SavePassengerInFlight();
        FlightPromptService flightPromptService = new FlightPromptService(flightCatalog);
        String flightNumber = flightPromptService.getFlightInformation(scanner).flightNumber();
        FlightPassengerService flightPassengerService = new FlightPassengerService(planeCatalog, flightCatalog, flightNumber);
        return new Initialize(distanceCalculator, scanner, flightCatalog, file,
                savePassengerInFlight, flightNumber, flightPassengerService);
    }

    private record Initialize(DistanceCalculator distanceCalculator, Scanner scanner, FlightCatalog flightCatalog,
                              FileWriter file, SavePassengerInFlight savePassengerInFlight,
                              String flightNumber, FlightPassengerService flightPassengerService) {
    }
}
