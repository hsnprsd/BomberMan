package ir.hsnprsd.bomberman.models.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.Settings;
import ir.hsnprsd.bomberman.models.geo.Direction;
import ir.hsnprsd.bomberman.models.geo.Position;

public class Bomb extends Sprite {
    private State state = State.EXPLODING;

    public Bomb(Game game, Position position) {
        super(game, Type.BOMB, position);

        new Thread(() -> {
            try {
                Thread.sleep(Settings.Bomb.TIMEOUT);
                explode();
            } catch (InterruptedException ignored) {
            }
        }).start();
    }

    private void explode() {
        synchronized (game) {
            for (Direction direction : Direction.values()) {
                int dx = direction.dx;
                int dy = direction.dy;
                Position position = getPosition().move(dx, dy);
                game.destroySprites(position);
            }
        }
        setState(State.EXPLODED);
    }

    public State getState() {
        return state;
    }

    private void setState(State state) {
        this.state = state;
    }

    public enum State {
        EXPLODING, EXPLODED
    }
}
