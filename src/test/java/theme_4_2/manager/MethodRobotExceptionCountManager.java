package theme_4_2.manager;

import theme_4_2.connection.*;

public class MethodRobotExceptionCountManager implements RobotConnectionManager {

    private int count;

    public MethodRobotExceptionCountManager(int count) {
        this.count = count;
    }

    @Override
    public RobotConnection getConnection() {
        if (count > 0) {
            count--;
            throw new RobotConnectionException("Manager RobotConnectionException. Попытка " + (3 - count));
        }
        return new GoodConnection();
    }
}