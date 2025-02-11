package ca.uqam.mgl7230.tp1.service;

import ca.uqam.mgl7230.tp1.adapter.flight.FlightCatalog;
import ca.uqam.mgl7230.tp1.adapter.plane.PlaneCatalog;
import ca.uqam.mgl7230.tp1.model.flight.FlightInformation;
import ca.uqam.mgl7230.tp1.model.passenger.Passenger;
import ca.uqam.mgl7230.tp1.model.passenger.PassengerClass;
import ca.uqam.mgl7230.tp1.model.plane.PlaneType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    FlightPassengerService flightPassengerService;
    @Mock
    PassengerService passengerService;
    @Mock
    Passenger passenger;
    @Mock
    FlightInformation flightInformation;

    BookingService bookingService;

    @BeforeEach
    void setUp() {
        PlaneCatalog planeCatalog = mock(PlaneCatalog.class);
        FlightCatalog flightCatalog = mock(FlightCatalog.class);
        flightPassengerService = new FlightPassengerService(planeCatalog, flightCatalog, "a");
        bookingService = new BookingService(flightPassengerService, passengerService);
    }

    @Test
    void bookFirstClassSeatsAvailable() {
        //Given
        given(passenger.getType()).willReturn(PassengerClass.FIRST_CLASS);
        given(flightPassengerService.numberOfFirstClassSeatsAvailable()).willReturn(1);

        //When
        bookingService.book(passenger, flightInformation);

        //Then
        assertThat(flightPassengerService.numberOfFirstClassSeatsAvailable()).isEqualTo(0);
    }
}