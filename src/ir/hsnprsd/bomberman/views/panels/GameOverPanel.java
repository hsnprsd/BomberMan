package ir.hsnprsd.bomberman.views.panels;

import ir.hsnprsd.bomberman.views.Settings;
import ir.hsnprsd.bomberman.views.components.GLabel;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {
    public GameOverPanel() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(480, 480));
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        add(Box.createHorizontalGlue());
        GLabel gameOverLabel = new GLabel("GAME OVER", Settings.FontSize.LARGE);
        add(gameOverLabel);
        add(Box.createHorizontalGlue());
    }
}
