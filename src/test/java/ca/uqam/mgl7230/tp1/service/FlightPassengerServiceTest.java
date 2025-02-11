package ca.uqam.mgl7230.tp1.service;

import ca.uqam.mgl7230.tp1.adapter.flight.FlightCatalog;
import ca.uqam.mgl7230.tp1.adapter.plane.PlaneCatalog;
import ca.uqam.mgl7230.tp1.model.flight.FlightInformation;
import ca.uqam.mgl7230.tp1.model.passenger.Passenger;
import ca.uqam.mgl7230.tp1.model.passenger.PassengerClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class FlightPassengerServiceTest {

    public static final String FLIGHT_NUMBER = "flightNumber";
    @Mock
    private PlaneCatalog planeCatalog;
    @Mock
    private FlightInformation flightInformation;
    @Mock
    private Passenger passenger;

    private FlightPassengerService flightPassengerService;

    @BeforeEach
    void setUp() {
        FlightCatalog flightCatalog = mock(FlightCatalog.class);
        given(flightCatalog.getFlightInformation(FLIGHT_NUMBER)).willReturn(flightInformation);

        given(planeCatalog.getNumberSeatsFirstClass(flightInformation.getPlaneType())).willReturn(1);
        given(planeCatalog.getNumberSeatsBusinessClass(flightInformation.getPlaneType())).willReturn(1);
        given(planeCatalog.getNumberSeatsEconomyClass(flightInformation.getPlaneType())).willReturn(1);

        flightPassengerService = new FlightPassengerService(planeCatalog, flightCatalog, FLIGHT_NUMBER);
    }

    @Test
    void addPassengerRemoveFirstClassSeat() {
        //Given
        given(passenger.getType()).willReturn(PassengerClass.FIRST_CLASS);

        //Adding 1 passenger when 1 seat is available
        flightPassengerService.addPassenger(passenger);
        assertThat(flightPassengerService.numberOfFirstClassSeatsAvailable()).isZero();

        //Adding 1 passenger when 0 seat is available
        flightPassengerService.addPassenger(passenger);
        assertThat(flightPassengerService.numberOfFirstClassSeatsAvailable()).isZero();

        assertThat(flightPassengerService.numberOfTotalSeatsAvailable()).isEqualTo(2);
    }

    @Test
    void addPassengerRemoveBusinessClassSeat() {
        //Given
        given(passenger.getType()).willReturn(PassengerClass.BUSINESS_CLASS);

        //Adding 1 passenger when 1 seat is available
        flightPassengerService.addPassenger(passenger);
        assertThat(flightPassengerService.numberOfBusinessClassSeatsAvailable()).isZero();

        //Adding 1 passenger when 0 seat is available
        flightPassengerService.addPassenger(passenger);
        assertThat(flightPassengerService.numberOfBusinessClassSeatsAvailable()).isZero();

        assertThat(flightPassengerService.numberOfTotalSeatsAvailable()).isEqualTo(2);
    }

    @Test
    void addPassengerRemoveEconomyClassSeat() {
        //Given
        given(passenger.getType()).willReturn(PassengerClass.ECONOMY_CLASS);

        //Adding 1 passenger when 1 seat is available
        flightPassengerService.addPassenger(passenger);
        assertThat(flightPassengerService.numberOfEconomyClassSeatsAvailable()).isZero();

        //Adding 1 passenger when 0 seat is available
        flightPassengerService.addPassenger(passenger);
        assertThat(flightPassengerService.numberOfEconomyClassSeatsAvailable()).isZero();

        assertThat(flightPassengerService.numberOfTotalSeatsAvailable()).isEqualTo(2);
    }
}
