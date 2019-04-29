public class Tractor_old {

    int[] position = new int[] { 0, 0 };
    int[] field = new int[] { 5, 5 };
    Orientation_old orientation = Orientation_old.NORTH;

    public void move(String command) {
        if (command == "F") {
            moveForwards();
        } else if (command == "T") {
            turnClockwise();
        }
    }

    public void moveForwards() {
        if (orientation == Orientation_old.NORTH) {
            position = new int[] { position[0], position[1] + 1 };
        } else if (orientation == Orientation_old.EAST) {
            position = new int[] { position[0] + 1, position[1] };
        } else if (orientation == Orientation_old.SOUTH) {
            position = new int[] { position[0], position[1] - 1 };
        } else if (orientation == Orientation_old.WEST) {
            position = new int[] { position[0] - 1, position[1] };
        }
        if (position[0] > field[0] || position[1] > field[1]) {
            throw new TractorInDitchException_old();
        }
    }

    public void turnClockwise() {
        if (orientation == Orientation_old.NORTH) {
            orientation = Orientation_old.EAST;
        } else if (orientation == Orientation_old.EAST) {
            orientation = Orientation_old.SOUTH;
        } else if (orientation == Orientation_old.SOUTH) {
            orientation = Orientation_old.WEST;
        } else if (orientation == Orientation_old.WEST) {
            orientation = Orientation_old.NORTH;
        }
    }

    public int getPositionX() {
        return position[0];
    }

    public int getPositionY() {
        return position[1];
    }

    public Orientation_old getOrientation() {
        return orientation;
    }

}
