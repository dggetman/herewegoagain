package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ZeroFunctionTest {
    ZeroFunction testFunction = new ZeroFunction();

    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        assertEquals(testFunction.apply(666.), 0, DELTA);
        assertEquals(testFunction.apply(0), 0, DELTA);
        assertEquals(testFunction.apply(Double.NEGATIVE_INFINITY), 0, DELTA);
    }

    @Test
    public void testGetNumber() {
        assertEquals(testFunction.getConstant(), 0, DELTA);
    }
}