package ru.ssau.tk._onimeshki_._herewegoagain_.operations;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;

import static ru.ssau.tk._onimeshki_._herewegoagain_.functions.Constants.DELTA;
import static org.testng.Assert.*;

public class LeftSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        double step = 0.005;
        SteppingDifferentialOperator differentialOperator = new LeftSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(6), 11.995, DELTA);
    }
}