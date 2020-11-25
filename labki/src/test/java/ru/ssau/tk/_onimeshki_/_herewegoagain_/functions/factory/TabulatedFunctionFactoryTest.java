package ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;

import static org.testng.Assert.*;

public class TabulatedFunctionFactoryTest {
    double[] x = {-1., 6., 9.};
    double[] y = {-10., 60., 90.};

    @Test
    public void testCreateStrictAndUnmodifiable() {
        TabulatedFunctionFactory listFactoryS = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction strictListFunction = listFactoryS.createUnmodifiable(x, y);
        assertTrue(strictListFunction instanceof UnmodifiableTabulatedFunction);

        TabulatedFunctionFactory arrayFactoryS = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction strictArrayFunction = arrayFactoryS.createUnmodifiable(x, y);
        assertTrue(strictArrayFunction instanceof UnmodifiableTabulatedFunction);

        TabulatedFunctionFactory listFactoryU = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction unmodifiableListFunction = listFactoryU.createStrict(x, y);
        assertTrue(unmodifiableListFunction instanceof StrictTabulatedFunction);

        TabulatedFunctionFactory arrayFactoryU = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction unmodifiableArrayFunction = arrayFactoryU.createStrict(x, y);
        assertTrue(unmodifiableArrayFunction instanceof StrictTabulatedFunction);


        TabulatedFunctionFactory listFactoryD = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction strictUnmodifiableListFunction = listFactoryD.createStrictUnmodifiable(x, y);
        assertTrue(strictUnmodifiableListFunction instanceof StrictTabulatedFunction);
        assertThrows(UnsupportedOperationException.class, () -> strictUnmodifiableListFunction.setY(0, 0));

        TabulatedFunctionFactory arrayFactoryD = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction strictUnmodifiableArrayFunction = arrayFactoryD.createStrictUnmodifiable(x, y);
        assertTrue(strictUnmodifiableArrayFunction instanceof StrictTabulatedFunction);
        assertThrows(UnsupportedOperationException.class, () -> strictUnmodifiableArrayFunction.setY(1, 0));
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