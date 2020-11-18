package ru.ssau.tk._onimeshki_._herewegoagain_.operations;

import ru.ssau.tk._onimeshki_._herewegoagain_.exceptions.*;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory.*;

public class TabulatedFunctionOperationService {

    private TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionOperationService() {
        factory = new ArrayTabulatedFunctionFactory();
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] points = new Point[tabulatedFunction.getCount()];
        for (Point newPoint : tabulatedFunction) {
            points[i++] = newPoint;
        }
        return points;
    }

    @FunctionalInterface
    private interface BiOperation {
        double apply(double u, double v);
    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {
        if (a.getCount() != b.getCount()) {
            throw new InconsistentFunctionsException("The number of tabulated points in the tabulated functions is different");
        }
        Point[] aPoints = asPoints(a);
        Point[] bPoints = asPoints(b);
        double[] xValues = new double[a.getCount()];
        double[] yValues = new double[a.getCount()];
        for (int i = 0; i < a.getCount(); i++) {
            final double DELTA = 0.00001;
            if (Math.abs(aPoints[i].x - bPoints[i].x) > DELTA) {
                throw new InconsistentFunctionsException("X-coordinates of the functions are various");
            }
            xValues[i] = aPoints[i].x;
            yValues[i] = operation.apply(aPoints[i].y, bPoints[i].y);
        }
        return factory.create(xValues, yValues);
    }

    public TabulatedFunction add(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, Double::sum);
    }

    public TabulatedFunction subtract(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u - v);
    }

    public TabulatedFunction multiply(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u * v);
    }

    public TabulatedFunction divide(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u / v);
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

}
