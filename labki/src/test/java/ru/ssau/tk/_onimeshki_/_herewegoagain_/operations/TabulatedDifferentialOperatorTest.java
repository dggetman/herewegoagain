package ru.ssau.tk._onimeshki_._herewegoagain_.operations;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.TabulatedFunction;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory.LinkedListTabulatedFunctionFactory;
import static ru.ssau.tk._onimeshki_._herewegoagain_.functions.Constants.DELTA;

import static org.testng.Assert.*;


public class TabulatedDifferentialOperatorTest {
    private final double[] xValues = new double[]{1, 2, 3, 4, 5};
    private final double[] yValues = new double[]{11, 22, 33, 44, 55};

    @Test
    public void testDerive() {
        TabulatedFunction testArrayFunction = new LinkedListTabulatedFunction(xValues, yValues);
        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction diffFunction = differentialOperator.derive(testArrayFunction);
        assertEquals(diffFunction.getX(0), 1, DELTA);
        assertEquals(diffFunction.getX(2), 3, DELTA);
        assertEquals(diffFunction.getX(4), 5, DELTA);
        assertEquals(diffFunction.getY(0), 11, DELTA);
        assertEquals(diffFunction.getY(2), 11, DELTA);
        assertEquals(diffFunction.getY(4), 55, DELTA);
    }
}