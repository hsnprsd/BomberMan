package ir.hsnprsd.bomberman.views;

import ir.hsnprsd.bomberman.Settings;
import ir.hsnprsd.bomberman.controllers.GameController;
import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.sprites.Block;
import ir.hsnprsd.bomberman.models.sprites.BomberMan;
import ir.hsnprsd.bomberman.models.sprites.Enemy;
import ir.hsnprsd.bomberman.views.utils.ResourceLoader;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class GameView extends JPanel {
    private Thread thread;

    private Game game;
    private GameController controller;

    GameView(Game game, GameController controller) {
        this.game = game;

        int cellSize = Settings.UI.GameView.CELL_SIZE;
        setPreferredSize(new Dimension(game.getWidth() * cellSize, game.getHeight() * cellSize));

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                controller.handleKeyEvent(keyEvent);
            }
        });
        addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent ancestorEvent) {
                requestFocus();
            }

            @Override
            public void ancestorRemoved(AncestorEvent ancestorEvent) {
            }

            @Override
            public void ancestorMoved(AncestorEvent ancestorEvent) {
            }
        });

        thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                repaint();
                try {
                    Thread.sleep(1000 / Settings.UI.GameView.FPS);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBG(g);
        synchronized (game) {
            drawBomberMan(g, game.getBomberMan());
            for (Enemy enemy: game.getEnemies()) {
                drawEnemy(g, enemy);
            }
            for (Block block : game.getBlocks()) {
                drawBlock(g, block);
            }
        }
    }

    private void drawEnemy(Graphics g, Enemy enemy) {
        drawImage(g, "enemy.png", enemy.getX(), enemy.getY());
    }

    private void drawBlock(Graphics g, Block block) {
        if (block.getType() == 1) {
            drawImage(g, "block-1.png", block.getX(), block.getY());
        } else {
            drawImage(g, "block-2.png", block.getX(), block.getY());
        }
    }

    private void drawBomberMan(Graphics g, BomberMan bomberMan) {
        drawImage(g, "bomberman.png", bomberMan.getX(), bomberMan.getY());
    }

    private void drawBG(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawImage(Graphics g, String path, int x, int y) {
        int cellSize = Settings.UI.GameView.CELL_SIZE;
        int padding = Settings.UI.GameView.CELL_PADDING;
        g.drawImage(ResourceLoader.loadImage(path), x * cellSize + padding, y * cellSize + padding, cellSize - 2 * padding, cellSize - 2 * padding, null);
    }
}
