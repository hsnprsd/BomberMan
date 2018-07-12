package ir.hsnprsd.bomberman.models;

import ir.hsnprsd.bomberman.Settings;
import ir.hsnprsd.bomberman.models.actions.Action;
import ir.hsnprsd.bomberman.models.actions.MoveAction;
import ir.hsnprsd.bomberman.models.sprites.Block;
import ir.hsnprsd.bomberman.models.sprites.BomberMan;
import ir.hsnprsd.bomberman.models.sprites.Enemy;
import ir.hsnprsd.bomberman.models.sprites.Sprite;
import ir.hsnprsd.bomberman.models.utils.Cell;

import java.util.*;

public class Game {
    private Thread thread;

    private int width, height;

    private State state = State.LOADING;

    private BomberMan bomberMan;
    private List<Enemy> enemies = new ArrayList<>();
    private List<Block> blocks = new ArrayList<>();

    private Queue<Action> actions = new LinkedList<>();

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        for (int x = 0; x < width; x += 2) {
            for (int y = 0; y < height; y += 2) {
                blocks.add(new Block(this, new Cell(x, y)));
            }
        }
        bomberMan = new BomberMan(this, new Cell(0, 1));
        Random random = new Random();
        for (int i = 0; i < Math.max((width + height) / 4, 1); ++i) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (getSprite(x, y) != null || (x == 1 && y == 1) || (x == 2 && y == 1)) {
                --i;
                continue;
            }
            enemies.add(new Enemy(this, new Cell(x, y)));
        }
    }

    public synchronized Sprite getSprite(int x, int y) {
        Cell cell = new Cell(x, y);
        if (bomberMan.getCell().equals(cell)) {
            return bomberMan;
        }
        for (Block block : blocks) {
            if (block.getCell().equals(cell)) {
                return block;
            }
        }
        return null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized BomberMan getBomberMan() {
        return bomberMan;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public synchronized List<Block> getBlocks() {
        return blocks;
    }

    public synchronized State getState() {
        return state;
    }

    private void setState(State state) {
        this.state = state;
    }

    public synchronized void addAction(Action action) {
        actions.add(action);
    }

    private synchronized void doActions() {
        while (!actions.isEmpty()) {
            doAction(actions.remove());
        }
    }

    private synchronized void doAction(Action action) {
        switch (action.getType()) {
            case MOVE:
                MoveAction moveAction = ((MoveAction) action);
                moveAction.getSprite().move(moveAction.getDirection());
                break;
        }
    }

    public void start() {
        if (thread != null) {
            throw new IllegalStateException();
        }
        thread = new Thread(() -> {
            setState(State.RUNNING);
            while (!Thread.currentThread().isInterrupted()) {
                if (getState() == State.RUNNING) {
                    doActions();
                }
                try {
                    Thread.sleep(1000 / Settings.Game.FPS);
                } catch (InterruptedException e) {
                    setState(State.INTERRUPTED);
                    break;
                }
            }
            setState(State.INTERRUPTED);
        });
        thread.start();
    }

    public synchronized void pause() {
        setState(State.PAUSED);
    }

    public void end() {
        thread.interrupt();
    }

    public enum State {
        LOADING, RUNNING, PAUSED, GAMEOVER, INTERRUPTED
    }
}
