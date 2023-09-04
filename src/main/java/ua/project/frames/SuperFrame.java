package ua.project.frames;

import ua.project.logic.Game;

import javax.swing.*;
import java.awt.*;

public abstract class SuperFrame extends JFrame {
    private final JPanel rootPanel = new JPanel();
    final Font font = new Font(Font.SERIF, Font.BOLD, 15);
    public static final Game game = new Game();

    public SuperFrame(String title, Dimension size) {
        super(title);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/java/ua/project/resources/CitiesLogo.png"));
        drawElements();
        configureFrame(size);
    }

    private void configureFrame(Dimension size) {
        add(rootPanel);
        rootPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        rootPanel.setFont(font);
        setSize(size);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    abstract void drawElements();
}
