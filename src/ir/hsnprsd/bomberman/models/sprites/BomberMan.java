package ir.hsnprsd.bomberman.models.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.Settings;
import ir.hsnprsd.bomberman.models.geo.Position;

public class BomberMan extends MoverSprite {
    public BomberMan(Game game, Position position) {
        super(game, Type.PLAYER, position, Settings.BomberMan.SPEED);
    }

    public enum State {
        IDLE, WALKING
    }
}
