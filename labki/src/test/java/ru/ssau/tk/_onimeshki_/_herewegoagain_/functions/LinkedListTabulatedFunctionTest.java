package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static ru.ssau.tk._onimeshki_._herewegoagain_.functions.Constants.DELTA;

public class LinkedListTabulatedFunctionTest {
    private final double[] xValues = new double[]{1, 2, 3, 4, 5};
    private final double[] yValues = new double[]{11, 22, 33, 44, 55};
    private final MathFunction testFunction = new SqrtFunction();

    private LinkedListTabulatedFunction getListOfArray() {

        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    private LinkedListTabulatedFunction getListOfMathFunction() {
        return new LinkedListTabulatedFunction(testFunction, 5, 10, 20);
    }

    @Test
    public void testApply() {
        assertEquals(getListOfArray().apply(3), 33, DELTA);
        assertEquals(getListOfMathFunction().apply(25), 5, DELTA);

        assertEquals(getListOfArray().apply(7), 77, DELTA);
        assertEquals(getListOfMathFunction().apply(11.4), 3.3762700, DELTA);
    }

    @Test
    public void testGetNode() {
        assertEquals(getListOfArray().getX(0), 1, DELTA);
        assertEquals(getListOfArray().getX(1), 2, DELTA);
        assertEquals(getListOfArray().getX(2), 3, DELTA);
        assertEquals(getListOfArray().getX(3), 4, DELTA);
        assertEquals(getListOfArray().getX(4), 5, DELTA);

    }

    @Test
    public void testAddNode() {
        LinkedListTabulatedFunction testList = getListOfArray();
        testList.addNode(3, 33);
        assertEquals(testList.rightBound(), 3, DELTA);
    }

    @Test
    public void testGetCount() {
        assertEquals(getListOfArray().getCount(), 5, DELTA);
        assertEquals(getListOfMathFunction().getCount(), 100, DELTA);
    }

    @Test
    public void testLeftBound() {
        assertEquals(getListOfArray().leftBound(), 1, DELTA);
        assertEquals(getListOfMathFunction().leftBound(), 5, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(getListOfArray().rightBound(), 5, DELTA);
        assertEquals(getListOfMathFunction().rightBound(), 25, DELTA);
    }

    @Test
    public void testGetX() {
        assertEquals(getListOfArray().getX(1), 2, DELTA);
        assertEquals(getListOfMathFunction().getX(1), 5.2631578, DELTA);
        assertEquals(getListOfMathFunction().getX(5), 6.3157894, DELTA);
    }

    @Test
    public void testGetY() {
        assertEquals(getListOfArray().getY(0), 11, DELTA);
        assertEquals(getListOfMathFunction().getY(0), 2.2360679, DELTA);
    }

    @Test
    public void testSetY() {
        LinkedListTabulatedFunction testListArray = getListOfArray();
        testListArray.setY(2, 60);
        assertEquals(testListArray.getY(2), 60, DELTA);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(getListOfArray().indexOfX(3), 2);
        assertEquals(getListOfMathFunction().indexOfX(5), 0);
        assertEquals(getListOfArray().indexOfX(50), -1);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(getListOfArray().indexOfY(6), -1);
        assertEquals(getListOfMathFunction().indexOfY(3), -1);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(getListOfArray().floorIndexOfX(5), 3);
        assertEquals(getListOfMathFunction().floorIndexOfX(-6), 0);
        assertEquals(getListOfMathFunction().floorIndexOfX(66), 40);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(getListOfArray().extrapolateLeft(11), 121, DELTA);
        assertEquals(getListOfMathFunction().extrapolateLeft(4), 2.0153284, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        LinkedListTabulatedFunction testListArray = getListOfArray();
        LinkedListTabulatedFunction testListMath = getListOfMathFunction();
        assertEquals(testListArray.extrapolateRight(6), 66, DELTA);
        assertEquals(testListMath.extrapolateRight(11), 3.3214456, DELTA);
    }

    @Test
    public void testInterpolate() {
        LinkedListTabulatedFunction testListArray = getListOfArray();
        LinkedListTabulatedFunction testListMath = getListOfMathFunction();
        assertEquals(testListArray.interpolate(6, 2), 66);
        assertEquals(testListMath.interpolate(11, 3), 3.4768599, DELTA);
    }

    @Test
    public void testRemove() {
        LinkedListTabulatedFunction testList = getListOfArray();
        testList.remove(1);
        assertEquals(testList.getX(0), 1);
        testList.remove(3);
        assertEquals(testList.getX(2), 4);

    }
}