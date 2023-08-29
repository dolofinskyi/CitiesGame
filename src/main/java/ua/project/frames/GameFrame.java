package ua.project.frames;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {

    JLabel cityHintLabel;
    JLabel computerLabel;

    JTextField cityField;
    JButton makeMoveButton;

    final Font font = new Font(Font.DIALOG, Font.BOLD, 14);

    public GameFrame() {
        super("Міста");
        drawElements();
        configureFrame();
    }

    private void drawElements() {
        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.PAGE_AXIS));

        Border margin = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        rootPanel.setBorder(margin);

        JPanel panel0 = new JPanel();
        panel0.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        cityHintLabel = new JLabel("Введіть назву міста");
        computerLabel = new JLabel("Комп'ютер: ");
        cityField = new JTextField(12);
        makeMoveButton = new JButton("Зробити хід");

        cityField.setPreferredSize(new Dimension(125, 25));
        makeMoveButton.setPreferredSize(new Dimension(125, 25));

        makeMoveButton.setFont(font);
        cityHintLabel.setFont(font);
        cityHintLabel.setFont(font);

        panel0.add(cityField);
        panel0.add(cityHintLabel);
        panel1.add(makeMoveButton);
        panel1.add(computerLabel);

        rootPanel.add(panel0);
        rootPanel.add(panel1);

        setContentPane(rootPanel);
        makeMoveButton.addActionListener(this);
    }

    private void configureFrame() {
        setSize(360, 140);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == makeMoveButton) {
            dispose();
            //new GameWindow();
        }
    }
}
