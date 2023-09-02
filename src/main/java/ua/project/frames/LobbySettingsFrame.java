package ua.project.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LobbySettingsFrame extends SuperFrame implements ActionListener {
    JTextField playerField;
    JButton addPlayerButton;
    JButton removePlayerButton;
    JButton confirmButton;
    JTextArea playersArea;

    // Game.players або List<AbstractClassOfPlayer> players;
    ArrayList<String> players = new ArrayList<>();

    public LobbySettingsFrame() {
        super("Лоббі гри", new Dimension(300, 300));
    }

    @Override
    public void drawElements() {
        JPanel panel0 = new JPanel();
        panel0.setLayout(new FlowLayout());
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());

        playerField = new JTextField();
        playerField.setPreferredSize(new Dimension(100, 25));

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

        panel2.add(scroll);

        getRootPanel().add(panel0);
        getRootPanel().add(panel1);
        getRootPanel().add(panel2);
    }

    private void refreshStats(){
        playersArea.setText(String.join("\n", players));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPlayerButton){
            String nickname = playerField.getText().strip();

            if (nickname.length() < 5 || nickname.length() > 15){
                // не підходить
                return;
            }

            if (!players.contains(nickname)){
                players.add(nickname);
            }

            refreshStats();
        }

        if (e.getSource() == removePlayerButton){
            String nickname = playerField.getText().strip();
            players.remove(nickname);
            refreshStats();
        }

        if (e.getSource() == confirmButton){
            dispose();
            // game.players = players;
        }
    }
}
