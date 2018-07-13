package ir.hsnprsd.bomberman.views.animations;

import java.awt.image.BufferedImage;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class ImageAnimation extends Animation {
    private List<BufferedImage> images;
    private Duration duration;

    private Date startDate;

    public ImageAnimation(List<BufferedImage> images, Duration duration) {
        this.images = images;
        this.duration = duration;
    }

    public void start() {
        if (startDate != null) {
            throw new IllegalStateException();
        }
        startDate = new Date();
    }

    public BufferedImage getImage() {
        if (startDate == null) {
            throw new IllegalStateException();
        }
        if (images == null || images.isEmpty()) {
            return null;
        }
        Date now = new Date();
        long elapsedTime = now.getTime() - startDate.getTime();
        return images.get((int) ((elapsedTime / (duration.toMillis() / images.size())) % images.size()));
    }
}
