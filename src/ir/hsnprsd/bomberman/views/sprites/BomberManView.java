package ir.hsnprsd.bomberman.views.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.geo.Direction;
import ir.hsnprsd.bomberman.models.geo.Position;
import ir.hsnprsd.bomberman.models.sprites.BomberMan;
import ir.hsnprsd.bomberman.views.ResourceLoader;
import ir.hsnprsd.bomberman.views.animations.ImageAnimation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.util.List;

public class BomberManView implements Drawable {
    private final Game game;
    private final BomberMan bomberMan;

    private final SpriteSheet spriteSheet;

    private Direction lastDirection;
    private ImageAnimation animation;

    public BomberManView(Game game, BomberMan bomberMan) {
        this.game = game;
        this.bomberMan = bomberMan;

        spriteSheet = new SpriteSheet(ResourceLoader.loadImage("bomberman.png"), 4, 4);

        lastDirection = bomberMan.getDirection();
    }

    @Override
    public void draw(Graphics g) {
        synchronized (game) {
            Position position = bomberMan.getPosition();
            g.drawImage(getImage(), position.getX1(), position.getY1(), position.getX2() - position.getX1(), position.getY2() - position.getY1(), null);
        }
    }

    private BufferedImage getImage() {
        BufferedImage image;
        synchronized (game) {
            if (bomberMan.getDirection() == null) {
                image = spriteSheet.getImage(3, 0);
            } else if (bomberMan.getDirection() != lastDirection) {
                List<BufferedImage> images = null;
                switch (bomberMan.getDirection()) {
                    case LEFT:
                        images = spriteSheet.getSequence(0, 2, 3, 2);
                        break;
                    case RIGHT:
                        images = spriteSheet.getSequence(0, 1, 3, 1);
                        break;
                    case UP:
                        images = spriteSheet.getSequence(0, 3, 3, 3);
                        break;
                    case DOWN:
                        images = spriteSheet.getSequence(0, 0, 3, 0);
                        break;
                }
                animation = new ImageAnimation(images, Duration.ofMillis(500));
                animation.start();
                image = animation.getImage();
            } else {
                image = animation.getImage();
            }
        }
        lastDirection = bomberMan.getDirection();
        if (image == null) {
            image = spriteSheet.getImage(0, 0);
        }
        return image;
    }
}
