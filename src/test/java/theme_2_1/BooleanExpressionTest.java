package theme_2_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class BooleanExpressionTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/theme_2_1/BooleanExpression/data.csv")
    void testCsv(boolean a, boolean b, boolean c, boolean d, boolean expected) {
        var actual = BooleanExpression.booleanExpression(a, b, c, d);
        Assertions.assertEquals(expected, actual);
    }
}
