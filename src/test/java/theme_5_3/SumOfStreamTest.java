package theme_5_3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.stream.Stream;

public class SumOfStreamTest {

    @ParameterizedTest
    @MethodSource("arguments")
    void testArguments(InputStream inputStream, double expected) {
        double actual = SumOfStream.sumOfStream(inputStream);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> arguments() {

        return Stream.of(
                Arguments.of(new ByteArrayInputStream("1 2 3".getBytes()), 6.000000),
                Arguments.of(new ByteArrayInputStream("a1 b2 c3".getBytes()), 0.000000),
                Arguments.of(new ByteArrayInputStream("""
                        -1e3
                        18 .111 11bbb
                        """.getBytes()), -981.889000));
    }
}
