package ir.hsnprsd.bomberman.views.sprites;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SpriteSheet {
    private BufferedImage image;
    private int gridWidth, gridHeight;

    public SpriteSheet(BufferedImage image, int gridWidth, int gridHeight) {
        this.image = image;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
    }

    public BufferedImage getImage(int x, int y) {
        if (!isIn(x, y)) {
            throw new IllegalArgumentException();
        }
        int cellWidth = image.getWidth() / gridWidth;
        int cellHeight = image.getHeight() / gridHeight;
        return image.getSubimage(cellWidth * x, cellHeight * y, cellWidth, cellHeight);
    }

    public java.util.List<BufferedImage> getSequence(int x1, int y1, int x2, int y2) {
        if (y1 > y2 || (y1 == y2 && x1 > x2)) {
            throw new IllegalArgumentException();
        }
        if (!isIn(x1, y1) || !isIn(x2, y2)) {
            throw new IllegalArgumentException();
        }
        List<BufferedImage> images = new ArrayList<>();
        do {
            images.add(getImage(x1, y1));
            ++x1;
            if (x1 == gridWidth) {
                x1 = 0;
                y1++;
            }
        } while (!(x1 == x2 && y1 == y2));
        return images;
    }

    private boolean isIn(int x, int y) {
        return 0 <= x && x < gridWidth && 0 <= y && y < gridHeight;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }
}