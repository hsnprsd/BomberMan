package ir.hsnprsd.bomberman.views;

import ir.hsnprsd.bomberman.controllers.GameController;
import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.views.panels.GameOverPanel;
import ir.hsnprsd.bomberman.views.panels.GamePanel;
import ir.hsnprsd.bomberman.views.panels.StartMenuPanel;

import javax.swing.*;

public class GameFrame extends JFrame {
    private Game game;
    private GameController controller;

    public GameFrame(GameController controller) {
        this.controller = controller;
        setTitle("Bomber Man!");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showStartMenu();
    }

    private void showStartMenu() {
        getContentPane().removeAll();
        getContentPane().add(new StartMenuPanel(this, controller));
        pack();
    }

    public void showGame() {
        getContentPane().removeAll();
        GamePanel panel = new GamePanel(controller.getGame(), controller, this);
        getContentPane().add(panel);
        pack();
        panel.start();
    }

    public void showGameOver() {
        getContentPane().removeAll();
        getContentPane().add(new GameOverPanel());
        pack();
    }
}