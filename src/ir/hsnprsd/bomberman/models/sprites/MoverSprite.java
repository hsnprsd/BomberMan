package ir.hsnprsd.bomberman.models.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.geo.Direction;
import ir.hsnprsd.bomberman.models.geo.Position;
import ir.hsnprsd.bomberman.models.sprites.interfaces.Destroyable;

import java.util.List;

public abstract class MoverSprite extends Sprite implements Destroyable {
    protected State state;

    private int speed;
    private Direction direction, nextDirection;

    MoverSprite(Game game, Type type, Position position, int speed) {
        super(game, type, position);
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getNextDirection() {
        return nextDirection;
    }

    public void setNextDirection(Direction nextDirection) {
        this.nextDirection = nextDirection;
    }

    public void move() {
        if (state == State.KILLED) {
            throw new IllegalStateException();
        }
        synchronized (game) {
            if (moveToDirection(nextDirection)) {
                if (moveToDirection(direction)) {
                    direction = null;
                }
            } else {
                direction = nextDirection;
                nextDirection = null;
            }
        }
    }

    private boolean moveToDirection(Direction direction) {
        if (state == State.KILLED) {
            throw new IllegalStateException();
        }
        if (direction == null) {
            return true;
        }
        Position destination = position.move(direction.dx * speed, direction.dy * speed);
        if (!new Position(0, 0, game.getWidth(), game.getHeight()).contains(destination)) {
            return true;
        }
        List<Sprite> sprites = game.getSprites(destination);
        boolean valid = true;
        for (Sprite sprite : sprites) {
            if (sprite.type == Type.BLOCK) {
                valid = false;
            }
        }
        if (valid) {
            position = destination;
        }
        return !valid;
    }

    @Override
    public void destroy() {
        setState(State.KILLED);
    }

    public State getState() {
        return state;
    }

    private void setState(State state) {
        this.state = state;
    }

    public enum State {
        ALIVE, KILLED
    }
}
