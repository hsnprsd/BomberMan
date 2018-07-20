package ir.hsnprsd.bomberman.views;

public interface Settings {
    String RESOURCE_DIRECTORY = "/ir/hsnprsd/bomberman/resources/";

    String FONT_NAME = "arcade.ttf";

    interface FontSize {
        int LARGE = 75;
        int NORMAL = 50;
        int SMALL = 35;
        int EXTRA_SMALL = 30;
    }

    interface StartMenu {
        int PADDING = 75;
        int SPACING = 50;
        int BUTTON_WIDTH = 150;
        int BUTTON_HEIGHT = 50;
    }

    interface GamePanel {
        int FPS = 50;
        int CELL_PADDING = 5;

        interface Bomb {
            int EXPLODE_ANIMATION_TIMEOUT = 1000;
        }
    }
}
