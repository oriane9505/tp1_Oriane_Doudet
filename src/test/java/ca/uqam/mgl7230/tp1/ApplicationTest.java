package ca.uqam.mgl7230.tp1;

import ca.uqam.mgl7230.tp1.model.passenger.PassengerClass;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ApplicationTest {

    private static final String FLIGHT_NUMBER = "UQAM005";
    private static final String PASSPORT = "passport";
    private static final String NAME = "name";
    private static final int AGE = 22;
    private static final String FIRST_CLASS_PROMPT = "first";
    private static final PassengerClass BUSINESS = PassengerClass.BUSINESS_CLASS;
    private static final PassengerClass ECONOMY = PassengerClass.ECONOMY_CLASS;
    private static final String YES = "yes";
    private static final String NO = "no";
    private static final int MILLEAGE_UQAM005 = 5739;

    @Test
    void mainTest() throws IOException {
        new Application();
        InputStream in = getInputStream();
        System.setIn(in);

        String[] args = {};
        Application.main(args);

        String[] expectedData = {FLIGHT_NUMBER + ',' + PASSPORT + ',' + NAME + ',' + AGE + ',' + BUSINESS + ',' + MILLEAGE_UQAM005,
                FLIGHT_NUMBER + ',' + PASSPORT + ',' + NAME + ',' + AGE + ',' + ECONOMY + ',' + MILLEAGE_UQAM005,
                FLIGHT_NUMBER + ',' + PASSPORT + ',' + NAME + ',' + AGE + ',' + ECONOMY + ',' + MILLEAGE_UQAM005,
                FLIGHT_NUMBER + ',' + PASSPORT + ',' + NAME + ',' + AGE + ',' + ECONOMY + ',' + MILLEAGE_UQAM005,
                FLIGHT_NUMBER + ',' + PASSPORT + ',' + NAME + ',' + AGE + ',' + ECONOMY + ',' + MILLEAGE_UQAM005};

        FileReader fr = new FileReader("passengerData.csv");
        BufferedReader br = new BufferedReader(fr);
        for (String expectedDatum : expectedData) {
            assertThat(br.readLine()).isEqualTo(expectedDatum);
        }
        assertThat(br.readLine()).isNull();
        br.close();
        fr.close();
    }

    private static InputStream getInputStream() {
        String input = FLIGHT_NUMBER + '\n' +
                (PASSPORT + '\n' +
                        NAME + '\n' +
                        AGE + '\n' +
                        FIRST_CLASS_PROMPT + '\n' +
                        YES + '\n').repeat(5) +
                PASSPORT + '\n' +
                NAME + '\n' +
                AGE + '\n' +
                FIRST_CLASS_PROMPT + '\n' +
                NO;
        return new ByteArrayInputStream(input.getBytes());
    }
}