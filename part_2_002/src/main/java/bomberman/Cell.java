package bomberman;

public final class Cell {
    private final int x;
    private final int y;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return String.format("Cell{x=%d, y=%d}", x, y);
    }
}
