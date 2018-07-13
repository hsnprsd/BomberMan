package ir.hsnprsd.bomberman.models.geo;

public class Position {
    private int x1, x2, y1, y2;

    public Position(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public Position(int x, int y, int size) {
        x1 = x;
        y1 = y;
        x2 = x + size;
        y2 = y + size;
    }

    public Position move(int dx, int dy) {
        return new Position(x1 + dx, y1 + dy, x2 + dx, y2 + dy);
    }

    public boolean contains(Position o) {
        return x1 <= o.x1 && y1 <= o.y1 && o.x2 <= x2 && o.y2 <= y2;
    }

    public boolean intersects(Position o) {
        if (x1 <= o.x1) {
            if (x2 <= o.x1) {
                return false;
            } else {
                return intersects(y1, y2, o.y1, o.y2);
            }
        } else {
            return o.intersects(this);
        }
    }

    // check if [a1, b1) and [a2, b2) intersect
    private boolean intersects(int a1, int b1, int a2, int b2) {
        if (a1 <= a2) {
            return b1 > a2;
        } else {
            return b2 > a1;
        }
    }

    public int getX1() {
        return x1;
    }

    public Position setX1(int x1) {
        Position position = copy();
        position.x1 = x1;
        return position;
    }

    public int getX2() {
        return x2;
    }

    public Position setX2(int x2) {
        Position position = copy();
        position.x2 = x2;
        return position;
    }

    public int getY1() {
        return y1;
    }

    public Position setY1(int y1) {
        Position position = copy();
        position.y1 = y1;
        return position;
    }

    public int getY2() {
        return y2;
    }

    public Position setY2(int y2) {
        Position position = copy();
        position.y2 = y2;
        return position;
    }

    public Position copy() {
        return new Position(x1, y1, x2, y2);
    }
}
