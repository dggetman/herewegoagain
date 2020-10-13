package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static ru.ssau.tk._onimeshki_._herewegoagain_.functions.Constants.DELTA;

public class ArrayTabulatedFunctionTest {
    private final double[] valuesX = new double[]{-3., -2., -1, -0., 1., 2., 3., 4., 5., 7., 9.};
    private final double[] valuesY = new double[]{-13., -4., -1., 0., 1., 4., 9., 13., 25.};
    private final MathFunction sqrFunc = new SqrFunction();

    private ArrayTabulatedFunction getDefinedThroughArrays() {
        return new ArrayTabulatedFunction(valuesX, valuesY);
    }

    private ArrayTabulatedFunction getDefinedThroughMathFunction() {
        return new ArrayTabulatedFunction(sqrFunc, 0, 9, 109);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(getDefinedThroughArrays().floorIndexOfX(13), 11, DELTA);
        assertEquals(getDefinedThroughArrays().floorIndexOfX(-1), 2, DELTA);
        assertEquals(getDefinedThroughMathFunction().floorIndexOfX(666), 109, DELTA);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(getDefinedThroughArrays().extrapolateLeft(-13), -103, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().extrapolateLeft(-1), -2, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(getDefinedThroughArrays().extrapolateRight(99), 0, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().extrapolateRight(-66), 3, DELTA);
    }

    @Test
    public void testInterpolate() {
        assertEquals(getDefinedThroughArrays().interpolate(0.666, getDefinedThroughArrays().floorIndexOfX(0.666)), 0.666, DELTA);
        assertEquals(getDefinedThroughMathFunction().interpolate(-0.13, getDefinedThroughMathFunction().floorIndexOfX(-0.13)), -0.010834, DELTA);
    }

    @Test
    public void testGetCount() {
        assertEquals(getDefinedThroughArrays().getCount(), 11);
        assertEquals(getDefinedThroughMathFunction().getCount(), 109);
    }

    @Test
    public void testGetX() {
        assertEquals(getDefinedThroughArrays().getX(0), -3, DELTA);
        assertEquals(getDefinedThroughArrays().getX(6), 3, DELTA);
        assertEquals(getDefinedThroughArrays().getX(9), 7, DELTA);
        assertEquals(getDefinedThroughMathFunction().getX(0), 0, DELTA);
        assertEquals(getDefinedThroughMathFunction().getX(35), 2.916667, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().getX(81), 27, DELTA);
    }

    @Test
    public void testGetY() {
        assertEquals(getDefinedThroughArrays().getY(0), -13, DELTA);
        assertEquals(getDefinedThroughArrays().getY(9), 0, DELTA);
        assertNotEquals(getDefinedThroughArrays().getY(10), 2, DELTA);
        assertEquals(getDefinedThroughMathFunction().getY(0), 0, DELTA);
        assertEquals(getDefinedThroughMathFunction().getY(20), 2.7777776, DELTA);
        assertEquals(getDefinedThroughMathFunction().getY(108), 81, DELTA);
    }

    @Test
    public void testSetY() {
        ArrayTabulatedFunction testDefinedThroughArrays = getDefinedThroughArrays();
        ArrayTabulatedFunction testDefinedThroughMathFunction = getDefinedThroughMathFunction();

        testDefinedThroughArrays.setY(1, 4);
        testDefinedThroughMathFunction.setY(0, 1009);
        assertEquals(testDefinedThroughArrays.getY(1), 4, DELTA);
        assertEquals(testDefinedThroughMathFunction.getY(0), 1009, DELTA);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(getDefinedThroughArrays().indexOfX(3), 6, DELTA);
        assertEquals(getDefinedThroughArrays().indexOfX(0.65), -1, DELTA);
        assertEquals(getDefinedThroughMathFunction().indexOfX(77), -1, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().indexOfX(0.1), 0, DELTA);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(getDefinedThroughArrays().indexOfY(8), -1, DELTA);
        assertEquals(getDefinedThroughArrays().indexOfY(0.65), -1, DELTA);
        assertEquals(getDefinedThroughMathFunction().indexOfY(18), -1, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().indexOfY(0.1), 100, DELTA);
    }

    @Test
    public void testLeftBound() {
        assertEquals(getDefinedThroughArrays().leftBound(), -3, DELTA);
        assertEquals(getDefinedThroughMathFunction().leftBound(), 0, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(getDefinedThroughArrays().rightBound(), 9, DELTA);
        assertEquals(getDefinedThroughMathFunction().rightBound(), 9, DELTA);
    }

    @Test
    public void testApply() {
        assertEquals(getDefinedThroughArrays().apply(-3), -13, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(-1), -0.08333, DELTA);
        assertEquals(getDefinedThroughArrays().apply(3), 9, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().apply(100), 10, DELTA);
        assertEquals(getDefinedThroughArrays().apply(0.65), 0.65, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(0.999), 0.99808, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(0), 0, DELTA);
    }

    @Test
    public void testInsert() {
        double[] x = new double[]{1, 2, 3};
        double[] y = new double[]{11, 22, 33};
        ArrayTabulatedFunction array = new ArrayTabulatedFunction(x, y);
        array.insert(0, 0);
        assertEquals(array.getY(0), 11);
        array.insert(6, 55);
        assertEquals(array.getY(4), 55);
        array.insert(4, 40);
        assertEquals(array.getY(1), 22);
        array.insert(2, 50);
        assertEquals(array.getY(1), 50);
    }

}