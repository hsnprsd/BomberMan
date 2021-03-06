package ir.hsnprsd.bomberman.models.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.Settings;
import ir.hsnprsd.bomberman.models.geo.Position;
import ir.hsnprsd.bomberman.models.sprites.interfaces.Destroyable;

public class Enemy extends MoverSprite {
    private State state = State.ALIVE;

    public Enemy(Game game, Position position) {
        super(game, Type.ENEMY, position, Settings.Enemy.SPEED);
    }
}
