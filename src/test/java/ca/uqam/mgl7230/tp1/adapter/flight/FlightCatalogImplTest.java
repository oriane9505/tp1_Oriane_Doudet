package ca.uqam.mgl7230.tp1.adapter.flight;

import ca.uqam.mgl7230.tp1.model.flight.FlightInformation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FlightCatalogImplTest {

    private static final String VALID_FLIGHT_NUMBER = "UQAM001";
    private static final String INVALID_FLIGHT_NUMBER = "incorrect";

    private final FlightCatalog flightCatalog = new FlightCatalogImpl();

    @Test
    void getFlightInformationReturnsFlightInformation() {
        FlightInformation flightInformation = flightCatalog.getFlightInformation(VALID_FLIGHT_NUMBER);

        assertThat(flightInformation).isNotNull();
    }

    @Test
    void getFlightInformationReturnsNull() {
        FlightInformation flightInformation = flightCatalog.getFlightInformation(INVALID_FLIGHT_NUMBER);

        assertThat(flightInformation).isNull();
    }
}