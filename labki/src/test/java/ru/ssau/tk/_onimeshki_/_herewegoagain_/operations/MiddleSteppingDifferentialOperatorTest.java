package ru.ssau.tk._onimeshki_._herewegoagain_.operations;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;
import static ru.ssau.tk._onimeshki_._herewegoagain_.functions.Constants.DELTA;


import static org.testng.Assert.*;

public class MiddleSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        double step = 0.005;
        SteppingDifferentialOperator differentialOperator = new MiddleSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(6), 12, DELTA);
    }
}