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
        TabulatedFunction strictListFunction = listFactoryS.createStrict(x, y);
        assertTrue(strictListFunction instanceof StrictTabulatedFunction);

        TabulatedFunctionFactory arrayFactoryS = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction strictArrayFunction = arrayFactoryS.createStrict(x, y);
        assertTrue(strictArrayFunction instanceof StrictTabulatedFunction);

        TabulatedFunctionFactory listFactoryU = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction unmodifiableListFunction = listFactoryU.createUnmodifiable(x, y);
        assertTrue(unmodifiableListFunction instanceof UnmodifiableTabulatedFunction);

        TabulatedFunctionFactory arrayFactoryU = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction unmodifiableArrayFunction = arrayFactoryU.createUnmodifiable(x, y);
        assertTrue(unmodifiableArrayFunction instanceof UnmodifiableTabulatedFunction);


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