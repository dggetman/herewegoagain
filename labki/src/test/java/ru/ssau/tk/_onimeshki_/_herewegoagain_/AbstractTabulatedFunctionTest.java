package ru.ssau.tk._onimeshki_._herewegoagain_;

import ru.ssau.tk._onimeshki_._herewegoagain_.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk._onimeshki_._herewegoagain_.exceptions.DifferentLengthOfArraysException;

import org.testng.annotations.Test;

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
}