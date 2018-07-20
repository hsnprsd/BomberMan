package ir.hsnprsd.bomberman.models;

public interface Settings {
    int FPS = 50;
    int CELL_SIZE = 75;

    interface BomberMan {
        int SPEED = 5;
    }

    interface Bomb {
        int TIMEOUT = 3000;
    }

    interface Enemy {
        int SPEED = 2;
        int DECIDE_TIMEOUT = 1000;
    }
}
