package ru.ssau.tk._onimeshki_._herewegoagain_.concurrent;

import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;

public class MultiplyingTask implements Runnable {
    private final TabulatedFunction tabulatedFunction;
    private Runnable postRunAction;

    public MultiplyingTask(TabulatedFunction func) {
        this.tabulatedFunction = func;
    }

    public MultiplyingTask(TabulatedFunction func, Runnable postRunAction) {
        this.tabulatedFunction = func;
        this.postRunAction = postRunAction;
    }

    @Override
    public void run() {
        double x;
        double y;
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            x = tabulatedFunction.getX(i);
            synchronized (tabulatedFunction) {
                y = tabulatedFunction.getY(i);
                System.out.printf("%s, i = %d, x = %f, old y = %f \n", Thread.currentThread().getName(), i, x, y);
                tabulatedFunction.setY(i, y * 10);
                y = tabulatedFunction.getY(i);
            }
            System.out.printf("%s, i = %d, x = %f, new y = %f \n", Thread.currentThread().getName(), i, x, y);
        }
        postRunAction.run();
    }
}
