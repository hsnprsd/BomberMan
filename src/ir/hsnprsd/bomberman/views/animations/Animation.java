package ir.hsnprsd.bomberman.views.animations;

import java.awt.image.BufferedImage;
import java.time.Duration;
import java.util.Date;

public class Animation {
    private ImageSequence imageSequence;
    private Duration duration;

    private Date startDate;

    public Animation(ImageSequence imageSequence, Duration duration) {
        this.imageSequence = imageSequence;
        this.duration = duration;
    }

    public void start() {
        startDate = new Date();
    }

    public BufferedImage getImage() {
        if (startDate == null) {
            throw new IllegalStateException();
        }
        if (imageSequence.getImages().isEmpty()) {
            return null;
        }
        Date now = new Date();
        long elapsedTime = now.getTime() - startDate.getTime();
        int sequenceSize = imageSequence.getImages().size();
        return imageSequence.getImages().get((int) ((elapsedTime / (duration.toMillis() / sequenceSize)) % sequenceSize));
    }

    public int getCycle() {
        Date now = new Date();
        long elapsedTime = now.getTime() - startDate.getTime();
        return (int) Math.ceil(1. * elapsedTime / duration.toMillis());
    }
}
