package ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.LinkedListTabulatedFunction;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionFactoryTest {
    private final double[] valuesX = new double[]{1, 2, 3, 4, 5, 6, 7};
    private final double[] valuesY = new double[]{11, 22, 33, 44, 55, 6, 7};

    @Test
    public void testCreate() {
        var testListFunction = new LinkedListTabulatedFunctionFactory().create(valuesX, valuesY);
        assertTrue(testListFunction instanceof LinkedListTabulatedFunction);
    }
}