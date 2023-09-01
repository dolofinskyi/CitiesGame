package ua.project.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoaderSettingsFrame extends SuperFrame implements ActionListener {
    JRadioButton offlineRadioButton;
    JRadioButton onlineRadioButton;
    JButton confirmButton;


    public LoaderSettingsFrame() {
        super("Варіант гри", new Dimension(300, 100));
    }

    @Override
    public void drawElements() {
        onlineRadioButton = new JRadioButton("Online");
        offlineRadioButton = new JRadioButton("Offline");
        confirmButton = new JButton("Зберегти");
        confirmButton.addActionListener(this);
        ButtonGroup bg = new ButtonGroup();
        bg.add(onlineRadioButton);
        bg.add(offlineRadioButton);

        getRootPanel().add(onlineRadioButton);
        getRootPanel().add(offlineRadioButton);
        getRootPanel().add(confirmButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton){
            if (onlineRadioButton.isSelected()) {
                // online
            }
            if (offlineRadioButton.isSelected()) {
                // offline
            }
            dispose();
        }
    }
}
