package ru.ssau.tk._onimeshki_._herewegoagain_.functions;


import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.exceptions.*;

import static org.testng.Assert.*;
import static ru.ssau.tk._onimeshki_._herewegoagain_.functions.Constants.DELTA;

public class AbstractTabulatedFunctionTest {
    public MockTabulatedFunction mockObj = new MockTabulatedFunction();

    @Test
    public void testInterpolate() {
        assertEquals(mockObj.interpolate(3, 6, 9, 7, 3), 11, DELTA);
    }

    @Test
    public void testApply() {
        assertEquals(mockObj.apply(7), 9, DELTA);
        assertEquals(mockObj.apply(-7), -19, DELTA);
        assertNotEquals(mockObj.apply(2), 1, DELTA);
        assertNotEquals(mockObj.apply(1), 5, DELTA);
    }

    @Test
    public void checkLengthIsTheSame() {
        assertThrows(DifferentLengthOfArraysException.class, () -> {
            double[] x = new double[]{-3., 5};
            double[] y = new double[]{9.};
            AbstractTabulatedFunction.checkLengthIsTheSame(x, y);
        });
    }

    @Test
    public void checkSorted() {
        assertThrows(ArrayIsNotSortedException.class, () -> {
            double[] x = new double[]{-13., 0, 1, 6, 10, -11};
            AbstractTabulatedFunction.checkSorted(x);
        });

        double[] x = new double[]{1, 5, 9, 10, 16};
        AbstractTabulatedFunction.checkSorted(x);
    }
    @Test
    public void testTestToString() {
        double[] valuesX = {1, 2, 3};
        double[] valuesY = {11, 22, 33};
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(valuesX, valuesY);
        assertEquals(arrayFunction.toString(), "ArrayTabulatedFunction size = 3\n[1.0; 11.0]\n[2.0; 22.0]\n[3.0; 33.0]");

        TabulatedFunction listFunction = new LinkedListTabulatedFunction(valuesX, valuesY);
        assertEquals(listFunction.toString(), "LinkedListTabulatedFunction size = 3\n[1.0; 11.0]\n[2.0; 22.0]\n[3.0; 33.0]");
    }
}