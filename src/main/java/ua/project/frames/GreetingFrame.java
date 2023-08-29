package ua.project.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GreetingFrame extends JFrame implements ActionListener {
    JLabel lb;
    JButton b;
    final Font font = new Font(Font.DIALOG, Font.BOLD, 15);

    public GreetingFrame() {
        super("Вітаємо!");
        drawElements();
        configureGrame();
    }

    private void drawElements() {
        lb = new JLabel("Вітаємо вас у грі дитинства і всіх розумників!");
        lb.setBounds(30, 30, 350, 30);
        lb.setFont(font);

        b = new JButton("OK");
        b.setBounds(380, 30, 60, 30);
        b.setFont(font);
        b.addActionListener(this);

        add(lb);
        add(b);
    }

    private void configureGrame() {
        setSize(480, 130);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b) {
            dispose();
            //new GameWindow();
        }
    }
}
