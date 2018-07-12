package ir.hsnprsd.bomberman.models.sprites;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.utils.Cell;
import ir.hsnprsd.bomberman.models.utils.Direction;

public abstract class MoverSprite extends Sprite {
    MoverSprite(Game game, Type type, Cell cell) {
        super(game, type, cell);
    }

    public void move(Direction direction) {
        synchronized (game) {
            int x = direction.dx + getX();
            int y = direction.dy + getY();
            Cell destination = new Cell(x, y);
            if (!destination.isIn(game.getWidth(), game.getHeight())) {
                return;
            }
            Sprite sprite = game.getSprite(x, y);
            if (sprite == null) {
                setX(x);
                setY(y);
            }
        }
    }
}
