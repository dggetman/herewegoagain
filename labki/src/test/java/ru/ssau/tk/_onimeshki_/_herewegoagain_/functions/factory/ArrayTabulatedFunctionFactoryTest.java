package ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionFactoryTest {
    private final double[] valuesX = new double[]{1, 2, 3, 4, 5, 6, 7};
    private final double[] valuesY = new double[]{11, 22, 33, 44, 55, 6, 7};

    @Test
    public void testCreate() {
        var testArrayFunction = new ArrayTabulatedFunctionFactory().create(valuesX, valuesY);
        assertTrue(testArrayFunction instanceof ArrayTabulatedFunction);
    }
}