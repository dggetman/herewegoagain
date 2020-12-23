package ru.ssau.tk._onimeshki_._herewegoagain_.ui;

import ru.ssau.tk._onimeshki_._herewegoagain_.exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ErrorWindow {
    public static Audio error;
    ErrorWindow(Component parent, Exception e) {
        showErrorWindow(parent, e);
    }

    public void showErrorWindow(Component parent, Exception e) {
        String head = generateMessageForException(e);
        ImageIcon  icon          = null;
        icon = new ImageIcon("res/icon.png");
        JOptionPane.showMessageDialog(parent, "Ошибка!", head, JOptionPane.INFORMATION_MESSAGE,icon);


        Audio error = new Audio("res/RIP ears.wav",0.8);
        error.repeat();//играть звук
    }


    private String generateMessageForException(Exception e) {
        if (e instanceof NumberFormatException) {
            return "Неверный формат числа";
        }
        if (e instanceof ArrayIsNotSortedException) {
            return "Массив точек неотсортирован";
        }
        if (e instanceof IOException) {
            return "Ошибка ввода/вывода";
        }
        if (e instanceof InconsistentFunctionsException) {
            return "Разная длина массивов";
        }
        if (e instanceof IllegalArgumentException) {
            return "Задана некорректная функция";
        }
        {
            e.printStackTrace();
            return "У тебя лапки♥";
        }
    }
}
