package ir.hsnprsd.bomberman.models.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.geo.Position;

public abstract class Sprite {
    protected Type type;

    protected final Game game;
    protected Position position;

    Sprite(Game game, Type type, Position position) {
        this.type = type;

        this.game = game;
        this.position = position;
    }

    public Type getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public enum Type {
        PLAYER, ENEMY, BLOCK
    }
}
