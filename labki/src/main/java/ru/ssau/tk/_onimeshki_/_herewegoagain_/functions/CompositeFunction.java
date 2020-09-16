package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

public class CompositeFunction implements MathFunction {
    private MathFunction functionH;
    private MathFunction functionG;

    public CompositeFunction(MathFunction functionH, MathFunction functionG) {
        this.functionG = functionH;
        this.functionH = functionG;
    }

    public double apply(double x) {
        return functionG.apply(functionH.apply(x));
    }
}
