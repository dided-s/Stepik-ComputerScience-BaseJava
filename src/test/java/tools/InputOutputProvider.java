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
        Path themePath = null;
        try {
            themePath = Path.of(ClassLoader.getSystemClassLoader().getResource(themeName).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Path inputsPath = themePath.resolve(taskName).resolve("inputs");
        Path outputsPath = themePath.resolve(taskName).resolve("outputs");

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
}