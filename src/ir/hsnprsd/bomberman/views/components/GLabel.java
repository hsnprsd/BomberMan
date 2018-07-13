package ir.hsnprsd.bomberman.views.components;

import ir.hsnprsd.bomberman.views.Settings;
import ir.hsnprsd.bomberman.views.ResourceLoader;

import javax.swing.*;
import java.awt.*;

public class GLabel extends JLabel {
    public GLabel(String text, int size) {
        super(text);
        setFont(ResourceLoader.loadFont(Settings.FONT_NAME, Font.TRUETYPE_FONT, size));
        setForeground(Color.WHITE);
    }
}
