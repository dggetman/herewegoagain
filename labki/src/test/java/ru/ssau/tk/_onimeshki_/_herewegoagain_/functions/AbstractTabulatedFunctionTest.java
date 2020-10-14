package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static ru.ssau.tk._onimeshki_._herewegoagain_.functions.Constants.DELTA;

public class AbstractTabulatedFunctionTest {
    public MockTabulatedFunction mockObj = new MockTabulatedFunction();

    @Test
    public void testInterpolate() {
        assertEquals(mockObj.interpolate(3, 6, 9, 7, 3), 11, DELTA);
    }

    @Test
    public void testApply() {
        assertEquals(mockObj.apply(7), 9, DELTA);
        assertEquals(mockObj.apply(-7), -19, DELTA);
        assertNotEquals(mockObj.apply(2), 1, DELTA);
        assertNotEquals(mockObj.apply(1), 5, DELTA);
    }
}