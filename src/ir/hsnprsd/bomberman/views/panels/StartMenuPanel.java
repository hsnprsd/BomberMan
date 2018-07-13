package ir.hsnprsd.bomberman.views.panels;

import ir.hsnprsd.bomberman.controllers.GameController;
import ir.hsnprsd.bomberman.views.GameFrame;
import ir.hsnprsd.bomberman.views.Settings;
import ir.hsnprsd.bomberman.views.components.GButton;
import ir.hsnprsd.bomberman.views.components.GLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartMenuPanel extends JPanel {
    public StartMenuPanel(GameFrame view, GameController controller) {
//        start menu settings
        setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        int padding = Settings.StartMenu.PADDING;
        setBorder(new EmptyBorder(padding, padding, padding, padding));

//        add title and lead to header
        JPanel header = getPanel();

        GLabel title = new GLabel("BOMBER MAN", Settings.FontSize.LARGE);
        title.setAlignmentX(CENTER_ALIGNMENT);
        GLabel lead = new GLabel("THE GREAT GAME!", Settings.FontSize.SMALL);
        lead.setAlignmentX(CENTER_ALIGNMENT);

        header.add(title);
        header.add(lead);

//        add buttons to menu
        JPanel menu = getPanel();

        GButton startButton = new GButton("START", GButton.Type.NORMAL);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                controller.startGame();
                view.showGame();
            }
        });
        GButton loadButton = new GButton("LOAD", GButton.Type.NORMAL);
        GButton exitButton = new GButton("EXIT", GButton.Type.NORMAL);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.exit(0);
            }
        });

        JButton button = new JButton();

        menu.add(startButton);
        menu.add(Box.createRigidArea(new Dimension(0, 25)));
        menu.add(loadButton);
        menu.add(Box.createRigidArea(new Dimension(0, 25)));
        menu.add(exitButton);

//        add panels to start menu
        add(header);
        add(Box.createRigidArea(new Dimension(0, Settings.StartMenu.SPACING)));
        add(menu);
    }

    private JPanel getPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        return panel;
    }
}
