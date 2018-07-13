package ir.hsnprsd.bomberman.views.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.geo.Position;
import ir.hsnprsd.bomberman.models.sprites.Block;
import ir.hsnprsd.bomberman.views.ResourceLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BlockView implements Drawable {
    private final Game game;
    private final Block block;

    public BlockView(Game game, Block block) {
        this.game = game;
        this.block = block;
    }

    @Override
    public void draw(Graphics g) {
        synchronized (game) {
            Position position = block.getPosition();
            BufferedImage image = ResourceLoader.loadImage("block.png");
            g.drawImage(image, position.getX1(), position.getY1(), position.getX2() - position.getX1(), position.getY2() - position.getY1(), null);
        }
    }
}
