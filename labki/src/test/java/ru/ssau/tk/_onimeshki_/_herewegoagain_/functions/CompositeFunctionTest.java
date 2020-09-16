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
        assertEquals(functionF.apply(36), 6, DELTA);
        MathFunction abs = new AbsFunction();
        MathFunction sqrt = new SqrtFunction();
        //MathFunction funcF = new CompositeFunction(sqrt, abs);
        //assertEquals(funcF.apply(6), Math.sqrt(36), DELTA);

        MathFunction sqr = new SqrFunction();
        MathFunction composite = abs.andThen(sqrt).andThen(sqr);
        double result = sqrt.andThen(abs).andThen(sqr).apply(6.66);



    }
}