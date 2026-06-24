package theme_2_2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CharExpressionTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/theme_2_2/CharExpression/data.csv", delimiter = ' ')
    void testCsv(int a, char expected) {
        var actual = CharExpression.charExpression(a);
        Assertions.assertEquals(expected, actual);
    }
}