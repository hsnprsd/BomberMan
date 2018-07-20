package ir.hsnprsd.bomberman.views.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.geo.Position;
import ir.hsnprsd.bomberman.models.sprites.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SpritePainter {
    protected final Game game;
    private final Sprite sprite;

    public SpritePainter(Game game, Sprite sprite) {
        this.game = game;
        this.sprite = sprite;
    }

    public void draw(Graphics g) {
        synchronized (game) {
            Position position = sprite.getPosition();
            BufferedImage image = getImage();
            if (image != null) {
                g.drawImage(image, position.getX1(), position.getY1(), position.getX2() - position.getX1(), position.getY2() - position.getY1(), null);
            }
        }
    }

    protected abstract BufferedImage getImage();
}
