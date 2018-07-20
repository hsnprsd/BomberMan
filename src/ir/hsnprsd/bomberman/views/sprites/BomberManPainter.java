package ir.hsnprsd.bomberman.views.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.sprites.BomberMan;
import ir.hsnprsd.bomberman.views.ResourceLoader;
import ir.hsnprsd.bomberman.views.animations.ImageSequence;

import java.awt.image.BufferedImage;

public class BomberManPainter extends MoverSpritePainter {
    private SpriteSheet spriteSheet;

    public BomberManPainter(Game game, BomberMan bomberMan) {
        super(game, bomberMan);

        spriteSheet = new SpriteSheet(ResourceLoader.loadImage("bomberman.png"), 4, 4);
    }

    @Override
    protected BufferedImage getStaticImage() {
        return spriteSheet.getImage(3, 0);
    }

    @Override
    protected ImageSequence getUImageSequence() {
        return spriteSheet.getSequence(0, 3, 3, 3);
    }

    @Override
    protected ImageSequence getRImageSequence() {
        return spriteSheet.getSequence(0, 1, 3, 1);
    }

    @Override
    protected ImageSequence getDImageSequence() {
        return spriteSheet.getSequence(0, 0, 3, 0);
    }

    @Override
    protected ImageSequence getLImageSequence() {
        return spriteSheet.getSequence(0, 2, 3, 2);
    }
}
