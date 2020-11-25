package ru.ssau.tk._onimeshki_._herewegoagain_.operations;

import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {
    protected double step;

    public SteppingDifferentialOperator(double step) {
        if (step <= 0 || Double.isInfinite(step) || Double.isNaN(step)) {
            throw new IllegalArgumentException("Step is nonpositive number or Null or infinity or NaN");
        }
        this.step = step;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }
}
