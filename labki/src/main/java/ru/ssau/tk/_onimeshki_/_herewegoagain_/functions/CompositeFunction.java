package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

public class CompositeFunction implements MathFunction {
    private final MathFunction functionH;
    private final MathFunction functionG;

    public CompositeFunction(MathFunction functionH, MathFunction functionG) {
        this.functionH = functionH;
        this.functionG = functionG;
    }

    public double apply(double x) {
        return functionG.apply(functionH.apply(x));
    }
}
