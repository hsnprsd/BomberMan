package ir.hsnprsd.bomberman.views.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.sprites.Block;
import ir.hsnprsd.bomberman.views.ResourceLoader;

import java.awt.image.BufferedImage;

public class BlockPainter extends SpritePainter {
    private final Game game;
    private final Block block;

    private BufferedImage staticImage;

    public BlockPainter(Game game, Block block) {
        super(game, block);
        this.game = game;
        this.block = block;

        staticImage = ResourceLoader.loadImage("block.png");
    }

    @Override
    protected BufferedImage getImage() {
        return staticImage;
    }
}
