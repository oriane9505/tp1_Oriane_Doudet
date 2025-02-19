package ca.uqam.mgl7230.tp1.model.passenger;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FirstClassPassengerTest {

    private static final String PASSPORT = "passport";
    private static final String NAME = "name";
    private static final int AGE = 22;
    private static final int MILLAGE_POINTS = 1062;

    private final FirstClassPassenger firstClassPassenger =
            new FirstClassPassenger(PASSPORT, NAME, AGE, MILLAGE_POINTS);

    @Test
    void getTypeReturnsFirstClass() {
        assertThat(firstClassPassenger.getType()).isEqualTo(PassengerClass.FIRST_CLASS);
    }
}