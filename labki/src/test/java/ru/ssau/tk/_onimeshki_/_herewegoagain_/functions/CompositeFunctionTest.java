package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        MathFunction functionH = new IdentityFunction();
        MathFunction functionG = new SqrtFunction();
        MathFunction functionF = new CompositeFunction(functionH, functionG);
        assertEquals(functionF.apply(8), 4, DELTA);
        MathFunction abs = new AbsFunction();
        MathFunction sqrt = new SqrtFunction();
        MathFunction funcF = new CompositeFunction(sqrt, abs);
    }
}