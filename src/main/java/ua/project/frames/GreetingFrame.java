package ua.project.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GreetingFrame extends SuperFrame implements ActionListener {
    JMenuBar menuBar;
    JMenu mainMenu;
    JMenuItem loaderSettingsMenu;
    JMenuItem lobbySettingsMenu;

    JLabel lb;
    JButton b;

    public GreetingFrame() {
        super("Вітаємо!", new Dimension(350, 150));
    }

    @Override
    public void drawElements() {
        menuBar = new JMenuBar();
        mainMenu = new JMenu("Налаштування");

        loaderSettingsMenu = new JMenuItem("Варіант гри");
        lobbySettingsMenu = new JMenuItem("Змінити лоббі гри");

        mainMenu.setPreferredSize(new Dimension(150, 25));
        loaderSettingsMenu.setPreferredSize(new Dimension(150, 25));
        lobbySettingsMenu.setPreferredSize(new Dimension(150, 25));

        mainMenu.add(loaderSettingsMenu);
        mainMenu.add(lobbySettingsMenu);
        menuBar.add(mainMenu);

        loaderSettingsMenu.addActionListener(this);
        lobbySettingsMenu.addActionListener(this);

        lb = new JLabel("Вітаємо вас у грі дитинства і всіх розумників!");

        b = new JButton("Розпочати гру");
        b.addActionListener(this);

        getRootPanel().add(menuBar);
        getRootPanel().add(lb);
        getRootPanel().add(b);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b) {
            dispose();
            new GameFrame();
        }
        if(e.getSource() == loaderSettingsMenu){
            new LoaderSettingsFrame();
        }
        if(e.getSource() == lobbySettingsMenu){
            new LobbySettingsFrame();
        }
    }
}
