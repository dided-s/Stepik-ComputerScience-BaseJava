package tools;

import org.junit.jupiter.params.provider.Arguments;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

public class InputOutputProvider {

    public static void main(String[] args) throws URISyntaxException, IOException {
        pathArguments("theme_2_1", "BooleanExpression")
                .forEach(arguments ->
                        System.out.println(Arrays.toString(arguments.get())));
    }

    public static Stream<Arguments> pathArguments(String themeName, String taskName) {
        Path taskPath = getTaskThemePath(themeName, taskName);

        Path inputsPath = taskPath.resolve("inputs");
        Path outputsPath = taskPath.resolve("outputs");

        try {
            return Files.walk(inputsPath)
                    .filter(Files::isRegularFile)
                    .map(input -> {
                        Path output = outputsPath.resolve(input.getFileName());
                        return Arguments.of(input, output);
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Path getTaskThemePath(String themeName, String taskName) {
        try {
            Path themePath = Path.of(ClassLoader.getSystemClassLoader().getResource(themeName).toURI());
            return themePath.resolve(taskName);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}