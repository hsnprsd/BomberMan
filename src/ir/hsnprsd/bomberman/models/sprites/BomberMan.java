package ir.hsnprsd.bomberman.models.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.Settings;
import ir.hsnprsd.bomberman.models.geo.Position;

public class BomberMan extends MoverSprite {
    public BomberMan(Game game, Position position) {
        super(game, Type.BOMBERMAN, position, Settings.BomberMan.SPEED);
    }

    public void placeBomb() {
        synchronized (game) {
            int x = position.getX1() / game.getCellSize() * game.getCellSize();
            int y = position.getY1() / game.getCellSize() * game.getCellSize();
            game.getBombs().add(new Bomb(game, new Position(x, y, game.getCellSize())));
        }
    }
}
