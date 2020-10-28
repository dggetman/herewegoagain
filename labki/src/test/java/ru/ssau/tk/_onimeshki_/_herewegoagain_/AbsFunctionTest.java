package ru.ssau.tk._onimeshki_._herewegoagain_;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.AbsFunction;

public class AbsFunctionTest {
    @Test
    public void testApply() {
        AbsFunction testFunction = new AbsFunction();
        Assert.assertEquals(testFunction.apply(-6), 6, Constants.DELTA);
        Assert.assertEquals(testFunction.apply(Integer.MIN_VALUE + 1), Integer.MAX_VALUE, Constants.DELTA);
        Assert.assertEquals(testFunction.apply(6), 6, Constants.DELTA);
        Assert.assertEquals(testFunction.apply(0), 0, Constants.DELTA);
        Assert.assertEquals(testFunction.apply(Double.NEGATIVE_INFINITY), Double.POSITIVE_INFINITY, Constants.DELTA);
    }
}