package ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory;

import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
}
