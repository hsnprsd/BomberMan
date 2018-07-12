package ir.hsnprsd.bomberman.models.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.utils.Cell;

public class Enemy extends MoverSprite {
    public Enemy(Game game, Cell cell) {
        super(game, Type.ENEMY, cell);
    }
}
