package ir.hsnprsd.bomberman.controllers;

import ir.hsnprsd.bomberman.Settings;
import ir.hsnprsd.bomberman.models.Game;
import ir.hsnprsd.bomberman.models.actions.MoveAction;
import ir.hsnprsd.bomberman.models.sprites.Enemy;
import ir.hsnprsd.bomberman.models.utils.Direction;

import java.util.Date;
import java.util.Random;

public class EnemyController extends Thread {
    private Game game;
    private Enemy enemy;

    private static Random random = new Random();

    public EnemyController(Game game, Enemy enemy) {
        this.game = game;
        this.enemy = enemy;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            long startTime = new Date().getTime();
            game.addAction(new MoveAction(enemy, Direction.values()[random.nextInt(4)]));
            try {
                Thread.sleep(Settings.Game.Enemy.DECIDE_TIMEOUT - (new Date().getTime() - startTime));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
