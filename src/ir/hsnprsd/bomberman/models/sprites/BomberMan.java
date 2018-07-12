package ir.hsnprsd.bomberman.models.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.utils.Cell;

public class BomberMan extends MoverSprite {
    public BomberMan(Game game, Cell cell) {
        super(game, Type.PLAYER, cell);
    }
}
