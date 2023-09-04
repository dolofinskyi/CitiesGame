package ua.project.frames;

import ua.project.logic.MoveState;
import ua.project.users.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameFrame extends SuperFrame implements ActionListener {
    static JLabel cityHintLabel;
    static JLabel computerLabel;
    JLabel moveStatusLabel;
    JTextField cityField;
    static JButton makeMoveButton;
    static JButton giveUpButton;
    static JTextPane summaryField;
    private static ScheduledExecutorService executorService;

    public GameFrame() {
        super("Міста", new Dimension(560, 190));
        configureFrame();
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

        summaryField = new JTextPane();
        summaryField.setPreferredSize(new Dimension(125, 100));
        summaryField.setBackground(this.getBackground());
        summaryField.setContentType("text/html");

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

    private void configureFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private static void checkStatusGame() {
        Player curPlayer = game.players.getFirst();
        Player prevPlayer = game.players.getLast();

        summaryField.setText(game.checkPlayersStatus());

        makeMoveButton.setEnabled(curPlayer.isHuman());
        setComputerLabelText(prevPlayer);

        if (game.gameCanGoOn()) {
            setCityHintLabelText(curPlayer);
        } else {
            if (game.getWinner() != null) {
                cityHintLabel.setText("Переміг " + "\"" + game.getWinner().getName() + "\"");
                computerLabel.setText("Комп'ютер: ");
                giveUpButton.setEnabled(false);
            }
            stopGame();
        }
    }

    private static void setCityHintLabelText(Player curPlayer) {
        StringBuilder cityHintLabelText = new StringBuilder();
        String colorText = curPlayer.isHuman() ? "green" : "red";

        if (!game.getLastSymbol().isEmpty()) {
            cityHintLabelText.append("<html><font color='")
                    .append(colorText)
                    .append("'>")
                    .append(curPlayer.getName())
                    .append("</font> вам на ")
                    .append("\"")
                    .append(game.getLastSymbol())
                    .append("\"")
                    .append("</html>");
        } else {
            cityHintLabelText.append("<html><font color='")
                    .append(colorText)
                    .append("'>")
                    .append(curPlayer.getName())
                    .append("</font> введіть назву міста")
                    .append("</html>");
        }
        cityHintLabel.setText(cityHintLabelText.toString());
    }

    private static void setComputerLabelText(Player prevPlayer) {
        if (!game.getLastSymbol().isEmpty() && !prevPlayer.isHuman()) {
            computerLabel.setText("Комп'ютер: " + game.getCurCity());
        } else {
            computerLabel.setText("Комп'ютер: ");
        }
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
        } else if (e.getSource() == giveUpButton) {
            game.processGame(MoveState.GIVEUP.name());
            checkStatusGame();
        }
        if (game.getWinner() != null) {
            JDialog dialog = createDialog();
            dialog.setVisible(true);
        }
    }

    private JDialog createDialog() {
        JDialog dialog = new JDialog(this, "Є переможець!", true);
        JPanel rootPanel = new JPanel();
        rootPanel.setBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );
        JLabel congratLabel = new JLabel("<html>Переміг: <font color='blue'>" + game.getWinner().getName() + "</font></html>");
        rootPanel.add(congratLabel);
        dialog.add(rootPanel);

        dialog.setLocationRelativeTo(cityField);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(280, 90);
        return dialog;
    }

    public void startGame() {
        game.start();

        executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(GameFrame::checkStatusGame, 0, 1, TimeUnit.SECONDS);
    }

    private static void stopGame() {
        executorService.shutdown();
        game.interrupt();
    }
}
