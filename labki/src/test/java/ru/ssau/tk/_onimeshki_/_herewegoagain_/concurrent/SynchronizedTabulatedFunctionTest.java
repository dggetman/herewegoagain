package ru.ssau.tk._onimeshki_._herewegoagain_.concurrent;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;

import java.util.Optional;

import static org.testng.Assert.assertEquals;


public class SynchronizedTabulatedFunctionTest {

    private final double[] xValues = new double[]{6, 9, 13, 77, 99};
    private final double[] yValues = new double[]{66, 99, 101, 188, 1000};
    private final Object mutex = new Object();

    private SynchronizedTabulatedFunction getSynchronizedList() {
        return new SynchronizedTabulatedFunction(new LinkedListTabulatedFunction(xValues, yValues), mutex);
    }

    private SynchronizedTabulatedFunction getSynchronizedArray() {
        return new SynchronizedTabulatedFunction(new ArrayTabulatedFunction(xValues, yValues), mutex);
    }

    @Test
    public void testGetCount() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();

        assertEquals(synchronizedTabulatedFunction.getCount(), 5);
        assertEquals(synchronizedArr.getCount(), 5);
    }

    @Test
    public void testGetX() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();

        assertEquals(synchronizedTabulatedFunction.getX(0), 6.0);
        assertEquals(synchronizedArr.getX(2), 13.0);
    }

    @Test
    public void testGetY() {
        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        assertEquals(synchronizedArr.getY(0), 66.0);

        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        assertEquals(synchronizedTabulatedFunction.getY(2), 101.0);
    }

    @Test
    public void testSetY() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        synchronizedTabulatedFunction.setY(2, 39);
        assertEquals(synchronizedTabulatedFunction.getY(2), 39, Constants.DELTA);

        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        synchronizedArr.setY(3, 12);
        assertEquals(synchronizedArr.getY(3), 12.0);
    }

    @Test
    public void testIndexOfX() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        assertEquals(synchronizedTabulatedFunction.indexOfX(6), 0);

        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        assertEquals(synchronizedArr.indexOfX(6), 0);
    }

    @Test
    public void testIndexOfY() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        assertEquals(synchronizedTabulatedFunction.indexOfY(66), 0);
        assertEquals(synchronizedTabulatedFunction.indexOfY(99), 1);
        assertEquals(synchronizedTabulatedFunction.indexOfY(101), 2);

        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        assertEquals(synchronizedArr.indexOfY(66), 0);
        assertEquals(synchronizedArr.indexOfY(99), 1);
        assertEquals(synchronizedArr.indexOfY(101), 2);
    }

    @Test
    public void testLeftBound() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        assertEquals(synchronizedTabulatedFunction.leftBound(), 6.0);

        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        assertEquals(synchronizedArr.leftBound(), 6.0);
    }

    @Test
    public void testRightBound() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        assertEquals(synchronizedTabulatedFunction.rightBound(), 99.0);

        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        assertEquals(synchronizedArr.rightBound(), 99.0);
    }

    /*@Test
    public void testIteratorWhile() {
        //И тут тебе тоже надо написать
    }

    @Test
    public void testIteratorForEach() {
        //и тут тоже
    }*/


    @Test
    public void testApply() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        assertEquals(synchronizedTabulatedFunction.apply(5), 55.0, Constants.DELTA);

        SynchronizedTabulatedFunction synchronizedArr = getSynchronizedArray();
        assertEquals(synchronizedArr.apply(6), 66.0);
    }

    @Test
    public void testDoSynchronously() {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction = getSynchronizedList();
        assertEquals((int) synchronizedTabulatedFunction.doSynchronously(SynchronizedTabulatedFunction::getCount), 5);
        assertEquals(java.util.Optional.ofNullable(synchronizedTabulatedFunction.doSynchronously(SynchronizedTabulatedFunction::leftBound)), Optional.of(6.0));

    }

}