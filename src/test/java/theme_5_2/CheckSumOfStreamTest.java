package theme_5_2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.stream.Stream;

public class CheckSumOfStreamTest {

    @ParameterizedTest
    @MethodSource("arguments")
    void testArguments(byte[] bytes, int expected) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes)) {
            int actual = CheckSumOfStream.checkSumOfStream(bais);
            Assertions.assertEquals(expected, actual);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new byte[]{0x33, 0x45, 0x01}, 71),
                Arguments.of(new byte[0], 0)
        );
    }
}