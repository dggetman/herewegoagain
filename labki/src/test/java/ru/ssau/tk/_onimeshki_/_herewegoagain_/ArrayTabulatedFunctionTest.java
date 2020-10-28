package ru.ssau.tk._onimeshki_._herewegoagain_;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.MathFunction;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.SqrFunction;
import ru.ssau.tk._onimeshki_._herewegoagain_.exceptions.InterpolationException;

import static org.testng.Assert.*;
import static ru.ssau.tk._onimeshki_._herewegoagain_.Constants.DELTA;

public class ArrayTabulatedFunctionTest {
    private final double[] valuesX = new double[]{-3., -2., -1, 0., 1., 2., 3., 4., 5.};
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
        assertEquals(getDefinedThroughArrays().floorIndexOfX(13), 9, DELTA);
        assertEquals(getDefinedThroughArrays().floorIndexOfX(-1), 2, DELTA);
        assertEquals(getDefinedThroughMathFunction().floorIndexOfX(666), 109, DELTA);
        assertThrows(IllegalArgumentException.class, () -> {
            getDefinedThroughArrays().floorIndexOfX(-13);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            getDefinedThroughArrays().floorIndexOfX(-66);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            getDefinedThroughArrays().floorIndexOfX(-666);
        });
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(getDefinedThroughArrays().extrapolateLeft(-13), -103, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().extrapolateLeft(-1), -2, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(getDefinedThroughArrays().extrapolateRight(99), 1153, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().extrapolateRight(-66), 3, DELTA);
    }

    @Test
    public void testInterpolate() {
        assertEquals(getDefinedThroughArrays().interpolate(0.666, getDefinedThroughArrays().floorIndexOfX(0.666)), 0.666, DELTA);
        assertEquals(getDefinedThroughMathFunction().interpolate(0.13, getDefinedThroughMathFunction().floorIndexOfX(0.13)), 0.018611, DELTA);
        assertThrows(InterpolationException.class, () -> getDefinedThroughArrays().interpolate(-1.5, 3));
        assertThrows(InterpolationException.class, () -> getDefinedThroughMathFunction().interpolate(4.5, 9));

    }

    @Test
    public void testGetCount() {
        assertEquals(getDefinedThroughArrays().getCount(), 9);
        assertEquals(getDefinedThroughMathFunction().getCount(), 109);
    }

    @Test
    public void testGetX() {
        assertEquals(getDefinedThroughArrays().getX(0), -3, DELTA);
        assertEquals(getDefinedThroughArrays().getX(6), 3, DELTA);
        assertEquals(getDefinedThroughArrays().getX(8), 5, DELTA);
        assertEquals(getDefinedThroughMathFunction().getX(0), 0, DELTA);
        assertEquals(getDefinedThroughMathFunction().getX(35), 2.916667, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().getX(81), 27, DELTA);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            getDefinedThroughMathFunction().getX(-111111);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            getDefinedThroughMathFunction().getX(131313);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            getDefinedThroughMathFunction().getX(666666);
        });
    }

    @Test
    public void testGetY() {
        assertEquals(getDefinedThroughArrays().getY(0), -13, DELTA);
        assertEquals(getDefinedThroughArrays().getY(8), 25, DELTA);
        assertNotEquals(getDefinedThroughArrays().getY(7), 2, DELTA);
        assertEquals(getDefinedThroughMathFunction().getY(0), 0, DELTA);
        assertEquals(getDefinedThroughMathFunction().getY(20), 2.7777776, DELTA);
        assertEquals(getDefinedThroughMathFunction().getY(108), 81, DELTA);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            getDefinedThroughMathFunction().getX(-111111);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            getDefinedThroughMathFunction().getX(131313);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            getDefinedThroughMathFunction().getX(666666);
        });
    }

    @Test
    public void testSetY() {
        ArrayTabulatedFunction testDefinedThroughArrays = getDefinedThroughArrays();
        ArrayTabulatedFunction testDefinedThroughMathFunction = getDefinedThroughMathFunction();

        testDefinedThroughArrays.setY(1, 4);
        testDefinedThroughMathFunction.setY(0, 1009);
        assertEquals(testDefinedThroughArrays.getY(1), 4, DELTA);
        assertEquals(testDefinedThroughMathFunction.getY(0), 1009, DELTA);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            getDefinedThroughMathFunction().getX(-111111);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            getDefinedThroughMathFunction().getX(131313);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            getDefinedThroughMathFunction().getX(666666);
        });
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
        assertEquals(getDefinedThroughArrays().rightBound(), 5, DELTA);
        assertEquals(getDefinedThroughMathFunction().rightBound(), 9, DELTA);
    }

    @Test
    public void testApply() {
        assertEquals(getDefinedThroughArrays().apply(-4), -22, DELTA);
        assertEquals(getDefinedThroughArrays().apply(13), 121, DELTA);
        assertEquals(getDefinedThroughArrays().apply(-3), -13, DELTA);
        assertEquals(getDefinedThroughArrays().apply(5), 25, DELTA);
        assertEquals(getDefinedThroughArrays().apply(1.3), 1.9, DELTA);

        assertEquals(getDefinedThroughMathFunction().apply(-4), -0.33333, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(-3), -0.25, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(5), 25, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(1.3), 1.691667, DELTA);
        assertEquals(getDefinedThroughMathFunction().apply(13), 152.66667, DELTA);
        assertNotEquals(getDefinedThroughMathFunction().apply(100), 10, DELTA);
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

    @Test
    public void testRemove() {
        ArrayTabulatedFunction testDefinedThroughArrays = getDefinedThroughArrays();
        testDefinedThroughArrays.remove(0);
        testDefinedThroughArrays.remove(6);
        testDefinedThroughArrays.remove(5);
       /* old array: [(-3, -13) (-2, -4) (-1, -1) (0, 0) (1, 1) (2, 4) (3, 9) (4, 13) (5, 25)]
        array: [(-2, -4) (-1, -1) (0, 0) (1, 1) (2, 4) (4, 13)] */
        assertEquals(testDefinedThroughArrays.getX(0), -2, DELTA);
        assertEquals(testDefinedThroughArrays.getX(1), -1, DELTA);
        assertEquals(testDefinedThroughArrays.getX(2), 0, DELTA);
        assertEquals(testDefinedThroughArrays.getX(3), 1, DELTA);
    }

}