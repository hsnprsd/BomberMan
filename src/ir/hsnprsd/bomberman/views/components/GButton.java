package ir.hsnprsd.bomberman.views.components;

import ir.hsnprsd.bomberman.Settings;
import ir.hsnprsd.bomberman.views.utils.ResourceLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GButton extends JComponent {
    private Type type;
    private String text;
    private State state = State.NORMAL;

    public GButton(String text, Type type) {
        this.text = text;
        this.type = type;

        setDimension(new Dimension(type.width, type.height));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                state = State.DOWN;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                state = State.NORMAL;
                repaint();
            }
        });
    }

    private void setDimension(Dimension dimension) {
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (state == State.DOWN) {
            drawBG(g, type.activeBGColor);
        } else {
            drawBG(g, type.bgColor);
        }
        drawText(g);
    }

    private void drawText(Graphics g) {
        g.setColor(type.fgColor);
        g.setFont(ResourceLoader.loadFont("arcade.ttf", Font.TRUETYPE_FONT, Settings.UI.FontSize.EXTRA_SMALL));
        int width = g.getFontMetrics().stringWidth(text);
        int height = g.getFontMetrics().getHeight();
        g.drawString(text, (getWidth() - width) / 2, (getHeight() + height) / 2);
    }

    private void drawBG(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private enum State {
        DOWN, NORMAL, HOVER
    }

    public enum Type {
        NORMAL(150, 50);

        int width, height, fontSize = Settings.UI.FontSize.EXTRA_SMALL;
        Color fgColor = Color.WHITE, bgColor = Color.DARK_GRAY, activeBGColor = Color.BLUE;

        Type(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}
