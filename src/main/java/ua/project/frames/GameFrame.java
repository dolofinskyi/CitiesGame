package ua.project.frames;

import ua.project.logic.Game;
import ua.project.logic.MoveState;
import ua.project.users.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends SuperFrame implements ActionListener {
    static JLabel cityHintLabel;
    static JLabel computerLabel;
    JLabel moveStatusLabel;
    JTextField cityField;
    JButton makeMoveButton;
    static JButton giveUpButton;
    static JTextArea summaryField;

    public GameFrame() {
        super("Міста", new Dimension(560, 170));
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
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel panel3 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));

        cityField = new JTextField();
        cityField.setPreferredSize(new Dimension(125, 25));

        makeMoveButton = new JButton("Зробити хід");
        makeMoveButton.setPreferredSize(new Dimension(125, 25));

        giveUpButton = new JButton("Здаюся");
        giveUpButton.setPreferredSize(new Dimension(125, 25));

        cityHintLabel = new JLabel("Введіть назву міста");
        cityHintLabel.setPreferredSize(new Dimension(225, 25));

        computerLabel = new JLabel("Комп'ютер: ");
        computerLabel.setPreferredSize(new Dimension(225, 25));

        moveStatusLabel = new JLabel("");
        moveStatusLabel.setPreferredSize(new Dimension(225, 25));

        summaryField = new JTextArea();
        summaryField.setPreferredSize(new Dimension(125, 90));
        //summaryField.setEnabled(false);
        //summaryField.setBackground(this.getBackground());

        panel0.add(cityField);
        panel0.add(cityHintLabel);

        panel1.add(makeMoveButton);
        panel1.add(computerLabel);

        panel2.add(giveUpButton);
        panel2.add(moveStatusLabel);

        panel3.add(summaryField);

        panelLeft.add(panel0);
        panelLeft.add(panel1);
        panelLeft.add(panel2);
        panelRight.add(panel3);

        getRootPanel().add(panelLeft);
        getRootPanel().add(panelRight);

        makeMoveButton.addActionListener(this);
        giveUpButton.addActionListener(this);
    }
    private static void checkStatusGame() {
        Player curPlayer = game.players.getFirst();
        Player prevPlayer = game.players.getLast();
        summaryField.setText(game.checkPlayersStatus());
        if (!game.getLastSymbol().isEmpty() && !prevPlayer.isHuman()) {
            computerLabel.setText("Комп'ютер: " + game.getCurCity());
        } else {
            computerLabel.setText("Комп'ютер: ");
        }

        if (game.gameCanGoOn()) {
            cityHintLabel.setText(curPlayer.getName() + " вам на " + "\"" + game.getLastSymbol() + "\"");
        } else if (game.getWinner() != null) {
            cityHintLabel.setText("Переміг " + "\"" + game.getWinner().getName() + "\"");
            computerLabel.setText("Комп'ютер: ");
            giveUpButton.setEnabled(false);
        }
//        if (!cityGame.gameCanGoOn()) {
//            stopGame();
//        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == makeMoveButton) {
            Player curPlayer = game.players.getFirst();
            if (curPlayer.isHuman()) {
                boolean result = game.processGame(cityField.getText());
                if (result) {
                    cityField.setText("");
                    moveStatusLabel.setText("");
                    checkStatusGame();
                } else {
                    moveStatusLabel.setText("Місто введене не вірно");
                }
            }
            //dispose();
            //new GameWindow();
        } else if (e.getSource() == giveUpButton) {
            game.processGame(MoveState.GIVEUP.name());
            checkStatusGame();
        }
    }
}
