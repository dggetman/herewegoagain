package ru.ssau.tk._onimeshki_._herewegoagain_;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.ConstantFunction;

import static org.testng.Assert.*;
import static ru.ssau.tk._onimeshki_._herewegoagain_.Constants.DELTA;

public class ConstantFunctionTest {
    ConstantFunction testFunction = new ConstantFunction(666.1313);
    ConstantFunction testFunction1 = new ConstantFunction(13.1313);

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