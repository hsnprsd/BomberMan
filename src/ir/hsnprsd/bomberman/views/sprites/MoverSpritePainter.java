package ir.hsnprsd.bomberman.views.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.geo.Direction;
import ir.hsnprsd.bomberman.models.sprites.MoverSprite;
import ir.hsnprsd.bomberman.views.animations.Animation;
import ir.hsnprsd.bomberman.views.animations.ImageSequence;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Duration;

public abstract class MoverSpritePainter extends SpritePainter {
    private MoverSprite sprite;

    private Animation animation;
    private Direction lastDirection;

    public MoverSpritePainter(Game game, MoverSprite sprite) {
        super(game, sprite);
        this.sprite = sprite;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    protected BufferedImage getImage() {
        synchronized (game) {
            if (sprite.getState() == MoverSprite.State.KILLED) {
                return null;
            }
            if (sprite.getDirection() == null) {
                return getStaticImage();
            } else {
                setAnimation();
                return animation.getImage();
            }
        }
    }

    private void setAnimation() {
        // TODO change duration according to type of MoverSprite
        Duration duration = Duration.ofMillis(400);
        if (sprite.getDirection() != lastDirection || lastDirection == null) {
            synchronized (game) {
                switch (sprite.getDirection()) {
                    case LEFT:
                        animation = new Animation(getLImageSequence(), duration);
                        break;
                    case RIGHT:
                        animation = new Animation(getRImageSequence(), duration);
                        break;
                    case UP:
                        animation = new Animation(getUImageSequence(), duration);
                        break;
                    case DOWN:
                        animation = new Animation(getDImageSequence(), duration);
                        break;
                }
            }
            animation.start();
        }
        lastDirection = sprite.getDirection();
    }

    protected abstract BufferedImage getStaticImage();

    // image sequence for moving right
    protected abstract ImageSequence getUImageSequence();

    // image sequence for moving up
    protected abstract ImageSequence getRImageSequence();

    // image sequence for moving down
    protected abstract ImageSequence getDImageSequence();

    // image sequence for moving left
    protected abstract ImageSequence getLImageSequence();
}
