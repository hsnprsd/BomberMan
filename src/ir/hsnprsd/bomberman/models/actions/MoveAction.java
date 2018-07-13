package ir.hsnprsd.bomberman.models.actions;

import ir.hsnprsd.bomberman.models.sprites.MoverSprite;
import ir.hsnprsd.bomberman.models.geo.Direction;

public class MoveAction extends Action {
    private MoverSprite sprite;
    private Direction direction;

    public MoveAction(MoverSprite sprite, Direction direction) {
        super(Type.MOVE);
        this.sprite = sprite;
        this.direction = direction;
    }

    public MoverSprite getSprite() {
        return sprite;
    }

    public Direction getDirection() {
        return direction;
    }
}
