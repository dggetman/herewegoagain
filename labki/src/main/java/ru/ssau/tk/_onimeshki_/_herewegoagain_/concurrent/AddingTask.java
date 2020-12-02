package ru.ssau.tk._onimeshki_._herewegoagain_.concurrent;

import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;

public class AddingTask implements Runnable {
    private final TabulatedFunction tabulatedFunctionfunction;
    private Runnable postRunAction;

    public AddingTask(TabulatedFunction func) {
        this.tabulatedFunctionfunction = func;
    }

    public AddingTask(TabulatedFunction func, Runnable postRunAction) {
        this.tabulatedFunctionfunction = func;
        this.postRunAction = postRunAction;
    }

    @Override
    public void run() {
        double x;
        double y;
        for (int i = 0; i < tabulatedFunctionfunction.getCount(); i++) {
            x = tabulatedFunctionfunction.getX(i);
            synchronized (tabulatedFunctionfunction) {
                y = tabulatedFunctionfunction.getY(i);
                System.out.printf("%s, i = %d, x = %f, old y = %f \n", Thread.currentThread().getName(), i, x, y);
                tabulatedFunctionfunction.setY(i, y + 3);
                y = tabulatedFunctionfunction.getY(i);
            }
            System.out.printf("%s, i = %d, x = %f, new y = %f \n", Thread.currentThread().getName(), i, x, y);
        }
        postRunAction.run();
    }
}
