package ru.ssau.tk._onimeshki_._herewegoagain_;

import ru.ssau.tk._onimeshki_._herewegoagain_.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk._onimeshki_._herewegoagain_.exceptions.DifferentLengthOfArraysException;


import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.ArrayTabulatedFunction;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.LinkedListTabulatedFunction;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.TabulatedFunction;

import static org.testng.Assert.*;
import static ru.ssau.tk._onimeshki_._herewegoagain_.Constants.DELTA;

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
            double[] valuesX = new double[]{-13., 6};
            double[] valuesY = new double[]{9.};
            mockObj.checkLengthIsTheSame(valuesX, valuesY);
        });
    }

    @Test
    public void checkSorted() {
        assertThrows(ArrayIsNotSortedException.class, () -> {
            double[] valuesX = new double[]{-13., 0, 1, 6, 10, -11};
            mockObj.checkSorted(valuesX);
        });
    }
    @Test
    public void testTestToString() {
        double[] x = {1, 2, 3};
        double[] y = {11, 22, 33};
        TabulatedFunction arrayFunction = new ArrayTabulatedFunction(x, y);
        assertEquals(arrayFunction.toString(), "ArrayTabulatedFunction size = 3\n[1.0; 11.0]\n[2.0; 22.0]\n[3.0; 33.0]");

        TabulatedFunction listFunction = new LinkedListTabulatedFunction(x, y);
        assertEquals(listFunction.toString(), "LinkedListTabulatedFunction size = 3\n[1.0; 11.0]\n[2.0; 22.0]\n[3.0; 33.0]");
    }
}