package ir.hsnprsd.bomberman;

public class Settings {
    public static final String RESOURCE_DIRECTORY = "/ir/hsnprsd/bomberman/resources/";

    public interface UI {
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

        interface GameView {
            int CELL_SIZE = 80;
            int CELL_PADDING = 5;
            int FPS = 50;
        }
    }

    public interface Game {
        int FPS = 60;

        interface Enemy {
            int DECIDE_TIMEOUT = 1000;
        }
    }
}
