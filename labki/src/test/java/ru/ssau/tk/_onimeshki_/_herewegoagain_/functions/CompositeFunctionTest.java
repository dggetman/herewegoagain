package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

public class CompositeFunctionTest {
    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        MathFunction identityFunction = new IdentityFunction();
        MathFunction sqrtFunction = new SqrtFunction();
        MathFunction identitySqrtFunction = new CompositeFunction(identityFunction, sqrtFunction);
        MathFunction absFunction = new AbsFunction();
        assertEquals(identitySqrtFunction.apply(36), 6, DELTA);
        MathFunction sqrFunction = new SqrFunction();
        MathFunction sqrSqrtFunction = new CompositeFunction(sqrFunction, sqrtFunction);
        assertEquals(sqrSqrtFunction.apply(-6), Math.sqrt(36), DELTA);
        MathFunction composite = absFunction.andThen(sqrtFunction).andThen(sqrFunction);
        assertEquals(composite.apply(-6.66), 6.66, DELTA);
        double result = sqrtFunction.andThen(absFunction).andThen(sqrFunction).apply(6.66);
        assertEquals(result, 6.66, DELTA);
    }
}