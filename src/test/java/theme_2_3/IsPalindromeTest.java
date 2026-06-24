package theme_2_3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tools.InputOutputProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class IsPalindromeTest {

    @ParameterizedTest
    @MethodSource("arguments")
    void testPath(String text, boolean expected) {
        var actual = IsPalindrome.isPalindrome(text);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> arguments() {
        return InputOutputProvider.pathArguments("theme_2_3", "isPalindrome")
                .map(arguments -> {
                    Path inputPath = Path.of(arguments.get()[0].toString());
                    Path outputPath = Path.of(arguments.get()[1].toString());
                    try {
                        return Arguments.of(Files.readString(inputPath), Files.readString(outputPath));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}