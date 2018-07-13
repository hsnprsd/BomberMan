package ir.hsnprsd.bomberman.views.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.geo.Direction;
import ir.hsnprsd.bomberman.models.geo.Position;
import ir.hsnprsd.bomberman.models.sprites.Enemy;
import ir.hsnprsd.bomberman.views.ResourceLoader;
import ir.hsnprsd.bomberman.views.animations.ImageAnimation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.util.List;

public class EnemyView implements Drawable {
    private final Game game;
    private final Enemy enemy;

    private final SpriteSheet spriteSheet;

    private Direction lastDirection;
    private ImageAnimation animation;

    public EnemyView(Game game, Enemy enemy) {
        this.game = game;
        this.enemy = enemy;

        spriteSheet = new SpriteSheet(ResourceLoader.loadImage("enemy.png"), 12, 8);

        lastDirection = enemy.getDirection();
    }

    @Override
    public void draw(Graphics g) {
        synchronized (game) {
            Position position = enemy.getPosition();
            g.drawImage(getImage(), position.getX1(), position.getY1(), position.getX2() - position.getX1(), position.getY2() - position.getY1(), null);
        }
    }

    private BufferedImage getImage() {
        BufferedImage image;
        synchronized (game) {
            if (enemy.getDirection() == null) {
                image = spriteSheet.getImage(0, 0);
            } else if (enemy.getDirection() != lastDirection) {
                List<BufferedImage> images = null;
                switch (enemy.getDirection()) {
                    case LEFT:
                        images = spriteSheet.getSequence(0, 1, 2, 1);
                        break;
                    case RIGHT:
                        images = spriteSheet.getSequence(0, 2, 2, 2);
                        break;
                    case UP:
                        images = spriteSheet.getSequence(0, 3, 2, 3);
                        break;
                    case DOWN:
                        images = spriteSheet.getSequence(0, 0, 2, 0);
                        break;
                }
                animation = new ImageAnimation(images, Duration.ofMillis(250));
                animation.start();
                image = animation.getImage();
            } else if (animation != null) {
                image = animation.getImage();
            } else {
                image = spriteSheet.getImage(0, 0);
            }
        }
        lastDirection = enemy.getDirection();
        return image;
    }
}
