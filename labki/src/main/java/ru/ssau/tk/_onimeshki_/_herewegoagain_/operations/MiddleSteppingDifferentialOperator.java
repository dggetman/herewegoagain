package ru.ssau.tk._onimeshki_._herewegoagain_.operations;

import ru.ssau.tk._onimeshki_._herewegoagain_.functions.MathFunction;

public class MiddleSteppingDifferentialOperator extends SteppingDifferentialOperator {
    MiddleSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return x -> (function.apply(x + step) - function.apply(x - step)) / (2 * step);
    }
}
