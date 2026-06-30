package theme_4_2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import theme_4_2.connection.*;
import theme_4_2.manager.MethodRobotExceptionCountManager;

import java.util.stream.Stream;

public class MoveRobotTest {

    @ParameterizedTest
    @MethodSource("argumentsGood")
    void testGood(RobotConnectionManager robotConnectionManager) {
        Assertions.assertDoesNotThrow(
                () -> MoveRobot.moveRobot(robotConnectionManager, 1, 1));
    }

    private static Stream<Arguments> argumentsGood() {
        return Stream.of(
                Arguments.of((RobotConnectionManager) () -> new GoodConnection()),
                Arguments.of(new MethodRobotExceptionCountManager(1)),
                Arguments.of(new MethodRobotExceptionCountManager(2))
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsException")
    void testArguments(RobotConnectionManager robotConnectionManager, String message) {
        Throwable e = Assertions.assertThrows(RuntimeException.class,
                () -> MoveRobot.moveRobot(robotConnectionManager, 1, 1));
        Assertions.assertEquals(message, e.getMessage());
    }


    private static Stream<Arguments> argumentsException() {
        return Stream.of(
                Arguments.of(
                        (RobotConnectionManager) () -> new MethodRuntimeExceptionConnection(),
                        "Метод Runtime с исключением"),
                Arguments.of(
                        (RobotConnectionManager) () -> new FinallyRuntimeExceptionConnection(),
                        "close Runtime с исключением"),
                Arguments.of(
                        (RobotConnectionManager) () -> new MethodRobotExceptionConnection(),
                        "Метод RobotConnectionException с исключением"),
                Arguments.of(new MethodRobotExceptionCountManager(3),
                        "Manager RobotConnectionException. Попытка 3")
        );
    }
}