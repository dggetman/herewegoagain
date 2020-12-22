package ru.ssau.tk._onimeshki_._herewegoagain_.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

public class MainWindow extends JFrame {
    private final JButton buttonCreateTFunction = new JButton("Создать табулированную функцию");
    private final JButton buttonSettings = new JButton("Настройки");
    private final JButton buttonDifferentiation = new JButton("Дифференцирование");
    private final JButton buttonSimpleOperations = new JButton("Поэлементные операции");
    private JButton openButton = new JButton("Открыть функцию");
    private JButton saveButton = new JButton("Сохранить функцию");



    public MainWindow() {
        super("☻");
        getContentPane().setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 500, 500);
        getContentPane().add(buttonCreateTFunction);
        getContentPane().add(buttonSimpleOperations);
        getContentPane().add(buttonSettings);
        getContentPane().add(buttonDifferentiation);
        getContentPane().add(openButton);
        getContentPane().add(saveButton);
        //actionPerformed();
        compose();
        addButtonListeners();
        setVisible(true);
    }

    private void compose() {
    }

    private void addButtonListeners() {
        addListenerForInputButton();
        addListenerForCreateButton();
        addListenerForCountButton();
    }

    private void addListenerForCountButton() {
    }

    private void addListenerForCreateButton() {
    }

    private void addListenerForInputButton() {
    }

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        window.setVisible(true);
    }
}