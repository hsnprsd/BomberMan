package ir.hsnprsd.bomberman.views.animations;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;

public class ImageSequence {
    private List<BufferedImage> images;

    public ImageSequence(List<BufferedImage> images) {
        this.images = images;
    }

    public List<BufferedImage> getImages() {
        return Collections.unmodifiableList(images);
    }
}
