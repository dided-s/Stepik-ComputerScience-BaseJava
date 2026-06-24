package theme_2_4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tools.InputOutputProvider;
import tools.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class MergeArraysTest {

    @ParameterizedTest
    @MethodSource("arguments")
    void testPath(int[] a1, int[] a2, int[] expected) {
        var actual = MergeArrays.mergeArrays(a1, a2);
        Assertions.assertArrayEquals(expected, actual);
    }

    private static Stream<Arguments> arguments() {
        return InputOutputProvider.pathArguments("theme_2_4", "MergeArrays")
                .map(arguments -> {
                    Path inputPath = Path.of(arguments.get()[0].toString());
                    Path outputPath = Path.of(arguments.get()[1].toString());
                    try (Stream<String> inputLines = Files.lines(inputPath)) {
                        var input = inputLines.map(Utils::parseIntArray)
                                .toArray();
                        var output = Utils.parseIntArray(Files.readString(outputPath));

                        return Arguments.of(input[0], input[1], output);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}