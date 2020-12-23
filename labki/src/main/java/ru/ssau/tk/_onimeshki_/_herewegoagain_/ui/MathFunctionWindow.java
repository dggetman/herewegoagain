package ru.ssau.tk._onimeshki_._herewegoagain_.ui;

import ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory.*;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.util.*;
import java.util.function.Consumer;

public class MathFunctionWindow extends JDialog {
    private JComboBox<String> functionComboBox = new JComboBox<>();
    private JLabel fromLabel = new JLabel("От:");
    private JLabel toLabel = new JLabel("До:");
    private JLabel countLabel = new JLabel("Количество:");
    private JTextField countField = new JTextField();
    private JTextField fromField = new JTextField();
    private JTextField toField = new JTextField();
    private JButton okButton = new JButton("OK");
    private Map<String, MathFunction> nameFunctionMap = new HashMap<>();

    TabulatedFunctionFactory factory;
    TabulatedFunction function;

    public MathFunctionWindow(TabulatedFunctionFactory factory, Consumer<? super TabulatedFunction> callback) {
        setModal(true);
        this.factory = factory;
        this.setBounds(300, 200, 500, 150);
        fillMap();
        compose();
        addButtonListeners(callback);
        setLocationRelativeTo(null);
    }

    public static void main(TabulatedFunctionFactory factory, Consumer<? super TabulatedFunction> callback) {
        MathFunctionWindow app = new MathFunctionWindow(factory, callback);
        app.setVisible(true);
    }

    public void fillMap() {
        nameFunctionMap.put("Квадратичная функция", new SqrFunction());
        nameFunctionMap.put("Нулевая функция", new ZeroFunction());
        nameFunctionMap.put("Единичная функция", new UnitFunction());
        nameFunctionMap.put("Тождественная функция", new IdentityFunction());
        nameFunctionMap.put("Функция квадратного корня", new SqrtFunction());
        String[] functions = new String[5];
        int i = 0;
        for (String string : nameFunctionMap.keySet()) {
            functions[i++] = string;
        }
        Arrays.sort(functions);
        for (String string : functions) {
            functionComboBox.addItem(string);
        }
    }

    public void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(fromLabel)
                        .addComponent(fromField)
                        .addComponent(toLabel)
                        .addComponent(toField)
                        .addComponent(countLabel)
                        .addComponent(countField))
                .addComponent(functionComboBox)
                .addComponent(okButton)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fromLabel)
                        .addComponent(fromField)
                        .addComponent(toLabel)
                        .addComponent(toField)
                        .addComponent(countLabel)
                        .addComponent(countField))
                .addComponent(functionComboBox)
                .addComponent(okButton)
        );
    }

    public void addButtonListeners(Consumer<? super TabulatedFunction> callback) {
        okButton.addActionListener(event -> {
            try {
                String func = (String) functionComboBox.getSelectedItem();
                MathFunction selectedFunction = nameFunctionMap.get(func);
                double from = Double.parseDouble(fromField.getText());
                double to = Double.parseDouble(toField.getText());
                int count = Integer.parseInt(countField.getText());
                MathFunction  mathFunction = nameFunctionMap.get(func);
                function = new ArrayTabulatedFunctionFactory().create(mathFunction, from, to, count);
                callback.accept(function);
                this.dispose();
            } catch (Exception e) {
                ErrorWindow errorWindow = new ErrorWindow(this, e);
                errorWindow.showErrorWindow(this, e);
            }
        });
    }

}
