package ir.hsnprsd.bomberman.models.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.utils.Cell;

public abstract class Sprite {
    protected Game game;
    protected Type type;
    protected Cell cell;

    Sprite(Game game, Type type, Cell cell) {
        this.game = game;
        this.type = type;
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    protected void setX(int x) {
        cell.setX(x);
    }

    public int getY() {
        return cell.getY();
    }

    protected void setY(int y) {
        cell.setY(y);
    }

    public enum Type {
        PLAYER, ENEMY, BLOCK
    }
}
