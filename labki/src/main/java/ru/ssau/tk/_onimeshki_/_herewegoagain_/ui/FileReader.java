package ru.ssau.tk._onimeshki_._herewegoagain_.ui;

import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory.*;
import ru.ssau.tk._onimeshki_._herewegoagain_.io.FunctionsIO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.function.Consumer;


public class FileReader extends JDialog {
    private JTextField filename = new JTextField();
    private JTextField dir = new JTextField();
    private JButton open = new JButton("Открыть");
    private TabulatedFunction func;
    private TabulatedFunctionFactory factory;

    public FileReader(Consumer<? super TabulatedFunction> callback) {
        setModal(true);
        JPanel p = new JPanel();
        addListenerForOpenButton(callback);
        p.add(open);
        Container cp = getContentPane();
        cp.add(p, BorderLayout.SOUTH);
        dir.setEditable(false);
        filename.setEditable(false);
        p = new JPanel();
        p.setLayout(new GridLayout(2, 1));
        p.add(filename);
        p.add(dir);
        cp.add(p, BorderLayout.NORTH);
        setLocationRelativeTo(null);
    }

    public void addListenerForOpenButton(Consumer<? super TabulatedFunction> callback) {
        open.addActionListener(event -> {
            JFileChooser c = new JFileChooser();
            c.setFileSelectionMode(JFileChooser.FILES_ONLY);
            c.addChoosableFileFilter(
                    new FileNameExtensionFilter("Text files", "txt"));
            c.setAcceptAllFileFilterUsed(false);
            int rVal = c.showOpenDialog(FileReader.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                filename.setText(c.getSelectedFile().getName());
                dir.setText(c.getCurrentDirectory().toString());
                File file = c.getSelectedFile();
                factory = new ArrayTabulatedFunctionFactory();
                if (file != null) {
                    try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                        func = FunctionsIO.readTabulatedFunction(inputStream, factory);
                        callback.accept(func);
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

    public static void main(Consumer<? super TabulatedFunction> callback) {
        run(new FileReader(callback), 250, 110);
    }

    public static void run(JDialog frame, int width, int height) {
        frame.setSize(width, height);
        frame.setVisible(true);
    }
}
