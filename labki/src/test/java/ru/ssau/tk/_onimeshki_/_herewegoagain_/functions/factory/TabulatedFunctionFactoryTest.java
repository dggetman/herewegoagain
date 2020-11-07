package ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;

import static org.testng.Assert.*;

public class TabulatedFunctionFactoryTest {

    @Test
    public void testCreateStrictAndUnmodifiable() {
        double[] x = {-1., 6., 9.};
        double[] y = {-10., 60., 90.};

        TabulatedFunctionFactory listFactoryS = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction strictListFunction = listFactoryS.createStrict(x, y);
        assertTrue(strictListFunction instanceof StrictTabulatedFunction);

        TabulatedFunctionFactory arrayFactoryS = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction strictArrayFunction = arrayFactoryS.createStrict(x, y);
        assertTrue(strictArrayFunction instanceof StrictTabulatedFunction);

        TabulatedFunctionFactory listFactoryD = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction strictUnmodifiableListFunction = listFactoryD.createStrictUnmodifiable(x, y);
        assertTrue(strictUnmodifiableListFunction instanceof StrictTabulatedFunction);
        assertThrows(UnsupportedOperationException.class, () -> strictUnmodifiableListFunction.setY(0, 0));

        TabulatedFunctionFactory arrayFactoryD = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction strictUnmodifiableArrayFunction = arrayFactoryD.createStrictUnmodifiable(x, y);
        assertTrue(strictUnmodifiableArrayFunction instanceof StrictTabulatedFunction);
        assertThrows(UnsupportedOperationException.class, () -> strictUnmodifiableArrayFunction.setY(1, 0));
    }
}