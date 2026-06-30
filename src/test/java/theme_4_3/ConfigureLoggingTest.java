package theme_4_3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tools.Utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class ConfigureLoggingTest {

    @ParameterizedTest
    @MethodSource("argumentsExist")
    void testArgumentsExist(Logger logger, Level level, String message) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream oldOut = System.err;

        try {
            System.setErr(new PrintStream(baos));
            ConfigureLogging.configureLogging();
            logger.log(level, message);
        } finally {
            System.setErr(oldOut);
        }

        String captured = baos.toString();
        System.out.println(captured);

        int count = Utils.textNonCrossedContainsCount(captured, message);
        Assertions.assertEquals(1, count, "Incorrect count of message for " + message + "\n" + captured);
    }

    private static Stream<Arguments> argumentsExist() {
        Logger loggerParent = Logger.getLogger("org.stepic");
        loggerParent.setLevel(Level.ALL);
        loggerParent.addHandler(new ConsoleHandler());

        return Stream.of(
                Arguments.of(
                        Logger.getLogger("org.stepic.java.logging.ClassA"),
                        Level.INFO,
                        "Message must exist"),
                Arguments.of(
                        Logger.getLogger("org.stepic.java.logging.ClassA"),
                        Level.FINE,
                        "Message must exist"),
                Arguments.of(
                        Logger.getLogger("org.stepic.java.logging.ClassB"),
                        Level.WARNING,
                        "Message must exist"),
                Arguments.of(
                        Logger.getLogger("org.stepic.java"),
                        Level.WARNING,
                        "Message must exist")
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsNotExist")
    void testArgumentsNotExist(Logger logger, Level level, String message) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream oldOut = System.err;

        try {
            System.setErr(new PrintStream(baos));
            ConfigureLogging.configureLogging();
            logger.log(level, message);
        } finally {
            System.setErr(oldOut);
        }

        String captured = baos.toString();
        System.out.println(captured);

        int count = Utils.textNonCrossedContainsCount(captured, message);
        Assertions.assertEquals(0, count, "Incorrect count of message for " + message + "\n" + captured);
    }

    private static Stream<Arguments> argumentsNotExist() {

        return Stream.of(
                Arguments.of(
                        Logger.getLogger("org.stepic.java.logging.ClassB"),
                        Level.INFO,
                        "Message must NOT exist")
        );
    }
}