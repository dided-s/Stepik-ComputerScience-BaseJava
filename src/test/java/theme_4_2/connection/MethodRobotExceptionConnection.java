package theme_4_2.connection;

public class MethodRobotExceptionConnection implements RobotConnection {

    @Override
    public void moveRobotTo(int x, int y) {
        throw new RobotConnectionException("Метод RobotConnectionException с исключением");
    }

    @Override
    public void close() {
        System.out.println("close без исключений");
    }
}