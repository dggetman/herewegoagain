package ru.ssau.tk._onimeshki_._herewegoagain_.operations;

import ru.ssau.tk._onimeshki_._herewegoagain_.functions.Point;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.TabulatedFunction;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory.TabulatedFunctionFactory;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    private TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {

        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        int length = points.length - 1;
        double[] xValues = new double[length + 1];
        double[] yValues = new double[length + 1];

        for (int i = 0; i < length; i++) {
            yValues[i] = (points[i + 1].y - points[i].y) / (points[i + 1].x - points[i].x);
            xValues[i] = points[i].x;
        }

        xValues[length] = points[length].x;
        yValues[length] = yValues[length - 1];

        return factory.create(xValues, yValues);
    }
}
