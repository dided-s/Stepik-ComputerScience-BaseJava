package theme_2_2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class IsPowerOfTwoTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/theme_2_2/IsPowerOfTwo/data.csv", delimiter = ' ')
    void testCsv(int value, boolean expected) {
        var actual = IsPowerOfTwo.isPowerOfTwo(value);
        Assertions.assertEquals(expected, actual);
    }
}