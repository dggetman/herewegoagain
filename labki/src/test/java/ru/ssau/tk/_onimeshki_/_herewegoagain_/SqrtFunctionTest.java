package ru.ssau.tk._onimeshki_._herewegoagain_;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.SqrtFunction;

import static org.testng.Assert.*;
import static ru.ssau.tk._onimeshki_._herewegoagain_.Constants.DELTA;

public class SqrtFunctionTest {

    @Test
    public void testApply() {
        SqrtFunction testFunction = new SqrtFunction();
        assertEquals(testFunction.apply(36), 6, DELTA);
        assertNotEquals(testFunction.apply(Double.POSITIVE_INFINITY), DELTA);
        assertEquals(testFunction.apply(0), 0, DELTA);
        assertEquals(testFunction.apply(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY, DELTA);
    }
}