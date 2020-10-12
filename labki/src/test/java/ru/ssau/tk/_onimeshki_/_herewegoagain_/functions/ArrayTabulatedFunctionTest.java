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
  /*  @Test
    public void testInsert() {
        double[] x = new double[]{1, 2, 3};
        double[] y = new double[]{10, 20, 30};
        ArrayTabulatedFunction array = new ArrayTabulatedFunction(x, y);
        array.insert(0, 0);
        assertEquals(array.getY(0), 0);
       // array.insert(6, 5);
       //assertEquals(array.getY(4), 5);
       // array.insert(6, 60);
       // assertEquals(array.getY(4), 60);
       // array.insert(4, 40);
       //  assertEquals(array.getY(4), 40);
       //  array.insert(5, 50);
       //  assertEquals(array.getY(5), 50);
    }
*/
}