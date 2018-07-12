package ir.hsnprsd.bomberman.models.actions;

import ir.hsnprsd.bomberman.models.sprites.Sprite;
import ir.hsnprsd.bomberman.models.utils.Direction;

public class MoveAction extends Action {
    private Sprite sprite;
    private Direction direction;

    public MoveAction(Sprite sprite, Direction direction) {
        super(Type.MOVE);
        this.sprite = sprite;
        this.direction = direction;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Direction getDirection() {
        return direction;
    }
}
