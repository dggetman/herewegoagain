package ru.ssau.tk._onimeshki_._herewegoagain_.operations;

import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;

public class RightSteppingDifferentialOperator extends SteppingDifferentialOperator {
    public RightSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return x -> (function.apply(x + step) - function.apply(x)) / step;
    }
}
