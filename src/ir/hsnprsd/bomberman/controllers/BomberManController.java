package ir.hsnprsd.bomberman.controllers;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.actions.Action;
import ir.hsnprsd.bomberman.models.actions.MoveAction;
import ir.hsnprsd.bomberman.models.geo.Direction;
import ir.hsnprsd.bomberman.models.sprites.BomberMan;

import java.awt.event.KeyEvent;

public class BomberManController {
    private Game game;
    private BomberMan bomberMan;

    public BomberManController(Game game) {
        this.game = game;
        bomberMan = game.getBomberMan();
    }

    void handleKeyEvent(KeyEvent event) {
        if (game == null || bomberMan == null) {
            throw new IllegalStateException();
        }
        Action action = null;
        switch (event.getKeyCode()) {
            case KeyEvent.VK_UP:
                action = new MoveAction(bomberMan, Direction.UP);
                break;
            case KeyEvent.VK_RIGHT:
                action = new MoveAction(bomberMan, Direction.RIGHT);
                break;
            case KeyEvent.VK_DOWN:
                action = new MoveAction(bomberMan, Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                action = new MoveAction(bomberMan, Direction.LEFT);
                break;
        }
        if (action != null) {
            game.addAction(action);
        }
    }
}
