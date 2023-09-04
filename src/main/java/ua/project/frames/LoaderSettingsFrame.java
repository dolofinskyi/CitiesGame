package ua.project.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoaderSettingsFrame extends SuperFrame implements ActionListener {
    JCheckBox loaderCheckBox;
    JButton confirmButton;
    JLabel loaderLabel;

    public LoaderSettingsFrame() {
        super("Варіант гри", new Dimension(300, 100));
    }

    @Override
    public void drawElements() {
        loaderCheckBox = new JCheckBox("Онлайн");
        confirmButton = new JButton("Зберегти");
        loaderLabel = new JLabel("Завантаження...");
        loaderLabel.setVisible(false);
        confirmButton.addActionListener(this);
        getRootPanel().add(loaderCheckBox);
        getRootPanel().add(loaderLabel);
        getRootPanel().add(confirmButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton){
            Runnable task = () -> {
                synchronized (game){
                    confirmButton.setEnabled(false);
                    loaderLabel.setVisible(true);
                    setSize(new Dimension(350, 100));
                    game.reloadCities(loaderCheckBox.isSelected());
                    dispose();
                }
            };

            new Thread(task).start();
        }
    }
}
