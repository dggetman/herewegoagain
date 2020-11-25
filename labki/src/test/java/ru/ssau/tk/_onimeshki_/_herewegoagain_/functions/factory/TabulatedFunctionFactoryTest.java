package ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;

import static org.testng.Assert.*;

public class TabulatedFunctionFactoryTest {
    double[] x = {-1., 6., 9.};
    double[] y = {-10., 60., 90.};

    @Test
    public void testCreateStrictAndUnmodifiable() {
        TabulatedFunctionFactory listFactoryD = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction strictUnmodifiableListFunction = listFactoryD.createStrictUnmodifiable(x, y);
        assertThrows(UnsupportedOperationException.class, () -> strictUnmodifiableListFunction.setY(0, 0));
        assertThrows(UnsupportedOperationException.class, () -> strictUnmodifiableListFunction.apply(0));

        TabulatedFunctionFactory arrayFactoryD = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction strictUnmodifiableArrayFunction = arrayFactoryD.createStrictUnmodifiable(x, y);
        assertThrows(UnsupportedOperationException.class, () -> strictUnmodifiableArrayFunction.setY(1, 0));
        assertThrows(UnsupportedOperationException.class, () -> strictUnmodifiableListFunction.apply(0));

    }

    @Test
    public void testCreateUnmodifiable() {
        TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunction unmodifiableArr = arrayFactory.createUnmodifiable(x, y);
        TabulatedFunctionFactory listFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction unmodifiableLis = listFactory.createUnmodifiable(x, y);
        assertTrue(unmodifiableArr instanceof UnmodifiableTabulatedFunction);
        assertTrue(unmodifiableLis instanceof UnmodifiableTabulatedFunction);

    }
}