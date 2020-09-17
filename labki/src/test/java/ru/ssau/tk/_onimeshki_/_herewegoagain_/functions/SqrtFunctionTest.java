package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqrtFunctionTest {

    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        SqrtFunction testFunction = new SqrtFunction();
        assertEquals(testFunction.apply(36), 6, DELTA);
        assertNotEquals(testFunction.apply(Double.POSITIVE_INFINITY), DELTA);
        assertEquals(testFunction.apply(0), 0, DELTA);
        assertEquals(testFunction.apply(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY, DELTA);
    }
}