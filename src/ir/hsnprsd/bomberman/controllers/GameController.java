package ir.hsnprsd.bomberman.controllers;

import ir.hsnprsd.bomberman.models.Game;

import java.awt.event.KeyEvent;

public class GameController {
    private Game game;

    private BomberManController bomberManController;

    public GameController() {
    }

    public void startGame() {
        if (game != null) {
            throw new IllegalStateException();
        }
        game = new Game(9, 9);
        game.start();
        bomberManController = new BomberManController(game);
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
    }

    public void handleKeyEvent(KeyEvent event) {
        bomberManController.handleKeyEvent(event);
    }

    public Game getGame() {
        return game;
    }
}
