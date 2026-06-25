package theme_3_3;

public class Robot {
    private int x;
    private int y;
    private Direction direction;

    public Robot() {
        this.x = 0;
        this.y = 0;
        this.direction = Direction.UP;

    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void turnLeft() {
        System.out.print(this.direction + " -> ");

        switch (this.direction) {
            case UP:
                this.direction = Direction.LEFT;
                break;
            case LEFT:
                this.direction = Direction.DOWN;
                break;
            case DOWN:
                this.direction = Direction.RIGHT;
                break;
            case RIGHT:
                this.direction = Direction.UP;
                break;
        }

        System.out.println(this.direction + " (Поворот против часовой стрелки)");
    }

    public void turnRight() {
        System.out.print(this.direction + " -> ");

        switch (this.direction) {
            case UP:
                this.direction = Direction.RIGHT;
                break;
            case RIGHT:
                this.direction = Direction.DOWN;
                break;
            case DOWN:
                this.direction = Direction.LEFT;
                break;
            case LEFT:
                this.direction = Direction.UP;
                break;
        }
        System.out.println(this.direction + " (Поворот по часовой стрелке)");
    }

    public void stepForward() {
        System.out.printf("движение %s (%d,%d) -> ", this.direction, this.x, this.y);
        switch (this.direction) {
            case UP:
                this.y++;
                break;
            case DOWN:
                this.y--;
                break;
            case LEFT:
                this.x--;
                break;
            case RIGHT:
                this.x++;
                break;
        }
        System.out.printf("(%d,%d)\n", this.x, this.y);
    }
}