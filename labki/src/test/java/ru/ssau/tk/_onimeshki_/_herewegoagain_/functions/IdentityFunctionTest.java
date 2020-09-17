package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunctionTest {

    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        IdentityFunction testFunction = new IdentityFunction();
        assertEquals(testFunction.apply(6), 6, DELTA);
        assertEquals(testFunction.apply(-6), -6, DELTA);
        assertEquals(testFunction.apply(0), 0, DELTA);
        assertEquals(testFunction.apply(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY, DELTA);
    }
}