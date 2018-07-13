package ir.hsnprsd.bomberman.views;

import ir.hsnprsd.bomberman.controllers.GameController;
import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.views.panels.GamePanel;
import ir.hsnprsd.bomberman.views.panels.StartMenuPanel;

import javax.swing.*;
import java.awt.*;

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
        GamePanel view = new GamePanel(controller.getGame(), controller);
        getContentPane().add(view);
        pack();
        view.start();
    }
}