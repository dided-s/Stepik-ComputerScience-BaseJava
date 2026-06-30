package theme_4_2;

import theme_4_2.connection.RobotConnection;
import theme_4_2.connection.RobotConnectionException;
import theme_4_2.connection.RobotConnectionManager;

public class MoveRobot {

    public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
        for (int num = 1; num <= 3; num++) {
            try (RobotConnection robotConnection = robotConnectionManager.getConnection()) {
                robotConnection.moveRobotTo(toX, toY);
                num = 4;
            } catch (RobotConnectionException e) {
                if (num == 3) throw e;
            }
        }
    }
}