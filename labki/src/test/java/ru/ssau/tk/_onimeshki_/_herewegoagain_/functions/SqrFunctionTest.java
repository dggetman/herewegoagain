package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import ru.ssau.tk._onimeshki_._herewegoagain_.functions.MathFunction;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.SqrFunction;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static ru.ssau.tk._onimeshki_._herewegoagain_.functions.Constants.DELTA;

public class SqrFunctionTest {

    @Test
    public void testApply() {
        SqrFunction testFunction = new SqrFunction();
        assertEquals(testFunction.apply(6), 36, DELTA);
        assertNotEquals(0, DELTA);
        assertEquals(testFunction.apply(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY, DELTA);
        assertNotEquals(-6, DELTA);
    }
}