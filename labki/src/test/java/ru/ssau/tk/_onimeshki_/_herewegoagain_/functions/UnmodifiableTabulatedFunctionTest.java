package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnmodifiableTabulatedFunctionTest {

    TabulatedFunction array = new UnmodifiableTabulatedFunction(
            new ArrayTabulatedFunction(new double[]{1., 2., 3.}, new double[]{1., 4., 9.}));
    TabulatedFunction list = new UnmodifiableTabulatedFunction(
            new LinkedListTabulatedFunction(new double[]{1., 2., 3., 4.}, new double[]{1., 4., 9., 16}));

    @Test
    public void testGetCount() {
    }

    @Test
    public void testGetX() {
    }

    @Test
    public void testGetY() {
    }

    @Test
    public void testSetY() {
    }

    @Test
    public void testIndexOfX() {
    }

    @Test
    public void testIndexOfY() {
    }

    @Test
    public void testLeftBound() {
    }

    @Test
    public void testRightBound() {
    }

    @Test
    public void testApply() {
    }

    @Test
    public void testIterator() {
    }
}