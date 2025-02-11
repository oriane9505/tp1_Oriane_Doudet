package ca.uqam.mgl7230.tp1.service;

import ca.uqam.mgl7230.tp1.model.flight.FlightInformation;
import ca.uqam.mgl7230.tp1.model.passenger.Passenger;
import ca.uqam.mgl7230.tp1.model.passenger.PassengerClass;
import ca.uqam.mgl7230.tp1.model.passenger.PassengerKeyConstants;
import ca.uqam.mgl7230.tp1.utils.DistanceCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PassengerServiceTest {

    public static final String PASSPORT = "passport";
    public static final String NAME = "name";
    public static final int AGE = 21;
    public static final int DISTANCE = 10;

    @Mock
    private DistanceCalculator distanceCalculator;
    @Mock
    private FlightInformation flightInformation;
    @Mock
    private Map<PassengerKeyConstants, Object> passengerData;

    private PassengerService passengerService;

    @BeforeEach
    void setUp() {
        given(passengerData.get(PassengerKeyConstants.PASSENGER_PASSPORT)).willReturn(PASSPORT);
        given(passengerData.get(PassengerKeyConstants.PASSENGER_NAME)).willReturn(NAME);
        given(passengerData.get(PassengerKeyConstants.PASSENGER_AGE)).willReturn(AGE);

        passengerService = new PassengerService(distanceCalculator);
    }

    @Test
    void createPassengerFirstReturnsFirstClassPassenger() {
        //Given
        given(passengerData.get(PassengerKeyConstants.PASSENGER_CLASS)).willReturn(PassengerClass.FIRST_CLASS);

        //When
        Passenger passenger = passengerService.createPassenger(flightInformation, passengerData);

        //Then
        assertThat(passenger.getType()).isEqualTo(PassengerClass.FIRST_CLASS);
    }

    @Test
    void createPassengerBusinessReturnsBusinessClassPassenger() {
        //Given
        given(passengerData.get(PassengerKeyConstants.PASSENGER_CLASS)).willReturn(PassengerClass.BUSINESS_CLASS);

        //When
        Passenger passenger = passengerService.createPassenger(flightInformation, passengerData);

        //Then
        assertThat(passenger.getType()).isEqualTo(PassengerClass.BUSINESS_CLASS);
    }

    @Test
    void createPassengerEconomyReturnsEconomyClassPassenger() {
        //Given
        given(passengerData.get(PassengerKeyConstants.PASSENGER_CLASS)).willReturn(PassengerClass.ECONOMY_CLASS);

        //When
        Passenger passenger = passengerService.createPassenger(flightInformation, passengerData);

        //Then
        assertThat(passenger.getType()).isEqualTo(PassengerClass.ECONOMY_CLASS);
    }

    @Test
    void createPassengerOtherTypeReturnsNull() {
        //Given
        given(passengerData.get(PassengerKeyConstants.PASSENGER_CLASS)).willReturn("nothing");

        //When
        Passenger passenger = passengerService.createPassenger(flightInformation, passengerData);

        //Then
        assertNull(passenger);
    }
}