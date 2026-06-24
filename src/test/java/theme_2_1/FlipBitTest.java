package theme_2_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class FlipBitTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/theme_2_1/FlipBit/data.csv", delimiter = ' ')
    void testCsv(int value, int bitIndex, int expected) {
        var actual = FlipBit.flipBit(value, bitIndex);
        Assertions.assertEquals(expected, actual);
    }
}