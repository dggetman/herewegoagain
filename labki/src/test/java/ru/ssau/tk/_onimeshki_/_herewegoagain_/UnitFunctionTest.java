package ru.ssau.tk._onimeshki_._herewegoagain_;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.UnitFunction;

public class UnitFunctionTest {
    UnitFunction testFunction = new UnitFunction();

    @Test
    public void testApply() {
        Assert.assertEquals(testFunction.apply(666.), 1, Constants.DELTA);
        Assert.assertEquals(testFunction.apply(Double.NEGATIVE_INFINITY), 1, Constants.DELTA);
        Assert.assertEquals(testFunction.apply(0), 1, Constants.DELTA);
    }

    @Test
    public void testGetNumber() {
        Assert.assertEquals(testFunction.getConstant(), 1, Constants.DELTA);
    }
}