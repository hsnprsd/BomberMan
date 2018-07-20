package ir.hsnprsd.bomberman.models;

import ir.hsnprsd.bomberman.models.actions.Action;
import ir.hsnprsd.bomberman.models.actions.MoveAction;
import ir.hsnprsd.bomberman.models.geo.Position;
import ir.hsnprsd.bomberman.models.sprites.*;
import ir.hsnprsd.bomberman.models.sprites.interfaces.Destroyable;

import java.util.*;

public class Game {
    private Thread thread;

    private int gridWidth, gridHeight;
    private int width, height;

    private State state = State.LOADING;

    private BomberMan bomberMan;
    private List<Bomb> bombs = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private List<Block> blocks = new ArrayList<>();

    private Queue<Action> actions = new LinkedList<>();

    public Game(int gridWidth, int gridHeight) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;

        this.width = gridWidth * getCellSize();
        this.height = gridHeight * getCellSize();

        for (int x = 0; x < gridWidth; x += 2) {
            for (int y = 0; y < gridHeight; y += 2) {
                blocks.add(new Block(this, new Position(x * getCellSize(), y * getCellSize(), getCellSize())));
            }
        }

        bomberMan = new BomberMan(this, new Position(0, getCellSize(), getCellSize()));

        Random random = new Random();
        for (int i = 0; i < Math.max((gridWidth + gridHeight) / 4, 1); ++i) {
            int x = random.nextInt(gridWidth);
            int y = random.nextInt(gridHeight);
            Position position = new Position(x * getCellSize(), y * getCellSize(), (x + 1) * getCellSize(), (y + 1) * getCellSize());
            if (!getSprites(position).isEmpty() || (x == 1 && y == 1) || (x == 2 && y == 1)) {
                --i;
                continue;
            }
            enemies.add(new Enemy(this, new Position(x * getCellSize(), y * getCellSize(), getCellSize())));
        }
    }

    public synchronized List<Sprite> getSprites(Position position) {
        List<Sprite> result = new ArrayList<>();
        if (bomberMan.getPosition().intersects(position)) {
            result.add(bomberMan);
        }
        for (Enemy enemy : enemies) {
            if (enemy.getPosition().intersects(position)) {
                result.add(enemy);
            }
        }
        for (Block block : blocks) {
            if (block.getPosition().intersects(position)) {
                result.add(block);
            }
        }
        return result;
    }

    public synchronized void destroySprites(Position position) {
        for (Sprite sprite : getSprites(position)) {
            if (sprite instanceof Destroyable) {
                ((Destroyable) sprite).destroy();
                switch (sprite.getType()) {
                    case BOMBERMAN:
                        setState(State.GAMEOVER);
                        break;
                    case ENEMY:
                        enemies.remove(((Enemy) sprite));
                        break;
                }
            }
        }
    }

    public int getCellSize() {
        return Settings.CELL_SIZE;
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

    public List<Bomb> getBombs() {
        return bombs;
    }

    public synchronized List<Enemy> getEnemies() {
        return Collections.unmodifiableList(enemies);
    }

    public synchronized List<Block> getBlocks() {
        return Collections.unmodifiableList(blocks);
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
                moveAction.getSprite().setNextDirection(moveAction.getDirection());
                break;
            case BOMB:
                bomberMan.placeBomb();
                break;
        }
    }

    private synchronized void doUpdates() {
        bomberMan.move();

        for (Enemy enemy : enemies) {
            enemy.move();
        }

        // bomb garbage collection
        ArrayList<Bomb> explodedBombs = new ArrayList<>();
        for (Bomb bomb : bombs) {
            if (bomb.getState() == Bomb.State.EXPLODED) {
                explodedBombs.add(bomb);
            }
        }
        for (Bomb bomb : explodedBombs) {
            bombs.remove(bomb);
        }
    }

    public void start() {
        if (thread != null) {
            throw new IllegalStateException();
        }
        thread = new Thread(() -> {
            setState(State.RUNNING);
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (this) {
                    if (getState() == State.RUNNING) {
                        doActions();
                        doUpdates();
                    }
                }
                try {
                    Thread.sleep(1000 / Settings.FPS);
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
