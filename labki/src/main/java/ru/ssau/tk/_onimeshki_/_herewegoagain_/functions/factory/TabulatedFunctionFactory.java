package ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory;


import ru.ssau.tk._onimeshki_._herewegoagain_.functions.TabulatedFunction;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.StrictTabulatedFunction;


public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);

    default TabulatedFunction createStrict(double[] xValues, double[] yValues) {
        return new StrictTabulatedFunction(create(xValues, yValues));
    }

}
