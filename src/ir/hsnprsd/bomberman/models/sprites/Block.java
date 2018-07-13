package ir.hsnprsd.bomberman.models.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.geo.Position;

public class Block extends Sprite {
    public Block(Game game, Position position) {
        super(game, Type.BLOCK, position);
    }
}
