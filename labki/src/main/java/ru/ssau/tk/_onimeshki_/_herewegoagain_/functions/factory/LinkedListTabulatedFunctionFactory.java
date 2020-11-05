package ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory;

import ru.ssau.tk._onimeshki_._herewegoagain_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.TabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
}
