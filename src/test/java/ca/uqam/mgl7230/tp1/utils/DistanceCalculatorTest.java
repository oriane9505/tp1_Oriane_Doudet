package ca.uqam.mgl7230.tp1.utils;

import ca.uqam.mgl7230.tp1.model.flight.FlightInformation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DistanceCalculatorTest {

    @Mock
    FlightInformation flightInformation;

    DistanceCalculator distanceCalculator = new DistanceCalculator();

    @Test
    void calculateReturnsRealDistance() {
        //Given
        given(flightInformation.latSource()).willReturn(22.0);
        given(flightInformation.lonSource()).willReturn(123.0);
        given(flightInformation.latDestination()).willReturn(22.0);
        given(flightInformation.lonDestination()).willReturn(123.0);

        //When
        int distanceReturned = distanceCalculator.calculate(flightInformation);

        //Then
        assertThat(distanceReturned).isZero();
    }
}