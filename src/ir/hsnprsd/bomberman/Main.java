package ir.hsnprsd.bomberman;

import ir.hsnprsd.bomberman.controllers.GameController;
import ir.hsnprsd.bomberman.views.GameFrame;

public class Main {
    public static void main(String[] args) {
        GameController controller = new GameController();
        GameFrame view = new GameFrame(controller);
        view.setVisible(true);
    }
}