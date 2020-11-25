package ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;

import static org.testng.Assert.*;
import static ru.ssau.tk._onimeshki_._herewegoagain_.functions.Constants.DELTA;


public class LinkedListTabulatedFunctionFactoryTest {
    private final double[] valuesX = new double[]{1, 2, 3, 4, 5, 6, 7};
    private final double[] valuesY = new double[]{11, 22, 33, 44, 55, 6, 7};

    @Test
    public void testCreate() {
        var testListFunction = new LinkedListTabulatedFunctionFactory().create(valuesX, valuesY);
        assertTrue(testListFunction instanceof LinkedListTabulatedFunction);
    }

    @Test
    public void testCreateStrict() {
        var functionStrictList = new LinkedListTabulatedFunctionFactory().createStrict(valuesX, valuesY);

        Assert.assertEquals(functionStrictList.apply(1), 11, DELTA);
        Assert.assertEquals(functionStrictList.apply(5), 55, DELTA);
        assertThrows(UnsupportedOperationException.class, () -> functionStrictList.apply(-1));
        assertThrows(UnsupportedOperationException.class, () -> functionStrictList.apply(2.5));

    }
}