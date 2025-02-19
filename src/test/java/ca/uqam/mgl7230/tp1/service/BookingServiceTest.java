package ca.uqam.mgl7230.tp1.service;

import ca.uqam.mgl7230.tp1.adapter.flight.FlightCatalog;
import ca.uqam.mgl7230.tp1.adapter.plane.PlaneCatalog;
import ca.uqam.mgl7230.tp1.adapter.plane.PlaneCatalogImpl;
import ca.uqam.mgl7230.tp1.model.flight.FlightInformation;
import ca.uqam.mgl7230.tp1.model.passenger.Passenger;
import ca.uqam.mgl7230.tp1.model.passenger.PassengerClass;
import ca.uqam.mgl7230.tp1.model.plane.PlaneType;
import ca.uqam.mgl7230.tp1.utils.DistanceCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    public static final String FLIGHT_NUMBER = "flightNumber";
    public static final PlaneType PLANE_TYPE = PlaneType.BOEING;

    FlightPassengerService flightPassengerService;
    PassengerService passengerService;
    @Mock
    Passenger passenger;
    @Mock
    FlightInformation flightInformation;
    @Mock
    DistanceCalculator distanceCalculator;

    BookingService bookingService;

    @BeforeEach
    void setUp() {
        PlaneCatalog planeCatalog = new PlaneCatalogImpl();
        FlightCatalog flightCatalog = mock(FlightCatalog.class);

        given(flightInformation.planeType()).willReturn(PLANE_TYPE);
        given(flightCatalog.getFlightInformation(FLIGHT_NUMBER)).willReturn(flightInformation);

        flightPassengerService = new FlightPassengerService(planeCatalog, flightCatalog, FLIGHT_NUMBER);
        passengerService = new PassengerService(distanceCalculator);
        bookingService = new BookingService(flightPassengerService, passengerService);
    }

    @Test
    void bookFirstClassSeats() {
        //Given
        given(passenger.getType()).willReturn(PassengerClass.FIRST_CLASS);
        int firstClassSeats = flightPassengerService.numberOfFirstClassSeatsAvailable();
        int businessClassSeats = flightPassengerService.numberOfBusinessClassSeatsAvailable();

        //When
        Passenger returnedPassenger;
        for (int i = 0; i < firstClassSeats; i++) {
            returnedPassenger = bookingService.book(passenger, flightInformation);
            assertThat(flightPassengerService.numberOfFirstClassSeatsAvailable()).isEqualTo(firstClassSeats - i - 1);
            assertThat(returnedPassenger.getType()).isEqualTo(PassengerClass.FIRST_CLASS);
        }

        returnedPassenger = bookingService.book(passenger, flightInformation);

        //Then
        assertThat(flightPassengerService.numberOfFirstClassSeatsAvailable()).isZero();
        assertThat(flightPassengerService.numberOfBusinessClassSeatsAvailable()).isEqualTo(businessClassSeats - 1);
        assertThat(returnedPassenger.getType()).isEqualTo(PassengerClass.BUSINESS_CLASS);
    }

    @Test
    void bookBusinessClassSeats() {
        //Given
        given(passenger.getType()).willReturn(PassengerClass.BUSINESS_CLASS);
        int businessClassSeats = flightPassengerService.numberOfBusinessClassSeatsAvailable();
        int economyClassSeats = flightPassengerService.numberOfEconomyClassSeatsAvailable();

        //When
        Passenger returnedPassenger;
        for (int i = 0; i < businessClassSeats; i++) {
            returnedPassenger = bookingService.book(passenger, flightInformation);
            assertThat(flightPassengerService.numberOfBusinessClassSeatsAvailable()).isEqualTo(businessClassSeats - i - 1);
            assertThat(returnedPassenger.getType()).isEqualTo(PassengerClass.BUSINESS_CLASS);
        }

        returnedPassenger = bookingService.book(passenger, flightInformation);

        //Then
        assertThat(flightPassengerService.numberOfBusinessClassSeatsAvailable()).isZero();
        assertThat(flightPassengerService.numberOfEconomyClassSeatsAvailable()).isEqualTo(economyClassSeats - 1);
        assertThat(returnedPassenger.getType()).isEqualTo(PassengerClass.ECONOMY_CLASS);
    }

    @Test
    void bookEconomyClassSeats() {
        //Given
        given(passenger.getType()).willReturn(PassengerClass.ECONOMY_CLASS);
        int economyClassSeats = flightPassengerService.numberOfEconomyClassSeatsAvailable();
        int totalSeats = flightPassengerService.numberOfTotalSeatsAvailable();

        //When
        Passenger returnedPassenger;
        for (int i = 0; i < economyClassSeats; i++) {
            returnedPassenger = bookingService.book(passenger, flightInformation);
            assertThat(flightPassengerService.numberOfEconomyClassSeatsAvailable()).isEqualTo(economyClassSeats - i - 1);
            assertThat(returnedPassenger.getType()).isEqualTo(PassengerClass.ECONOMY_CLASS);
        }

        returnedPassenger = bookingService.book(passenger, flightInformation);

        //Then
        assertThat(flightPassengerService.numberOfEconomyClassSeatsAvailable()).isZero();
        assertThat(flightPassengerService.numberOfTotalSeatsAvailable()).isEqualTo(totalSeats - economyClassSeats);
        assertThat(returnedPassenger).isNull();
    }
}