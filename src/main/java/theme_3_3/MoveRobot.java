package theme_3_3;

public class MoveRobot {

    public static void moveRobot(Robot robot, int toX, int toY) {
        int xIndex = getDirectionIndex(robot.getX() > toX ? Direction.LEFT : Direction.RIGHT);
        int yIndex = getDirectionIndex(robot.getY() > toY ? Direction.DOWN : Direction.UP);
        int robotIndex = getDirectionIndex(robot.getDirection());

        int dx = Math.abs(toX - robot.getX());
        int dy = Math.abs(toY - robot.getY());

        // Если Робот сначала повернут в правильную сторону по оси x или повернут в противоположную сторону по оси y,
        // то лучше сначала пойти по оси X, а потом по Y
        if (robotIndex == xIndex || isOpposite(robotIndex, yIndex)) {
            turnAndMove(robot, xIndex, dx);
            turnAndMove(robot, yIndex, dy);
        } else {
            turnAndMove(robot, yIndex, dy);
            turnAndMove(robot, xIndex, dx);
        }
    }

    private static boolean isOpposite(int aIndex, int bIndex) {
        int dd = bIndex - aIndex;
        return dd == 2 || dd == -2;
    }

    /**
     * Самый быстрый поворот в нужную сторону
     */
    private static void fastTurn(Robot robot, int directionIndex) {
        int robotIndex = getDirectionIndex(robot.getDirection());

        // Робот уже находится в правильном положении, поэтому идем дальше
        if (robotIndex == directionIndex) {
            return;
        }

        int dd = directionIndex - robotIndex;

        // Робот находится в противоположном направлении, поэтому тут нужно два поворота
        if (isOpposite(robotIndex, directionIndex)) {
            robot.turnLeft();
            robot.turnLeft();
            return;
        }

        if (dd == 1 || dd == -3) robot.turnRight();
        else robot.turnLeft();
    }

    private static void turnAndMove(Robot robot, int directionIndex, int d) {
        if (d == 0) return;

        fastTurn(robot, directionIndex);
        for (int i = 0; i < d; i++) {
            robot.stepForward();
        }
    }

    private static int getDirectionIndex(Direction direction) {
        switch (direction) {
            case UP:
                return 0;
            case RIGHT:
                return 1;
            case DOWN:
                return 2;
            case LEFT:
                return 3;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
    }
}