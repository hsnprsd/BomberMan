package ir.hsnprsd.bomberman.views.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

public class ResourceLoader {
    private static HashMap<String, Image> imageCache = new HashMap<>();
    private static HashMap<String, Font> fontCache = new HashMap<>();

    public static Image loadImage(String path) {
        if (imageCache.containsKey(path)) {
            return imageCache.get(path);
        }
        Image image = null;
        try {
            image = ImageIO.read(ResourceLoader.class.getResource("/ir/hsnprsd/bomberman/resources/" + path));
            imageCache.put(path, image);
        } catch (IOException ignored) {
        }
        return image;
    }

    public static Font loadFont(String path, int style, float size) {
        if (fontCache.containsKey(path)) {
            return fontCache.get(path).deriveFont(size);
        }
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(ResourceLoader.class.getResource("/ir/hsnprsd/bomberman/resources/" + path).toURI()));
            fontCache.put(path, font);
            return font.deriveFont(style, size);
        } catch (FontFormatException | IOException | URISyntaxException e) {
            return null;
        }
    }
}
