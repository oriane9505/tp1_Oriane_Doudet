package ca.uqam.mgl7230.tp1.service.prompt;

import ca.uqam.mgl7230.tp1.adapter.flight.FlightCatalog;
import ca.uqam.mgl7230.tp1.model.flight.FlightInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class FlightPromptServiceTest {

    private static final String FLIGHT_NUMBER = "flightNumber";

    @Mock
    private FlightCatalog flightCatalog;
    @Mock
    private Scanner scanner;
    @InjectMocks
    private FlightPromptService flightPromptService;

    @BeforeEach
    void setUp() {
        given(scanner.nextLine()).willReturn(FLIGHT_NUMBER);
    }

    @Test
    void getFlightInformationReturnsFlightInformation() {
        //Given
        FlightInformation expectedFlightInformation = mock(FlightInformation.class);
        given(flightCatalog.getFlightInformation(FLIGHT_NUMBER)).willReturn(expectedFlightInformation);

        //When
        FlightInformation returnedFlightInformation = flightPromptService.getFlightInformation(scanner);

        //Then
        assertThat(returnedFlightInformation).isEqualTo(expectedFlightInformation);
    }

    @Test
    void getFlightInformationReturnsNull() {
        //Given
        given(flightCatalog.getFlightInformation(FLIGHT_NUMBER)).willReturn(null);

        //When
        FlightInformation returnedFlightInformation = flightPromptService.getFlightInformation(scanner);

        //Then
        assertThat(returnedFlightInformation).isNull();
    }
}