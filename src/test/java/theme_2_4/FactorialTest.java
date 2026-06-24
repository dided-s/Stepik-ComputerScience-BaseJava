package theme_2_4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigInteger;

public class FactorialTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/theme_2_4/Factorial/data.csv", delimiter = ' ')
    void testCsv(int value, BigInteger expected) {
        var actual = Factorial.factorial(value);
        Assertions.assertEquals(expected, actual);
    }
}