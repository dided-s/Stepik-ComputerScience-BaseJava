package theme_4_2.connection;

public class MethodRuntimeExceptionConnection implements RobotConnection {

    @Override
    public void moveRobotTo(int x, int y) {
        throw new RuntimeException("Метод Runtime с исключением");
    }

    @Override
    public void close() {
        System.out.println("close без исключений");
    }
}