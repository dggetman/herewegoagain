package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AbsFunctionTest {

    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        AbsFunction testFunction = new AbsFunction();
        assertEquals(testFunction.apply(-6), 6, DELTA);
    }
}