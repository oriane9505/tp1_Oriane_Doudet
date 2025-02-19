package ca.uqam.mgl7230.tp1.model.passenger;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BusinessClassPassengerTest {

    private static final String PASSPORT = "passport";
    private static final String NAME = "name";
    private static final int AGE = 22;
    private static final int MILLAGE_POINTS = 1062;

    private final BusinessClassPassenger businessClassPassenger =
            new BusinessClassPassenger(PASSPORT, NAME, AGE, MILLAGE_POINTS);

    @Test
    void getType() {
        assertThat(businessClassPassenger.getType()).isEqualTo(PassengerClass.BUSINESS_CLASS);
    }
}