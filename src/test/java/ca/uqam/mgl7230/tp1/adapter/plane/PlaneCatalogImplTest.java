package ca.uqam.mgl7230.tp1.adapter.plane;

import ca.uqam.mgl7230.tp1.model.plane.PlaneInformation;
import ca.uqam.mgl7230.tp1.model.plane.PlaneType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlaneCatalogImplTest {

    private static final PlaneType BOEING = PlaneType.BOEING;
    private final PlaneInformation boeingInformation = new PlaneInformation(2, 5, 12);
    private final PlaneCatalog planeCatalog = new PlaneCatalogImpl();

    @Test
    void getNumberSeatsFirstClassReturnsNumberSeats() {
        int expectedSeats = boeingInformation.numberSeatsFirstClass();

        int actualSeats = planeCatalog.getNumberSeatsFirstClass(BOEING);

        assertThat(actualSeats).isEqualTo(expectedSeats);
    }

    @Test
    void getNumberSeatsBusinessClass() {
        int expectedSeats = boeingInformation.numberSeatsBusinessClass();

        int actualSeats = planeCatalog.getNumberSeatsBusinessClass(BOEING);

        assertThat(actualSeats).isEqualTo(expectedSeats);
    }

    @Test
    void getNumberSeatsEconomyClass() {
        int expectedSeats = boeingInformation.numberSeatsEconomyClass();

        int actualSeats = planeCatalog.getNumberSeatsEconomyClass(BOEING);

        assertThat(actualSeats).isEqualTo(expectedSeats);
    }

    @Test
    void getNumberOfTotalSeats() {
        int expectedSeats = boeingInformation.numberSeatsFirstClass() +
                boeingInformation.numberSeatsBusinessClass() +
                boeingInformation.numberSeatsEconomyClass();

        int actualSeats = planeCatalog.getNumberOfTotalSeats(BOEING);

        assertThat(actualSeats).isEqualTo(expectedSeats);
    }
}