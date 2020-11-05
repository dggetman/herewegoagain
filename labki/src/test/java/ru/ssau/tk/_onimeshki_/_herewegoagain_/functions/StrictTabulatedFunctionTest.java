package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import org.testng.annotations.Test;

import java.util.Iterator;

import static org.testng.Assert.*;

public class StrictTabulatedFunctionTest {

    private final double[] xValues = new double[]{1, 2, 3, 4, 5, 6, 7};
    private final double[] yValues = new double[]{11, 22, 33, 44, 55, 66, 77};

    private ArrayTabulatedFunction getDefinedThroughArrays() {
        return new ArrayTabulatedFunction(xValues, yValues);
    }

    private LinkedListTabulatedFunction getListOfArray() {
        return new LinkedListTabulatedFunction(xValues,yValues);
    }

    @Test
    public void testGetCount() {
        StrictTabulatedFunction testArrayFunction = new StrictTabulatedFunction(getDefinedThroughArrays());
        assertEquals(testArrayFunction.getCount(), 7);

        StrictTabulatedFunction testListFunction = new StrictTabulatedFunction(getListOfArray());
        assertEquals(testListFunction.getCount(), 7);
    }

    @Test
    public void testGetX() {
        StrictTabulatedFunction testArrayFunction = new StrictTabulatedFunction(getDefinedThroughArrays());
        assertEquals(testArrayFunction.getX(0), 1);
        assertEquals(testArrayFunction.getX(2), 3);
        assertEquals(testArrayFunction.getX(4), 5);

        StrictTabulatedFunction testListFunction = new StrictTabulatedFunction(getListOfArray());
        assertEquals(testListFunction.getX(0), 1);
        assertEquals(testListFunction.getX(2), 3);
        assertEquals(testListFunction.getX(4), 5);
    }

    @Test
    public void testGetY() {
        StrictTabulatedFunction testArrayFunction = new StrictTabulatedFunction(getDefinedThroughArrays());
        assertEquals(testArrayFunction.getY(0), 11);
        assertEquals(testArrayFunction.getY(2), 33);
        assertEquals(testArrayFunction.getY(4), 55);

        StrictTabulatedFunction testListFunction = new StrictTabulatedFunction(getListOfArray());
        assertEquals(testListFunction.getY(0), 11);
        assertEquals(testListFunction.getY(2), 33);
        assertEquals(testListFunction.getY(4), 55);
    }

    @Test
    public void testSetY() {
        StrictTabulatedFunction testArrayFunction = new StrictTabulatedFunction(getDefinedThroughArrays());
        testArrayFunction.setY(0, 0);
        testArrayFunction.setY(2, 0);
        testArrayFunction.setY(4, 0);
        assertEquals(testArrayFunction.getY(0), 0);
        assertEquals(testArrayFunction.getY(2), 0);
        assertEquals(testArrayFunction.getY(4), 0);
        StrictTabulatedFunction testListFunction = new StrictTabulatedFunction(getListOfArray());
        testListFunction.setY(0, 0);
        testListFunction.setY(2, 0);
        testListFunction.setY(4, 0);
        assertEquals(testListFunction.getY(0), 0);
        assertEquals(testListFunction.getY(2), 0);
        assertEquals(testListFunction.getY(4), 0);
    }

    @Test
    public void testIndexOfX() {
        StrictTabulatedFunction testArrayFunction = new StrictTabulatedFunction(getDefinedThroughArrays());
        assertEquals(testArrayFunction.indexOfX(1), 0);
        assertEquals(testArrayFunction.indexOfX(3), 2);
        assertEquals(testArrayFunction.indexOfX(5), 4);
        StrictTabulatedFunction testListFunction = new StrictTabulatedFunction(getListOfArray());
        assertEquals(testListFunction.indexOfX(11), 0);
        assertEquals(testListFunction.indexOfX(33), 2);
        assertEquals(testListFunction.indexOfX(55), 4);
    }

    @Test
    public void testIndexOfY() {
        StrictTabulatedFunction testArrayFunction = new StrictTabulatedFunction(getDefinedThroughArrays());
        assertEquals(testArrayFunction.indexOfY(11), 0);
        assertEquals(testArrayFunction.indexOfY(33), 2);
        assertEquals(testArrayFunction.indexOfY(55), 4);
        StrictTabulatedFunction testListFunction = new StrictTabulatedFunction(getListOfArray());
        assertEquals(testListFunction.indexOfY(1), 0);
        assertEquals(testListFunction.indexOfY(3), 2);
        assertEquals(testListFunction.indexOfY(5), 4);
    }

    @Test
    public void testLeftBound() {
        StrictTabulatedFunction testArrayFunction = new StrictTabulatedFunction(getDefinedThroughArrays());
        assertEquals(testArrayFunction.leftBound(), 1);
        StrictTabulatedFunction testListFunction = new StrictTabulatedFunction(getListOfArray());
        assertEquals(testListFunction.leftBound(), 11);
    }

    @Test
    public void testRightBound() {
        StrictTabulatedFunction testArrayFunction = new StrictTabulatedFunction(getDefinedThroughArrays());
        assertEquals(testArrayFunction.rightBound(), 7);
        StrictTabulatedFunction testListFunction = new StrictTabulatedFunction(getListOfArray());
        assertEquals(testListFunction.rightBound(), 77);
    }

    @Test
    public void testIterator() {
        StrictTabulatedFunction testListFunction = new StrictTabulatedFunction(getListOfArray());
        Iterator<Point> myIterator = testListFunction.iterator();
        int i = 0;
        for (Point myPoint : testListFunction) {
            myPoint = myIterator.next();
            assertEquals(testListFunction.getX(i), myPoint.x);
            assertEquals(testListFunction.getY(i++), myPoint.y);
        }

    }

    @Test
    public void testApply() {
        StrictTabulatedFunction testArrayFunction = new StrictTabulatedFunction(getDefinedThroughArrays());
        assertEquals(testArrayFunction.apply(1), 11);
        assertEquals(testArrayFunction.apply(3), 33);
        assertEquals(testArrayFunction.apply(5), 55);
        StrictTabulatedFunction testListFunction = new StrictTabulatedFunction(getListOfArray());
        assertEquals(testListFunction.apply(11), 1);
        assertEquals(testListFunction.apply(33), 3);
        assertEquals(testListFunction.apply(55), 5);

        assertThrows(UnsupportedOperationException.class, () -> testArrayFunction.apply(-2));
        assertThrows(UnsupportedOperationException.class, () -> testListFunction.apply(-2));
    }
}