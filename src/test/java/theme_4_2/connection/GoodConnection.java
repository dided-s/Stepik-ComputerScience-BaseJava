package theme_4_2.connection;

public class GoodConnection implements RobotConnection {

    @Override
    public void moveRobotTo(int x, int y) {
        System.out.println("Хороший метод без исключений");
    }

    @Override
    public void close() {
        System.out.println("Хороший close без исключений");
    }
}