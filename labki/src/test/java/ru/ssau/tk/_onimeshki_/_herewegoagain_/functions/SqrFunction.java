package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

public class SqrFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return Math.pow(x,2);
    }
}
