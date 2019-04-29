public class Tractor {
    private final Field field;

    private Position position;
    private Orientation_old orientation;

    public Tractor(Position initialPosition, Field field, Orientation_old initialOrientation) {
        this.position = initialPosition;
        this.field = field;
        this.orientation = initialOrientation;
    }

    public void move(TractorCommands command) {
        switch (command) {
            case Forward:
                moveForwards();
                break;
            case TurnClockwise:
                turnClockwise();
                break;
            default:
                throw new RuntimeException("Undefined command");
        }
    }

    public void moveForwards() {
        switch (orientation) {
            case NORTH:
                position = new Position(position.getX(), position.getY() + 1);
                break;
            case EAST:
                position = new Position(position.getX() + 1, position.getY());
                break;
            case SOUTH:
                position = new Position(position.getX(), position.getY() - 1);
                break;
            case WEST:
                position = new Position(position.getX() - 1, position.getY());
                break;
        }
        checkCorrectNewPosition();
    }

    private void checkCorrectNewPosition() {
        if (position.getX() > field.getHeight() || position.getY() > field.getWidth()) {
            throw new TractorInDitchException_old();
        }
    }

    public void turnClockwise() {
        switch (orientation) {
            case NORTH:
                orientation = Orientation_old.EAST;
                break;
            case EAST:
                orientation = Orientation_old.SOUTH;
                break;
            case SOUTH:
                orientation = Orientation_old.WEST;
                break;
            case WEST:
                orientation = Orientation_old.NORTH;
                break;
        }
    }

    public int getPositionX() {
        return position.getX();
    }

    public int getPositionY() {
        return position.getY();
    }

    public Orientation_old getOrientation() {
        return orientation;
    }
}