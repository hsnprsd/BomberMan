package ir.hsnprsd.bomberman.models.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.geo.Direction;
import ir.hsnprsd.bomberman.models.geo.Position;

import java.util.List;

public abstract class MoverSprite extends Sprite {
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
        synchronized (game) {
            if (!move(nextDirection)) {
                if (!move(direction)) {
                    direction = null;
                }
            } else {
                direction = nextDirection;
                nextDirection = null;
            }
        }
    }

    private boolean move(Direction direction) {
        if (direction == null) {
            return false;
        }
        Position destination = position.move(direction.dx * speed, direction.dy * speed);
        if (!new Position(0, 0, game.getWidth(), game.getHeight()).contains(destination)) {
            return false;
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
        return valid;
    }
}
