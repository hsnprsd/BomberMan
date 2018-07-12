package ir.hsnprsd.bomberman.views;

import ir.hsnprsd.bomberman.controllers.GameController;
import ir.hsnprsd.bomberman.models.Game;

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
        getContentPane().add(new StartMenu(this, controller));
        pack();
    }

    public void showGame() {
        getContentPane().removeAll();
        getContentPane().add(new GameView(controller.getGame(), controller));
        pack();
    }
}