package ua.project.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame extends JFrame {
    JRadioButton r1;
    JRadioButton r2;
    JLabel lb;
    JButton b;
    final Font font = new Font(Font.DIALOG, Font.BOLD, 15);

    public SettingsFrame() {
        super("Налаштування гри");
        drawElements();
        configureFrame();
    }

    private void drawElements() {
        r1 = new JRadioButton("Online");
        r2 = new JRadioButton("Offline");
        r1.setBounds(30,20,100,30);
        r2.setBounds(30,50,100,30);

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        r1.setFont(font);
        r2.setFont(font);

        add(r1);
        add(r2);
    }

    private void configureFrame() {
        setSize(250, 150);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
