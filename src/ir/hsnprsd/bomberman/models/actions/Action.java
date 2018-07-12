package ir.hsnprsd.bomberman.models.actions;

public abstract class Action {
    protected Type type;

    public Action(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        MOVE, BOMB
    }
}
