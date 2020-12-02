package ru.ssau.tk._onimeshki_._herewegoagain_.operations;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory.*;
import static ru.ssau.tk._onimeshki_._herewegoagain_.functions.Constants.DELTA;

import static org.testng.Assert.*;


public class TabulatedDifferentialOperatorTest {

    private final double[] xValues = new double[]{1, 2, 3, 4, 5, 6};
    private final double[] yValues = new double[]{11, 22, 33, 44, 55, 66};

    @Test
    public void testDerive() {
        TabulatedFunction testArray = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedDifferentialOperator differentialArrayOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        testArray = differentialArrayOperator.derive(testArray);
        assertTrue(testArray instanceof ArrayTabulatedFunction);
        assertEquals(testArray.getX(0), 1, DELTA);
        assertEquals(testArray.getX(2), 3, DELTA);
        assertEquals(testArray.getX(4), 5, DELTA);
        assertEquals(testArray.getY(0), 11, DELTA);
        assertEquals(testArray.getY(2), 11, DELTA);

        TabulatedFunction testList = new LinkedListTabulatedFunction(xValues, yValues);
        TabulatedDifferentialOperator differentialListOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        testList = differentialListOperator.derive(testList);
        assertTrue(testList instanceof LinkedListTabulatedFunction);
        assertEquals(testList.getX(0), 1, DELTA);
        assertEquals(testList.getX(2), 3, DELTA);
        assertEquals(testList.getX(4), 5, DELTA);
        assertEquals(testList.getY(0), 11, DELTA);
        assertEquals(testList.getY(2), 11, DELTA);
    }

    @Test
    public void testDeriveSynchronously() {
        TabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(xValues, yValues);
        TabulatedDifferentialOperator lDifferentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction diffFunctionList = lDifferentialOperator.deriveSynchronously(linkedListTabulatedFunction);
        TabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedDifferentialOperator arrDifferentialOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction diffFunctionArray = arrDifferentialOperator.deriveSynchronously(arrayTabulatedFunction);

        assertEquals(diffFunctionList.getX(0), 1, DELTA);
        assertEquals(diffFunctionList.getX(2), 3, DELTA);
        assertEquals(diffFunctionList.getY(0), 11, DELTA);
        assertEquals(diffFunctionList.getY(2), 11, DELTA);

        assertEquals(diffFunctionArray.getX(0), 1, DELTA);
        assertEquals(diffFunctionArray.getX(2), 3, DELTA);
        assertEquals(diffFunctionArray.getY(0), 11, DELTA);
        assertEquals(diffFunctionArray.getY(2), 11, DELTA);
    }
}