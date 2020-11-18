package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class CompositeFunctionTest {

    private final double[] valuesX = new double[]{-3., -2., -1, -0., 1., 2., 3., 4., 5.};
    private final double[] valuesY = new double[]{-13., -4., -1., 0., 1., 4., 9., 13., 25.};


    @Test
    public void testApply() {
        MathFunction identityFunction = new IdentityFunction();
        MathFunction sqrtFunction = new SqrtFunction();
        MathFunction identitySqrtFunction = new CompositeFunction(identityFunction, sqrtFunction);
        MathFunction absFunction = new AbsFunction();
        Assert.assertEquals(identitySqrtFunction.apply(36), 6, Constants.DELTA);
        MathFunction sqrFunction = new SqrFunction();
        MathFunction sqrSqrtFunction = new CompositeFunction(sqrFunction, sqrtFunction);

        Assert.assertEquals(sqrSqrtFunction.apply(-6), Math.sqrt(36), Constants.DELTA);
        MathFunction composite = absFunction.andThen(sqrtFunction).andThen(sqrFunction);
        Assert.assertEquals(composite.apply(-6.66), 6.66, Constants.DELTA);
        double result = sqrtFunction.andThen(absFunction).andThen(sqrFunction).apply(6.66);
        Assert.assertEquals(result, 6.66, Constants.DELTA);

        MathFunction listFunction = new LinkedListTabulatedFunction(valuesX, valuesY);
        MathFunction arrayFunction = new ArrayTabulatedFunction(valuesX, valuesY);
        MathFunction arrayListSqrFunction = arrayFunction.andThen(listFunction).andThen(sqrFunction);
        Assert.assertEquals(arrayListSqrFunction.apply(1), 1, Constants.DELTA);
        Assert.assertEquals(arrayListSqrFunction.apply(2), 169, Constants.DELTA);

    }
}