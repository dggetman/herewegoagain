package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.exceptions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;
import static ru.ssau.tk._onimeshki_._herewegoagain_.functions.Constants.DELTA;

public class    LinkedListTabulatedFunctionTest {
    private final double[] xValues = new double[]{1, 2, 3, 4, 5};
    private final double[] yValues = new double[]{11, 22, 33, 44, 55};
    private final MathFunction testFunction = new SqrtFunction();

    private LinkedListTabulatedFunction getListOfArray() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    private LinkedListTabulatedFunction getListOfMathFunction() {
        return new LinkedListTabulatedFunction(testFunction, 1., 5, 10);
    }

    @Test
    public void testLinkedListTabulatedFunction() {
        double[] xValues = {1.3};
        double[] yValues = {6.9};
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(xValues, yValues));
        double[] xValues1 = new double[]{};
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(xValues1, yValues));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(testFunction, 10, 2, 10));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(testFunction, 6, 2, 20));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(testFunction, 11, 1, 21));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(testFunction, 999, 666, 6969690));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(testFunction, 999, 666, 1));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(testFunction, 999, 666, 2));
    }

    @Test
    public void testApply() {
        assertEquals(getListOfArray().apply(1), 11, DELTA);
        assertEquals(getListOfArray().apply(5), 55, DELTA);
        assertEquals(getListOfArray().apply(3), 33, DELTA);
        assertEquals(getListOfArray().apply(0), 0, DELTA);
        assertEquals(getListOfArray().apply(6), 66, DELTA);

        assertEquals(getListOfMathFunction().apply(6), 2.46488, DELTA);
        assertEquals(getListOfMathFunction().apply(9), 3.151307, DELTA);
        assertEquals(getListOfMathFunction().apply(11), 3.608926, DELTA);
        assertEquals(getListOfMathFunction().apply(13), 4.066546, DELTA);
    }

    @Test
    public void testGetNode() {
        assertEquals(getListOfArray().getX(0), 1, DELTA);
        assertEquals(getListOfArray().getX(1), 2, DELTA);
        assertEquals(getListOfArray().getX(2), 3, DELTA);
        assertEquals(getListOfArray().getX(3), 4, DELTA);
        assertEquals(getListOfArray().getX(4), 5, DELTA);
        assertThrows(IllegalArgumentException.class, () -> getListOfArray().getX(-123));

        assertThrows(IllegalArgumentException.class, () -> getListOfArray().getX(-5));
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
        assertEquals(getListOfMathFunction().getCount(), 10, DELTA);
    }

    @Test
    public void testLeftBound() {
        assertEquals(getListOfArray().leftBound(), 1, DELTA);
        assertEquals(getListOfMathFunction().leftBound(), 1, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(getListOfArray().rightBound(), 5, DELTA);
        assertEquals(getListOfMathFunction().rightBound(), 5, DELTA);
    }

    @Test
    public void testGetX() {
        assertEquals(getListOfArray().getX(1), 2, DELTA);
        assertEquals(getListOfMathFunction().getX(1), 1.4444444, DELTA);
        assertEquals(getListOfMathFunction().getX(5), 3.2222222, DELTA);
    }

    @Test
    public void testGetY() {
        assertEquals(getListOfArray().getY(0), 11, DELTA);
        assertEquals(getListOfMathFunction().getY(0), 1, DELTA);
        assertThrows(IllegalArgumentException.class, () -> getListOfArray().floorIndexOfX(-13));
        assertThrows(IllegalArgumentException.class, () -> getListOfArray().floorIndexOfX(-66));
        assertThrows(IllegalArgumentException.class, () -> getListOfArray().floorIndexOfX(-131313));
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
        assertEquals(getListOfMathFunction().indexOfX(5), -1);
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
        assertEquals(getListOfMathFunction().floorIndexOfX(66), 10);
        assertThrows(IllegalArgumentException.class, () -> getListOfArray().floorIndexOfX(-13));
        assertThrows(IllegalArgumentException.class, () -> getListOfArray().floorIndexOfX(-66));
        assertThrows(IllegalArgumentException.class, () -> getListOfArray().floorIndexOfX(-666));
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(getListOfArray().extrapolateLeft(11), 121, DELTA);
        assertEquals(getListOfMathFunction().extrapolateLeft(4), 2.3624904, DELTA);
    }

    @Test
    public void testExtrapolateRight() {
        LinkedListTabulatedFunction testListArray = getListOfArray();
        LinkedListTabulatedFunction testListMath = getListOfMathFunction();
        assertEquals(testListArray.extrapolateRight(6), 66, DELTA);
        assertEquals(testListMath.extrapolateRight(11), 3.6089266, DELTA);
    }

    @Test
    public void testInterpolate() {
        LinkedListTabulatedFunction testListArray = getListOfArray();
        LinkedListTabulatedFunction testListMath = getListOfMathFunction();
        assertEquals(testListArray.interpolate(6, 2), 66, DELTA);
        assertEquals(testListMath.interpolate(11, 3), 4.240783214, DELTA);
        assertThrows(InterpolationException.class, () -> testListArray.interpolate(1.5, 2));
        assertThrows(InterpolationException.class, () -> testListMath.interpolate(1.5, 1));
    }

    @Test
    public void testInsert() {
        double[] valuesXFirst = new double[]{-1., 79.};
        double[] valuesYFirst = new double[]{-1., 46.};
        LinkedListTabulatedFunction testInsertLinkedListFirst = new LinkedListTabulatedFunction(valuesXFirst, valuesYFirst);

        testInsertLinkedListFirst.insert(0, 0);
        testInsertLinkedListFirst.insert(1., 1.);
        testInsertLinkedListFirst.insert(6., 9.);
        testInsertLinkedListFirst.insert(13., 26.);
        testInsertLinkedListFirst.insert(77., 33.);

        assertEquals(testInsertLinkedListFirst.getX(0), 0, DELTA);
        assertEquals(testInsertLinkedListFirst.getY(0), 0, DELTA);

        assertEquals(testInsertLinkedListFirst.getX(1), -1, DELTA);
        assertEquals(testInsertLinkedListFirst.getY(1), -1, DELTA);

        assertEquals(testInsertLinkedListFirst.getX(2), 79, DELTA);
        assertEquals(testInsertLinkedListFirst.getY(2), 46, DELTA);

        assertEquals(testInsertLinkedListFirst.getX(3), 1, DELTA);
        assertEquals(testInsertLinkedListFirst.getY(3), 1, DELTA);

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

        assertThrows(IllegalArgumentException.class, () -> {
            double[] valuesXTest = new double[]{-13., 21.};
            double[] valuesYTest = new double[]{19., 44.};
            ArrayTabulatedFunction testRemove = new ArrayTabulatedFunction(valuesXTest, valuesYTest);
            testRemove.remove(0);
        });

    }    /*old array: [(1, 11) (2, 22) (3, 33) (4, 44) (5, 55)]
          array: [(1, 11) (3, 33) (4, 44)]         */

    @Test
    public void testFloorNodeOfX() {
        assertEquals(getListOfArray().floorIndexOfX(5), 3);
        assertEquals(getListOfMathFunction().floorIndexOfX(66), 10);
    }

    @Test
    public void testIteratorCycleWhile() {
        Iterator<Point> iterator = getListOfArray().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(getListOfArray().getX(i), point.x, DELTA);
            assertEquals(getListOfArray().getY(i++), point.y, DELTA);
        }
        assertEquals(getListOfArray().getCount(), i);

        Iterator<Point> iteratorM = getListOfMathFunction().iterator();
        int j = 0;
        while (iteratorM.hasNext()) {
            Point point = iteratorM.next();
            assertEquals(getListOfMathFunction().getX(j), point.x, DELTA);
            assertEquals(getListOfMathFunction().getY(j++), point.y, DELTA);
        }
        assertEquals(getListOfMathFunction().getCount(), j);
        assertThrows(NoSuchElementException.class, iterator::next);
    }


    @Test
    public void testIteratorCycleForEach() {
        int i = 0;
        for (Point point : getListOfArray()) {
            assertEquals(getListOfArray().getX(i), point.x, DELTA);
            assertEquals(getListOfArray().getY(i++), point.y, DELTA);
        }
        assertEquals(getListOfArray().getCount(), i);
        int j = 0;
        for (Point point : getListOfMathFunction()) {
            assertEquals(getListOfMathFunction().getX(j), point.x, DELTA);
            assertEquals(getListOfMathFunction().getY(j++), point.y, DELTA);
        }
        assertEquals(getListOfMathFunction().getCount(), j);
    }

}