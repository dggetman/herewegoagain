package ru.ssau.tk._onimeshki_._herewegoagain_;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.ZeroFunction;

import static org.testng.Assert.*;
import static ru.ssau.tk._onimeshki_._herewegoagain_.Constants.DELTA;

public class ZeroFunctionTest {
    ZeroFunction testFunction = new ZeroFunction();

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