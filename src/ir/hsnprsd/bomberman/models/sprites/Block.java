package ir.hsnprsd.bomberman.models.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.utils.Cell;

import java.util.Random;

public class Block extends Sprite {
    private int type;

    public Block(Game game, Cell cell) {
        super(game, Type.BLOCK, cell);

        Random random = new Random();
        type = random.nextInt(2);
    }

    public int getType() {
        return type;
    }
}
