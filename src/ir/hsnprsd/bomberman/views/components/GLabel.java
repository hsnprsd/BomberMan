package ir.hsnprsd.bomberman.views.components;

import ir.hsnprsd.bomberman.Settings;
import ir.hsnprsd.bomberman.views.utils.ResourceLoader;

import javax.swing.*;
import java.awt.*;

public class GLabel extends JLabel {
    public GLabel(String text, int size) {
        super(text);
        setFont(ResourceLoader.loadFont(Settings.UI.FONT_NAME, Font.TRUETYPE_FONT, size));
        setForeground(Color.WHITE);
    }
}
