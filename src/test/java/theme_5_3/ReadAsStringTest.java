package theme_5_3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

public class ReadAsStringTest {

    @ParameterizedTest
    @MethodSource("arguments")
    void testArguments(byte[] bytes, Charset charset, String expected) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes)) {
            var actual = ReadAsString.readAsString(bais, charset);
            Assertions.assertEquals(expected, actual);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new byte[]{48, 49, 50, 51}, StandardCharsets.US_ASCII, "0123")
        );
    }
}