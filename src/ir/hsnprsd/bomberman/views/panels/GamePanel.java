package ir.hsnprsd.bomberman.views.panels;

import ir.hsnprsd.bomberman.controllers.GameController;
import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.sprites.Block;
import ir.hsnprsd.bomberman.models.sprites.Bomb;
import ir.hsnprsd.bomberman.models.sprites.Enemy;
import ir.hsnprsd.bomberman.views.GameFrame;
import ir.hsnprsd.bomberman.views.Settings;
import ir.hsnprsd.bomberman.views.sprites.BlockPainter;
import ir.hsnprsd.bomberman.views.sprites.BombPainter;
import ir.hsnprsd.bomberman.views.sprites.BomberManPainter;
import ir.hsnprsd.bomberman.views.sprites.EnemyPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class GamePanel extends JPanel {
    private Thread thread;

    private final Game game;
    private final GameController controller;

    private GameFrame frame;

    private final BomberManPainter bomberManPainter;
    private final HashMap<Bomb, BombPainter> bombViews = new HashMap<>();
    private final HashMap<Enemy, EnemyPainter> enemyViews = new HashMap<>();
    private final HashMap<Block, BlockPainter> blockViews = new HashMap<>();

    public GamePanel(Game game, GameController controller, GameFrame frame) {
        this.game = game;
        this.controller = controller;
        this.frame = frame;

        setPreferredSize(new Dimension(game.getWidth(), game.getHeight()));

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                controller.handleKeyEvent(keyEvent);
            }
        });
        addHierarchyListener(hierarchyEvent -> {
            if ((hierarchyEvent.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) > 0) {
                requestFocus();
            }
        });

        synchronized (game) {
            bomberManPainter = new BomberManPainter(game, game.getBomberMan());
            for (Enemy enemy : game.getEnemies()) {
                enemyViews.put(enemy, new EnemyPainter(game, enemy));
            }
            for (Block block : game.getBlocks()) {
                blockViews.put(block, new BlockPainter(game, block));
            }
        }
    }

    public void start() {
        if (thread != null) {
            throw new IllegalStateException();
        }
        thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                repaint();
                try {
                    Thread.sleep(1000 / Settings.GamePanel.FPS);
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
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        synchronized (game) {
            if (game.getState() == Game.State.GAMEOVER) {
                frame.showGameOver();
                return;
            }
            BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics g = image.getGraphics();
            drawBG(g);
            bomberManPainter.draw(g);
            updateBombs();
            for (BombPainter bombPainter : bombViews.values()) {
                bombPainter.draw(g);
            }
            for (EnemyPainter enemyPainter : enemyViews.values()) {
                enemyPainter.draw(g);
            }
            for (BlockPainter blockPainter : blockViews.values()) {
                blockPainter.draw(g);
            }
            graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }
    }

    private void updateBombs() {
        synchronized (game) {
            for (Bomb bomb : game.getBombs()) {
                if (!bombViews.containsKey(bomb)) {
                    bombViews.put(bomb, new BombPainter(game, bomb));
                }
            }
        }
    }

    private void drawBG(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
