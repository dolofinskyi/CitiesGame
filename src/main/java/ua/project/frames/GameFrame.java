package ua.project.frames;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends SuperFrame implements ActionListener {
    JLabel cityHintLabel;
    JLabel computerLabel;

    JTextField cityField;
    JButton makeMoveButton;

    public GameFrame() {
        super("Міста", new Dimension(360, 150));
    }

    @Override
    public void drawElements() {
        JPanel panel0 = new JPanel();
        panel0.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

        cityHintLabel = new JLabel("Введіть назву міста");
        computerLabel = new JLabel("Комп'ютер: ");
        cityField = new JTextField();
        makeMoveButton = new JButton("Зробити хід");

        cityField.setPreferredSize(new Dimension(125, 25));
        makeMoveButton.setPreferredSize(new Dimension(125, 25));

        computerLabel.setPreferredSize(new Dimension(125, 25));
        cityHintLabel.setPreferredSize(new Dimension(125, 25));

        panel0.add(cityField);
        panel0.add(cityHintLabel);
        panel1.add(makeMoveButton);
        panel1.add(computerLabel);

        getRootPanel().add(panel0);
        getRootPanel().add(panel1);

        makeMoveButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == makeMoveButton) {
            dispose();
            //new GameWindow();
        }
    }
}
