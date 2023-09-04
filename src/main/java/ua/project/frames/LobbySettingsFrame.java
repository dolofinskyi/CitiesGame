package ua.project.frames;

import ua.project.users.ComputerPlayer;
import ua.project.users.HumanPlayer;
import ua.project.users.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LobbySettingsFrame extends SuperFrame implements ActionListener {
    JTextField playerField;
    JButton addPlayerButton;
    JButton removePlayerButton;
    JButton confirmButton;
    JTextArea playersArea;
    JLabel lobbyLabel;

    public LobbySettingsFrame() {
        super("Лоббі гри", new Dimension(300, 340));
        refreshStats();
    }

    @Override
    public void drawElements() {
        JPanel panel0 = new JPanel();
        panel0.setLayout(new FlowLayout());
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());

        playerField = new JTextField();
        playerField.setPreferredSize(new Dimension(100, 25));
        lobbyLabel = new JLabel("Нікнейм має бути від 4 до 16 символів");
        lobbyLabel.setVisible(false);
        addPlayerButton = new JButton("Додати");
        addPlayerButton.setPreferredSize(new Dimension(100, 25));
        removePlayerButton = new JButton("Видалити");
        removePlayerButton.setPreferredSize(new Dimension(100, 25));
        confirmButton = new JButton("Зберегти");
        confirmButton.setPreferredSize(new Dimension(100, 25));

        addPlayerButton.addActionListener(this);
        removePlayerButton.addActionListener(this);
        confirmButton.addActionListener(this);

        playersArea = new JTextArea();
        playersArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(playersArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(200, 150));
        panel0.add(playerField);
        panel0.add(confirmButton);

        panel1.add(removePlayerButton);
        panel1.add(addPlayerButton);

        panel2.add(lobbyLabel);
        panel3.add(scroll);

        panel0.setPreferredSize(new Dimension(225, 30));
        panel1.setPreferredSize(new Dimension(225, 30));
        panel2.setPreferredSize(new Dimension(225, 25));
        panel3.setPreferredSize(new Dimension(225, 150));

        getRootPanel().add(panel0);
        getRootPanel().add(panel1);
        getRootPanel().add(panel2);
        getRootPanel().add(panel3);
    }

    private void refreshStats(){
        playersArea.setText(String.join("\n", game.players.stream()
                .map(Player::getName)
                .toList()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPlayerButton){
            String nickname = playerField.getText().strip();

            if (nickname.length() < 4 || nickname.length() > 16){
                lobbyLabel.setVisible(true);
                return;
            }

            if (game.getPlayerByNickname(nickname) == null){
                if (nickname.equals("Comp")) {
                    game.players.add(new ComputerPlayer(nickname));
                } else{
                    game.players.add(new HumanPlayer(nickname));
                }
            }

            lobbyLabel.setVisible(false);
            playerField.setText("");
            refreshStats();
        }

        if (e.getSource() == removePlayerButton){
            if (game.players.size() > 0) {
                game.players.removeLast();
            }
            refreshStats();
        }

        if (e.getSource() == confirmButton){
            if (game.players.size() >= 2){
                dispose();
            }
        }
    }
}
