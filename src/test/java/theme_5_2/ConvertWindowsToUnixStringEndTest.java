package theme_5_2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

public class ConvertWindowsToUnixStringEndTest {

    @ParameterizedTest
    @MethodSource("arguments")
    void testArguments(byte[] bytes, byte[] expected) {

        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ConvertWindowsToUnixStringEnd.convertWindowsToUnixStringEnd(bis, bos);

            byte[] actual = bos.toByteArray();
            Assertions.assertArrayEquals(expected, actual,
                    String.format("""
                            \nExpected: %s
                            Actual:   %s
                            """, Arrays.toString(expected), Arrays.toString(actual)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new byte[]{65, 13, 10, 10, 13}, new byte[]{65, 10, 10, 13}),
                Arguments.of(new byte[]{13, 10}, new byte[]{10}),
                Arguments.of(new byte[]{10}, new byte[]{10}),
                Arguments.of(new byte[]{13}, new byte[]{13}),
                Arguments.of(new byte[0], new byte[0]),
                Arguments.of(new byte[]{13, 13, 13, 10, 13, 13}, new byte[]{13, 13, 10, 13, 13})
        );
    }
}