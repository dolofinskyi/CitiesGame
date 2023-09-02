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
    JTextArea summaryField;

    public GameFrame() {
        super("Міста", new Dimension(460, 170));
    }

    @Override
    public void drawElements() {
        JPanel panelLeft = new JPanel();
        BoxLayout layoutPanelIn = new BoxLayout(panelLeft, BoxLayout.Y_AXIS);
        panelLeft.setLayout(layoutPanelIn);

        JPanel panelRight = new JPanel();
        BoxLayout layoutPanelOut = new BoxLayout(panelRight, BoxLayout.Y_AXIS);
        panelRight.setLayout(layoutPanelOut);

        JPanel panel0 = new JPanel();
        panel0.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));

        cityHintLabel = new JLabel("Введіть назву міста");
        computerLabel = new JLabel("Комп'ютер: ");
        cityField = new JTextField();
        makeMoveButton = new JButton("Зробити хід");

        cityField.setPreferredSize(new Dimension(125, 25));
        makeMoveButton.setPreferredSize(new Dimension(125, 25));

        computerLabel.setPreferredSize(new Dimension(125, 25));
        cityHintLabel.setPreferredSize(new Dimension(125, 25));

        summaryField = new JTextArea();
        summaryField.setPreferredSize(new Dimension(125, 90));

        panel0.add(cityField);
        panel0.add(cityHintLabel);
        panel1.add(makeMoveButton);
        panel1.add(computerLabel);
        panel2.add(summaryField);

        panelLeft.add(panel0);
        panelLeft.add(panel1);
        panelRight.add(panel2);

        getRootPanel().add(panelLeft);
        getRootPanel().add(panelRight);

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
