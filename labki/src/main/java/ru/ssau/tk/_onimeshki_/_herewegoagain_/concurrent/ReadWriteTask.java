package ru.ssau.tk._onimeshki_._herewegoagain_.concurrent;

import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;

public class ReadWriteTask implements Runnable {
    private final TabulatedFunction function;

    public ReadWriteTask(TabulatedFunction function) {
        this.function = function;
    }

    @Override
    public void run() {
        double x ;
        double y ;
        for (int i = 0; i < function.getCount(); i++) {
            x = function.getX(i);
            synchronized (function) {
                y = function.getY(i);
                System.out.println(Thread.currentThread().getName() + ", before write: i = " + i + ", x = " + x + ", y = " + y);
                function.setY(i, y + 1);
                y = function.getY(i);
            }

            System.out.println(Thread.currentThread().getName() + ", after write: i = " + i + ", x = " + x + ", y = " + y + "\n");
        }
    }
}