package theme_3_3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class MoveRobotTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/theme_3_3/MoveRobot/data.csv", delimiter = ' ')
    void testCsv(int x, int y) {
        Robot robot = new Robot();

        MoveRobot.moveRobot(robot, x, y);

        Assertions.assertAll(
                () -> Assertions.assertEquals(x, robot.getX()),
                () -> Assertions.assertEquals(y, robot.getY())
        );
    }
}