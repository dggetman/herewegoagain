package ru.ssau.tk._onimeshki_._herewegoagain_;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.IdentityFunction;

import static org.testng.Assert.*;
import static ru.ssau.tk._onimeshki_._herewegoagain_.Constants.DELTA;

public class IdentityFunctionTest {

    @Test
    public void testApply() {
        IdentityFunction testFunction = new IdentityFunction();
        assertEquals(testFunction.apply(6), 6, DELTA);
        assertEquals(testFunction.apply(-6), -6, DELTA);
        assertEquals(testFunction.apply(0), 0, DELTA);
        assertEquals(testFunction.apply(Double.POSITIVE_INFINITY), Double.POSITIVE_INFINITY, DELTA);
    }
}