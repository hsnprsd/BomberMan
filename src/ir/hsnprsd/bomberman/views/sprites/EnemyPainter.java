package ir.hsnprsd.bomberman.views.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.sprites.Enemy;
import ir.hsnprsd.bomberman.views.ResourceLoader;
import ir.hsnprsd.bomberman.views.animations.ImageSequence;

import java.awt.image.BufferedImage;

public class EnemyPainter extends MoverSpritePainter {
    private SpriteSheet spriteSheet;

    public EnemyPainter(Game game, Enemy enemy) {
        super(game, enemy);

        spriteSheet = new SpriteSheet(ResourceLoader.loadImage("enemy.png"), 4, 4);
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
