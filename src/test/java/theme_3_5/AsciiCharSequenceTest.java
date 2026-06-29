package theme_3_5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

public class AsciiCharSequenceTest {

    @ParameterizedTest
    @MethodSource("arguments")
    void testArguments(byte[] bytes, String expected) {
        AsciiCharSequence asciiCharSequence = new AsciiCharSequence(bytes);

        Assertions.assertEquals(expected.length(), asciiCharSequence.length());
        for (int i = 0; i < asciiCharSequence.length(); i++) {
            Assertions.assertEquals(expected.charAt(i), asciiCharSequence.charAt(i));
        }

        Random random = new Random();

        int beginIndex = random.nextInt(asciiCharSequence.length() - 1);
        int endIndex = random.nextInt(beginIndex, asciiCharSequence.length());

        Assertions.assertEquals(
                expected.subSequence(beginIndex, endIndex).toString(),
                asciiCharSequence.subSequence(beginIndex, endIndex).toString(),
                String.format("Substring [%d,%d) is incorrect", beginIndex, endIndex));

        Assertions.assertEquals(expected, asciiCharSequence.toString());
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new byte[]{65, 66, 67, 68, 69, 70, 71}, "ABCDEFG"),
                Arguments.of(new byte[]{72, 101, 108, 108, 111, 33}, "Hello!")
        );
    }
}