package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AbsFunctionTest {

    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        AbsFunction testFunction = new AbsFunction();
        assertEquals(testFunction.apply(-6), 6, DELTA);
        assertEquals(testFunction.apply(Integer.MIN_VALUE + 1), Integer.MAX_VALUE, DELTA);
        assertEquals(testFunction.apply(6), 6, DELTA);
        assertEquals(testFunction.apply(0), 0, DELTA);
        assertEquals(testFunction.apply(Double.NEGATIVE_INFINITY), Double.POSITIVE_INFINITY, DELTA);
    }
}