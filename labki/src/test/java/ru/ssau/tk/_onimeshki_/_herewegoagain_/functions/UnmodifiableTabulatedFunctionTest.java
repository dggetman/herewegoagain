package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.Constants;

import static org.testng.Assert.*;

public class UnmodifiableTabulatedFunctionTest {

    TabulatedFunction array = new UnmodifiableTabulatedFunction(
            new ArrayTabulatedFunction(new double[]{-1., 6., 9.}, new double[]{-1., 3., 10.}));
    TabulatedFunction list = new UnmodifiableTabulatedFunction(
            new LinkedListTabulatedFunction(new double[]{-1., 6., 9., 13.}, new double[]{-1., 3., 10., 11}));

    @Test
    public void testGetCount() {
        assertEquals(array.getCount(), 3);
        assertEquals(list.getCount(), 4);
    }

    @Test
    public void testGetX() {
        assertEquals(array.getX(1), 6., Constants.DELTA);
        assertEquals(list.getX(3), 13., Constants.DELTA);
    }

    @Test
    public void testGetY() {
        assertEquals(array.getY(1), 3., Constants.DELTA);
        assertEquals(list.getY(3), 11., Constants.DELTA);
    }

    @Test
    public void testSetY() {
        assertThrows(UnsupportedOperationException.class, () -> array.setY(0, 2.));
        assertThrows(UnsupportedOperationException.class, () -> list.setY(3, 1.));
    }

    @Test
    public void testIndexOfX() {
        assertEquals(array.indexOfX(6.), 1);
        assertEquals(list.indexOfX(-1.), 0);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(array.indexOfY(10.), 2);
        assertEquals(list.indexOfY(11.), 3);
    }

    @Test
    public void testLeftBound() {
        assertEquals(array.leftBound(), -1., Constants.DELTA);
        assertEquals(list.leftBound(), -1., Constants.DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(array.rightBound(), 9., Constants.DELTA);
        assertEquals(list.rightBound(), 13., Constants.DELTA);
    }

    @Test
    public void testApply() {
        assertEquals(array.apply(9.5), 11.16666, Constants.DELTA);
        assertEquals(list.apply(13.1), 11.025, Constants.DELTA);
    }

    @Test
    public void testIterator() {
        assertEquals(array.iterator().next().x, -1., Constants.DELTA);
        assertEquals(list.iterator().next().x, -1., Constants.DELTA);
    }
}