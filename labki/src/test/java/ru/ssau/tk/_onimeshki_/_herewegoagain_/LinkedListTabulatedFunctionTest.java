package ru.ssau.tk._onimeshki_._herewegoagain_;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.MathFunction;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.SqrtFunction;

import static org.testng.Assert.*;
import static ru.ssau.tk._onimeshki_._herewegoagain_.Constants.DELTA;

public class LinkedListTabulatedFunctionTest {
    private final double[] xValues = new double[]{1, 2, 3, 4, 5};
    private final double[] yValues = new double[]{11, 22, 33, 44, 55};
    private final MathFunction testFunction = new SqrtFunction();

    private LinkedListTabulatedFunction getListOfArray() {

        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    private LinkedListTabulatedFunction getListOfMathFunction() {
        return new LinkedListTabulatedFunction(testFunction, 5, 15, 30);
    }

    @Test
    public void testApply() {
        assertEquals(getListOfArray().apply(1), 11, DELTA);
        assertEquals(getListOfArray().apply(5), 55, DELTA);
        assertEquals(getListOfArray().apply(1.4), 15.4, DELTA);
        assertEquals(getListOfArray().apply(0), 0, DELTA);
        assertEquals(getListOfArray().apply(6), 66, DELTA);

        assertEquals(getListOfMathFunction().apply(1), 1.35655, DELTA);
        assertEquals(getListOfMathFunction().apply(5), 2.23607, DELTA);
        assertEquals(getListOfMathFunction().apply(1.4), 1.444504, DELTA);
        assertEquals(getListOfMathFunction().apply(0), 1.13667, DELTA);
        assertEquals(getListOfMathFunction().apply(6), 2.4494, DELTA);
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
        assertEquals(getListOfMathFunction().getCount(), 60, DELTA);
    }

    @Test
    public void testLeftBound() {
        assertEquals(getListOfArray().leftBound(), 1, DELTA);
        assertEquals(getListOfMathFunction().leftBound(), 5, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(getListOfArray().rightBound(), 5, DELTA);
        assertEquals(getListOfMathFunction().rightBound(), 15, DELTA);
    }

    @Test
    public void testGetX() {
        assertEquals(getListOfArray().getX(1), 2, DELTA);
        assertEquals(getListOfMathFunction().getX(1), 5.3448275, DELTA);
        assertEquals(getListOfMathFunction().getX(5), 6.7241379, DELTA);
    }

    @Test
    public void testGetY() {
        assertEquals(getListOfArray().getY(0), 11, DELTA);
        assertEquals(getListOfMathFunction().getY(0), 2.2360679, DELTA);
        assertThrows(IllegalArgumentException.class, () -> {
            getListOfArray().floorIndexOfX(-13);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            getListOfArray().floorIndexOfX(-66);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            getListOfArray().floorIndexOfX(-131313);
        });
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
        assertEquals(getListOfMathFunction().floorIndexOfX(66), 60);
        assertThrows(IllegalArgumentException.class, () -> {
            getListOfArray().floorIndexOfX(-13);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            getListOfArray().floorIndexOfX(-66);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            getListOfArray().floorIndexOfX(-666);
        });
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(getListOfArray().extrapolateLeft(11), 121, DELTA);
        assertEquals(getListOfMathFunction().extrapolateLeft(4), 2.0161889, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        LinkedListTabulatedFunction testListArray = getListOfArray();
        LinkedListTabulatedFunction testListMath = getListOfMathFunction();
        assertEquals(testListArray.extrapolateRight(6), 66, DELTA);
        assertEquals(testListMath.extrapolateRight(11), 3.3535831, DELTA);
    }

    @Test
    public void testInterpolate() {
        LinkedListTabulatedFunction testListArray = getListOfArray();
        LinkedListTabulatedFunction testListMath = getListOfMathFunction();
        assertEquals(testListArray.interpolate(6, 2), 66);
        assertEquals(testListMath.interpolate(11, 3), 3.4531603, DELTA);
    }

    @Test
    public void testInsert() {
        double[] valuesXFirst = new double[]{};
        double[] valuesYFirst = new double[]{};
        LinkedListTabulatedFunction testInsertLinkedListFirst = new LinkedListTabulatedFunction(valuesXFirst, valuesYFirst);

        testInsertLinkedListFirst.insert(0, 0);
        testInsertLinkedListFirst.insert(1., 1.);
        testInsertLinkedListFirst.insert(6., 9.);
        testInsertLinkedListFirst.insert(13., 26.);
        testInsertLinkedListFirst.insert(77., 33.);

        //list: [(0, 0) (1, 1) (6, 9) (13, 26) (77, 33)]
        assertEquals(testInsertLinkedListFirst.getX(0), 0, DELTA);
        assertEquals(testInsertLinkedListFirst.getY(0), 0, DELTA);

        assertEquals(testInsertLinkedListFirst.getX(1), 1, DELTA);
        assertEquals(testInsertLinkedListFirst.getY(1), 1, DELTA);

        assertEquals(testInsertLinkedListFirst.getX(2), 6, DELTA);
        assertEquals(testInsertLinkedListFirst.getY(2), 9, DELTA);

        assertEquals(testInsertLinkedListFirst.getX(3), 13, DELTA);
        assertEquals(testInsertLinkedListFirst.getY(3), 26, DELTA);

        assertNotEquals(testInsertLinkedListFirst.getX(4), 33, DELTA);
        assertNotEquals(testInsertLinkedListFirst.getY(4), 77, DELTA);
    }

    @Test
    public void testRemove() {
        LinkedListTabulatedFunction testList = getListOfArray();
        testList.remove(1);
        testList.remove(3);

        assertEquals(testList.getX(2), 4);
        assertEquals(testList.getX(1), 3);
        assertEquals(testList.getX(0), 1);

    }    /*old array: [(1, 11) (2, 22) (3, 33) (4, 44) (5, 55)]
          array: [(1, 11) (3, 33) (4, 44)]         */

    @Test
    public void testFloorNodeOfX() {
        assertEquals(getListOfArray().floorIndexOfX(5), 3);
        assertEquals(getListOfMathFunction().floorIndexOfX(66), 60);
    }

}