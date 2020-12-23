package ru.ssau.tk._onimeshki_._herewegoagain_.ui;

import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory.*;
import ru.ssau.tk._onimeshki_._herewegoagain_.io.FunctionsIO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

public class FileWriter extends JDialog {
    private JTextField filename = new JTextField();
    private JTextField dir = new JTextField();
    private JButton save = new JButton("Сохранить");
    private TabulatedFunction func;

    public FileWriter(TabulatedFunction func) {
        setModal(true);
        this.func = func;
        JPanel p = new JPanel();
        addListenerForSaveButton();
        p.add(save);
        Container cp = getContentPane();
        cp.add(p, BorderLayout.SOUTH);
        dir.setEditable(false);
        filename.setEditable(false);
        p = new JPanel();
        p.setLayout(new GridLayout(2, 1));
        p.add(filename);
        p.add(dir);
        cp.add(p, BorderLayout.NORTH);
    }

    public void addListenerForSaveButton() {
        save.addActionListener(event -> {
            JFileChooser c = new JFileChooser();
            c.setFileSelectionMode(JFileChooser.FILES_ONLY);
            c.addChoosableFileFilter(
                    new FileNameExtensionFilter("Text files", "txt"));
            c.setAcceptAllFileFilterUsed(false);
            int rVal = c.showSaveDialog(FileWriter.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                filename.setText(c.getSelectedFile().getName());
                dir.setText(c.getCurrentDirectory().toString());
                File file = c.getSelectedFile();
                if (file != null) {
                    try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                        FunctionsIO.writeTabulatedFunction(outputStream, func);
                    } catch (Exception e) {
                        new ErrorWindow(this, e);
                    }
                }
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {
                filename.setText("Вы отменили операцию");
                dir.setText("");
            }
        });
    }

    public static void main(TabulatedFunction func) {
        run(new FileWriter(func), 250, 110);
    }

    public static void run(JDialog frame, int width, int height) {
        frame.setSize(width, height);
        frame.setVisible(true);
    }
}
