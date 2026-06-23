package theme_2_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class DoubleExpressionTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/theme_2_1/DoubleExpression/data.csv", delimiter = ' ')
    void testCsv(double a, double b, double c, boolean expected) {
        var actual = DoubleExpression.doubleExpression(a, b, c);
        Assertions.assertEquals(expected, actual);
    }
}