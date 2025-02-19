package ca.uqam.mgl7230.tp1.service.prompt;

import ca.uqam.mgl7230.tp1.model.passenger.PassengerClass;
import ca.uqam.mgl7230.tp1.model.passenger.PassengerKeyConstants;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PassengerPromptServiceTest {

    private static final String PASSPORT = "passport";
    private static final String NAME = "name";
    private static final int AGE = 22;
    private static final int AGE_NEGATIVE = -12;
    private static final String AGE_NAN = "not a number";
    private static final String FIRST_CLASS = "first";
    private static final String BUSINESS_CLASS = "business";
    private static final String ECONOMY_CLASS = "economy";
    private static final String NOT_A_CLASS = "other";

    private final PassengerPromptService passengerPromptService = new PassengerPromptService();

    @Test
    void getPassengerDataReturnsFirstClassPassengerData() {
        String input =
                PASSPORT + "\n" +
                NAME + "\n" +
                AGE_NAN + "\n" +
                AGE_NEGATIVE + "\n" +
                AGE + "\n" +
                NOT_A_CLASS + "\n" +
                FIRST_CLASS;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        Map<PassengerKeyConstants, Object> expectedData =
                Map.of(PassengerKeyConstants.PASSENGER_PASSPORT, PASSPORT,
                        PassengerKeyConstants.PASSENGER_NAME, NAME,
                        PassengerKeyConstants.PASSENGER_AGE, AGE,
                        PassengerKeyConstants.PASSENGER_CLASS, PassengerClass.FIRST_CLASS);

        Map<PassengerKeyConstants, Object> returnedData = passengerPromptService.getPassengerData(scanner);

        assertThat(returnedData).isEqualTo(expectedData);
    }

    @Test
    void getPassengerDataReturnsBusinessClassPassengerData() {
        String input = PASSPORT + "\n" +
                NAME + "\n" +
                AGE_NAN + "\n" +
                AGE_NEGATIVE + "\n" +
                AGE + "\n" +
                NOT_A_CLASS + "\n" +
                BUSINESS_CLASS;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        Map<PassengerKeyConstants, Object> expectedData =
                Map.of(PassengerKeyConstants.PASSENGER_PASSPORT, PASSPORT,
                        PassengerKeyConstants.PASSENGER_NAME, NAME,
                        PassengerKeyConstants.PASSENGER_AGE, AGE,
                        PassengerKeyConstants.PASSENGER_CLASS, PassengerClass.BUSINESS_CLASS);

        Map<PassengerKeyConstants, Object> returnedData = passengerPromptService.getPassengerData(scanner);

        assertThat(returnedData).isEqualTo(expectedData);
    }

    @Test
    void getPassengerDataReturnsEconomyClassPassengerData() {
        String input = PASSPORT + "\n" +
                NAME + "\n" +
                AGE_NAN + "\n" +
                AGE_NEGATIVE + "\n" +
                AGE + "\n" +
                NOT_A_CLASS + "\n" +
                ECONOMY_CLASS;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        Map<PassengerKeyConstants, Object> expectedData =
                Map.of(PassengerKeyConstants.PASSENGER_PASSPORT, PASSPORT,
                        PassengerKeyConstants.PASSENGER_NAME, NAME,
                        PassengerKeyConstants.PASSENGER_AGE, AGE,
                        PassengerKeyConstants.PASSENGER_CLASS, PassengerClass.ECONOMY_CLASS);

        Map<PassengerKeyConstants, Object> returnedData = passengerPromptService.getPassengerData(scanner);

        assertThat(returnedData).isEqualTo(expectedData);
    }
}