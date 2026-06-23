package theme_2_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class LeapYearCountTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/theme_2_1/LeapYearCount/data.csv", delimiter = ' ')
    void testCsv(int year, int expected) {
        var actual = LeapYearCount.leapYearCount(year);
        Assertions.assertEquals(expected, actual);
    }
}