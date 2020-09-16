package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {
    ConstantFunction testFunction = new ConstantFunction(666.1313);
    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        assertEquals(testFunction.apply(666.), 666.1313, DELTA);
    }

    @Test
    public void testGetConstanta() {
        assertEquals(testFunction.getConstanta(), 666.1313, DELTA);
    }
}