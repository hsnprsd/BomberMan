package ir.hsnprsd.bomberman;

import java.util.Arrays;
import java.util.List;

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
            int CELL_SIZE = 64;
            int CELL_PADDING = 7;
            int FPS = 50;
            List<String> BLOCK_IMAGES = Arrays.asList("block-1.png", "block-2.png");
        }
    }

    public interface Game {
        int FPS = 60;
    }
}
