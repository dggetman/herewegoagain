package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {
    ConstantFunction testFunction = new ConstantFunction(666.1313);
    ConstantFunction testFunction1 = new ConstantFunction(13.1313);
    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        assertEquals(testFunction1.apply(13.1313), 13.1313, DELTA);
        assertEquals(testFunction.apply(666.), 666.1313, DELTA);
    }

    @Test
    public void testGetConstanta() {
        assertEquals(testFunction.getConstant(), 666.1313, DELTA);
        assertEquals(testFunction1.getConstant(), 13.1313, DELTA);
    }
}