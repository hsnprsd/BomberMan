package ir.hsnprsd.bomberman.controllers;

import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.sprites.Enemy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class GameController {
    private Game game;

    private BomberManController bomberManController;
    private HashMap<Enemy, EnemyController> enemyControllers = new HashMap<>();

    public GameController() {
    }

    public void startGame() {
        if (game != null) {
            throw new IllegalStateException();
        }
        game = new Game(9, 9);
        bomberManController = new BomberManController(game);
        synchronized (game) {
            for (Enemy enemy : game.getEnemies()) {
                EnemyController controller = new EnemyController(game, enemy);
                controller.start();
                enemyControllers.put(enemy, controller);
            }
        }
        game.start();
    }

    public void pauseGame() {
        if (game == null) {
            throw new IllegalStateException();
        }
        game.pause();
    }

    public void endGame() {
        if (game == null) {
            throw new IllegalStateException();
        }
        game.end();
        bomberManController = null;
        for (Enemy enemy: enemyControllers.keySet()) {
            enemyControllers.get(enemy).interrupt();
        }
        enemyControllers.clear();
    }

    public void handleKeyEvent(KeyEvent event) {
        bomberManController.handleKeyEvent(event);
    }

    public Game getGame() {
        return game;
    }
}
