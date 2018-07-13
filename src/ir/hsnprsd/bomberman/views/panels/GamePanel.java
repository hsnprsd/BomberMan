package ir.hsnprsd.bomberman.views.panels;

import ir.hsnprsd.bomberman.controllers.GameController;
import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.sprites.Block;
import ir.hsnprsd.bomberman.models.sprites.Enemy;
import ir.hsnprsd.bomberman.views.Settings;
import ir.hsnprsd.bomberman.views.sprites.BlockView;
import ir.hsnprsd.bomberman.views.sprites.BomberManView;
import ir.hsnprsd.bomberman.views.sprites.EnemyView;

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

    private final BomberManView bomberManView;
    private final HashMap<Enemy, EnemyView> enemyViews = new HashMap<>();
    private final HashMap<Block, BlockView> blockViews = new HashMap<>();

    public GamePanel(Game game, GameController controller) {
        this.game = game;
        this.controller = controller;

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
            bomberManView = new BomberManView(game, game.getBomberMan());
            for (Enemy enemy : game.getEnemies()) {
                enemyViews.put(enemy, new EnemyView(game, enemy));
            }
            for (Block block : game.getBlocks()) {
                blockViews.put(block, new BlockView(game, block));
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
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        drawBG(g);
        synchronized (game) {
            bomberManView.draw(g);
            for (EnemyView enemyView : enemyViews.values()) {
                enemyView.draw(g);
            }
            for (BlockView blockView : blockViews.values()) {
                blockView.draw(g);
            }
        }
        graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    private void drawBG(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
