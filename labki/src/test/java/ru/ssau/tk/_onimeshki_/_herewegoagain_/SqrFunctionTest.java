package ru.ssau.tk._onimeshki_._herewegoagain_;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.SqrFunction;

import static org.testng.Assert.*;
import static ru.ssau.tk._onimeshki_._herewegoagain_.Constants.DELTA;

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