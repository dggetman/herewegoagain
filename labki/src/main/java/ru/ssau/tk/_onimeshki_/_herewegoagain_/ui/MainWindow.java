package ru.ssau.tk._onimeshki_._herewegoagain_.ui;

import ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.List;

public class MainWindow extends JFrame {
    private final JButton buttonCreateTFunction = new JButton("Создать табулированную функцию");
    private final JButton buttonSettings = new JButton("Настройки");
    private final JButton inputButtonMath = new JButton("Поэлементные операции");
    private final JButton buttonDifferentiation = new JButton("Дифференцирование");
    private JButton openButton = new JButton("Открыть функцию");
    private JButton saveButton = new JButton("Сохранить функцию");
    private List<Double> xValues = new ArrayList<>();
    private List<Double> yValues = new ArrayList<>();
    private ModelMainWindow tableModel = new ModelMainWindow();
    private JTable table = new JTable(tableModel);
    private TabulatedFunctionFactory factory;

    public static Audio error;

    public MainWindow() {
        super("☻");
        setIconImage(getImage("icon"));
        getContentPane().setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 1000, 500);
        getContentPane().add(buttonCreateTFunction);
        getContentPane().add(inputButtonMath);
        getContentPane().add(buttonSettings);
        getContentPane().add(buttonDifferentiation);
        getContentPane().add(openButton);
        getContentPane().add(saveButton);
        setLocationRelativeTo(null);
        actionPerformed();
        compose();
        addButtonListeners();
        setVisible(true);

        Audio error = new Audio("res/Bird.wav",0.8);
        error.play();//играть звук
    }

    private Image getImage(String name) {
        ImageIcon icon = new ImageIcon("res/" + name.toLowerCase() + ".png");
        return icon.getImage();

    }

    public void wrapTable(int countOld, int countNew) {
        tableModel.fireTableDataChanged();
        for (int i = 0; i < countOld; i++) {
            if (xValues.size() != 0) xValues.remove(countOld - i - 1);
            if (yValues.size() != 0) yValues.remove(countOld - i - 1);
        }
        for (int i = 0; i < countNew; i++) {
            xValues.add(tableModel.getFunction().getX(i));
            yValues.add(tableModel.getFunction().getY(i));
        }
    }


    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPane = new JScrollPane(table);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonCreateTFunction)
                        .addComponent(inputButtonMath)
                        .addComponent(buttonSettings)
                        .addComponent(openButton)
                        .addComponent(saveButton))
                .addComponent(tableScrollPane)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(buttonCreateTFunction)
                        .addComponent(inputButtonMath)
                        .addComponent(buttonSettings)
                        .addComponent(openButton)
                        .addComponent(saveButton))
                .addComponent(tableScrollPane)
        );
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

    public void actionPerformed() {
        buttonCreateTFunction.addActionListener(event -> {
                    try {
                        int countOld = xValues.size();
                        TabulatedFunctionWindow.main(factory, data -> tableModel.setFunction(data));
                        int countNew = tableModel.getFunction().getCount();
                        wrapTable(countOld, countNew);
                    } catch (Exception e) {
                        if (e instanceof NullPointerException) {
                            e.printStackTrace();
                        } else
                            new ErrorWindow(this, e);
                    }
                }
        );
        inputButtonMath.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                MathFunctionWindow.main(factory, data -> tableModel.setFunction(data));
                int countNew = tableModel.getFunction().getCount();
                wrapTable(countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorWindow(this, e);
            }
        });
        buttonSettings.addActionListener(event -> {
            try {
                SettingWindow.main(factory);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorWindow(this, e);
            }
        });
        openButton.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                FileReader.main(data -> tableModel.setFunction(data));
                int countNew = tableModel.getFunction().getCount();
                wrapTable(countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorWindow(this, e);
            }
        });
        saveButton.addActionListener(event -> {
            try {
                FileWriter.main(tableModel.getFunction());
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new ErrorWindow(this, e);
            }
        });
    }


}