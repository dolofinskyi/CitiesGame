package ua.project.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoaderSettingsFrame extends SuperFrame implements ActionListener {
    JCheckBox loaderCheckBox;
    JButton confirmButton;

    public LoaderSettingsFrame() {
        super("Варіант гри", new Dimension(300, 100));
    }

    @Override
    public void drawElements() {
        loaderCheckBox = new JCheckBox("Онлайн");
        confirmButton = new JButton("Зберегти");
        confirmButton.addActionListener(this);
        getRootPanel().add(loaderCheckBox);
        getRootPanel().add(confirmButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton){
            game.reloadCities(loaderCheckBox.isSelected());
            dispose();
        }
    }
}
