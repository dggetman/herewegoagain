package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static ru.ssau.tk._onimeshki_._herewegoagain_.functions.Constants.DELTA;

public class UnitFunctionTest {
    UnitFunction testFunction = new UnitFunction();

    @Test
    public void testApply() {
        assertEquals(testFunction.apply(666.), 1, DELTA);
        assertEquals(testFunction.apply(Double.NEGATIVE_INFINITY), 1, DELTA);
        assertEquals(testFunction.apply(0), 1, DELTA);
    }

    @Test
    public void testGetNumber() {
        assertEquals(testFunction.getConstant(), 1, DELTA);
    }
}