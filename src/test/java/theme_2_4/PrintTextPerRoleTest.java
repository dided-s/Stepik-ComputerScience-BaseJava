package theme_2_4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tools.InputOutputProvider;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PrintTextPerRoleTest {
    @ParameterizedTest
    @MethodSource("arguments")
    void testPath(String[] roles, String[] textLines, String expected) {
        var actual = PrintTextPerRole.printTextPerRole(roles, textLines);
        Assertions.assertEquals(expected.trim(), actual.trim());
    }

    private static Stream<Arguments> arguments() {
        return InputOutputProvider.pathArguments("theme_2_4", "PrintTextPerRole")
                .map(arguments -> {
                    Path inputPath = Path.of(arguments.get()[0].toString());
                    Path outputPath = Path.of(arguments.get()[1].toString());
                    List<String> roles = new ArrayList<>();
                    List<String> textLines = new ArrayList<>();

                    try (var inputBufferedReader = Files.newBufferedReader(inputPath)) {
                        String line = inputBufferedReader.readLine();
                        if (!line.trim().contentEquals("roles:"))
                            throw new AssertionError("Expected 'roles:' line but got " + line);

                        while (true) {
                            line = inputBufferedReader.readLine();
                            if (line == null)
                                throw new AssertionError("Expected 'textLines:' but got " + line);
                            if (line.trim().contentEquals("textLines:"))
                                break;
                            roles.add(line);
                        }

                        while (true) {
                            line = inputBufferedReader.readLine();
                            if (line == null) break;
                            textLines.add(line);
                        }

                        return Arguments.of(
                                roles.toArray(String[]::new),
                                textLines.toArray(String[]::new),
                                Files.readString(outputPath));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}