package ca.uqam.mgl7230.tp1.adapter.persist;

import ca.uqam.mgl7230.tp1.model.passenger.Passenger;
import ca.uqam.mgl7230.tp1.model.passenger.PassengerClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SavePassengerInFlightTest {

    private static final String FLIGHT_NUMBER = "flightNumber";
    private static final String PASSPORT = "passport";
    private static final String NAME = "name";
    private static final int AGE = 22;
    private static final PassengerClass CLASS = PassengerClass.BUSINESS_CLASS;
    private static final int MILLEAGE = 1062;

    private FileWriter fw;
    @Mock
    private Passenger passenger;

    private final SavePassengerInFlight savePassengerInFlight = new SavePassengerInFlight();

    @BeforeEach
    void setUp() throws IOException {
        fw = new FileWriter("test");

        given(passenger.getPassport()).willReturn(PASSPORT);
        given(passenger.getName()).willReturn(NAME);
        given(passenger.getAge()).willReturn(AGE);
        given(passenger.getType()).willReturn(CLASS);
        given(passenger.getMillagePoints()).willReturn(MILLEAGE);
    }

    @Test
    void saveWritesInFile() throws IOException {
        String expectedContent = FLIGHT_NUMBER + ',' + PASSPORT + ',' + NAME + ',' + AGE + ',' + CLASS + ',' + MILLEAGE;

        savePassengerInFlight.save(fw, passenger, FLIGHT_NUMBER);
        fw.close();

        FileReader fr = new FileReader("test");
        BufferedReader br = new BufferedReader(fr);
        String actualContent = br.readLine();
        br.close();
        fr.close();

        assertThat(actualContent).isEqualTo(expectedContent);
    }

    @AfterEach
    void tearDown() {
        File f = new File("test");
        f.delete();
    }
}