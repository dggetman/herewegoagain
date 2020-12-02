package ru.ssau.tk._onimeshki_._herewegoagain_.concurrent;

import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;

public class MultiplyingTask implements Runnable {
    private final TabulatedFunction tabulatedFunctionFunction;
    private Runnable postRunAction;

    public MultiplyingTask(TabulatedFunction func) {
        this.tabulatedFunctionFunction = func;
    }

    public MultiplyingTask(TabulatedFunction func, Runnable postRunAction) {
        this.tabulatedFunctionFunction = func;
        this.postRunAction = postRunAction;
    }

    @Override
    public void run() {
        double x;
        double y;
        for (int i = 0; i < tabulatedFunctionFunction.getCount(); i++) {
            x = tabulatedFunctionFunction.getX(i);
            synchronized (tabulatedFunctionFunction) {
                y = tabulatedFunctionFunction.getY(i);
                System.out.printf("%s, i = %d, x = %f, old y = %f \n", Thread.currentThread().getName(), i, x, y);
                tabulatedFunctionFunction.setY(i, y * 10);
                y = tabulatedFunctionFunction.getY(i);
            }
            System.out.printf("%s, i = %d, x = %f, new y = %f \n", Thread.currentThread().getName(), i, x, y);
        }
        postRunAction.run();
    }
}
