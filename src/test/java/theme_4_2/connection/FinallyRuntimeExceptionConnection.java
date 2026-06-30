package theme_4_2.connection;

public class FinallyRuntimeExceptionConnection implements RobotConnection {

    @Override
    public void moveRobotTo(int x, int y) {
        System.out.println("Хороший метод без исключений");
    }

    @Override
    public void close() {
        throw new RuntimeException("close Runtime с исключением");
    }
}