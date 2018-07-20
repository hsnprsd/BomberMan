package ir.hsnprsd.bomberman.views.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.sprites.Bomb;
import ir.hsnprsd.bomberman.views.ResourceLoader;
import ir.hsnprsd.bomberman.views.Settings;
import ir.hsnprsd.bomberman.views.animations.Animation;

import java.awt.image.BufferedImage;
import java.time.Duration;

public class BombPainter extends SpritePainter {
    private Bomb bomb;

    private BufferedImage staticImage;
    private SpriteSheet spriteSheet;

    private Animation animation;

    public BombPainter(Game game, Bomb bomb) {
        super(game, bomb);
        this.bomb = bomb;

        staticImage = ResourceLoader.loadImage("tnt.png");
        spriteSheet = new SpriteSheet(ResourceLoader.loadImage("explosion.png"), 6, 7);
    }

    @Override
    protected BufferedImage getImage() {
        if (bomb.getState() == Bomb.State.EXPLODING) {
            return staticImage;
        } else {
            if (animation == null) {
                animation = new Animation(spriteSheet.getSequence(0, 0, 2, 6), Duration.ofMillis(Settings.GamePanel.Bomb.EXPLODE_ANIMATION_TIMEOUT));
                animation.start();
                return animation.getImage();
            } else {
                BufferedImage image = animation.getImage();
                if (animation.getCycle() > 1) {
                    return null;
                }
                return image;
            }
        }
    }
}
