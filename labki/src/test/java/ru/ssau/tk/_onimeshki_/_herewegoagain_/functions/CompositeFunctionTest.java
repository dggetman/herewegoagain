package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

public class CompositeFunctionTest {
    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        MathFunction functionH = new IdentityFunction();
        MathFunction functionG = new SqrtFunction();
        MathFunction functionF = new CompositeFunction(functionH, functionG);
        MathFunction abs = new AbsFunction();
        MathFunction sqrt = new SqrtFunction();
        assertEquals(functionF.apply(36), 6, DELTA);


        MathFunction sqr = new SqrFunction();
        MathFunction funcF = new CompositeFunction(sqr, sqrt);
        assertEquals(funcF.apply(6), Math.sqrt(36), DELTA);


        MathFunction composite = abs.andThen(sqrt).andThen(sqr);
        assertEquals(composite.apply(-6.66), 6.66, DELTA);
        double result = sqrt.andThen(abs).andThen(sqr).apply(6.66);
        assertEquals(result, 6.66, DELTA);


    }
}