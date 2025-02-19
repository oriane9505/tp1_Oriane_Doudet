package ca.uqam.mgl7230.tp1.model.passenger;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PassengerTest {

    private static final String PASSPORT = "passport";
    private static final String NAME = "name";
    private static final int AGE = 22;
    private static final int MILLAGE_POINTS = 1062;

    private final Passenger passenger = new Passenger(PASSPORT, NAME, AGE, MILLAGE_POINTS) {
        public PassengerClass getType() {
            return null;
        }
    };

    @Test
    void getPassportReturnsPassport() {
        assertThat(passenger.getPassport()).isEqualTo(PASSPORT);
    }

    @Test
    void getNameReturnsName() {
        assertThat(passenger.getName()).isEqualTo(NAME);
    }

    @Test
    void getAgeReturnsAge() {
        assertThat(passenger.getAge()).isEqualTo(AGE);
    }

    @Test
    void getMillagePointsReturnsMillagePoints() {
        assertThat(passenger.getMillagePoints()).isEqualTo(MILLAGE_POINTS);
    }
}