package ru.ssau.tk._onimeshki_._herewegoagain_.operations;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.exceptions.*;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory.*;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {
    double[] firstX = new double[]{1., 2., 3., 4.};
    double[] firstY = new double[]{2., 4., 6., 8.};
    double[] secondX = new double[]{1., 2., 3., 5.};
    double[] secondY = new double[]{1., 2., 3., 4.};

    private final TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
    private final TabulatedFunctionOperationService operationServiceThroughArray = new TabulatedFunctionOperationService();
    private final TabulatedFunctionOperationService operationServiceThroughLinkedList = new TabulatedFunctionOperationService(linkedListFactory);
    private final TabulatedFunction a = new ArrayTabulatedFunctionFactory().create(firstX, firstY);
    private final TabulatedFunction b = linkedListFactory.create(firstX, secondY);

    @Test
    public void testAsPoints() {
        TabulatedFunction array = new ArrayTabulatedFunctionFactory().create(firstX, firstY);
        Point[] points = TabulatedFunctionOperationService.asPoints(array);
        int i = 0;
        for (Point point : points) {
            assertEquals(point.x, array.getX(i));
            assertEquals(point.y, array.getY(i++));
        }
        assertEquals(array.getCount(), i);
    }

    @Test
    public void testAdd() {
        TabulatedFunction resultSumThroughArray = operationServiceThroughArray.add(a, b);
        TabulatedFunction resultSumThroughLinkedList = operationServiceThroughLinkedList.add(a, b);
        assertTrue(resultSumThroughArray instanceof ArrayTabulatedFunction);
        int i = 0;
        for (Point point : resultSumThroughArray) {
            assertEquals(point.x, firstX[i]);
            assertEquals(point.y, firstY[i] + secondY[i++]);
        }
        assertTrue(resultSumThroughLinkedList instanceof LinkedListTabulatedFunction);
        i = 0;
        for (Point point : resultSumThroughLinkedList) {
            assertEquals(point.x, firstX[i]);
            assertEquals(point.y, firstY[i] + secondY[i++]);
        }
        assertThrows(InconsistentFunctionsException.class, () ->
                operationServiceThroughLinkedList.add(a, linkedListFactory.create(secondX, secondY)));
        assertThrows(InconsistentFunctionsException.class, () -> {
            TabulatedFunction c = linkedListFactory.create(new double[]{11., 22., 33.}, new double[]{11., 22., 33.});
            operationServiceThroughLinkedList.add(a, c);
        });
    }

    @Test
    public void testSubtract() {
        TabulatedFunction resultSubtractThroughArray = operationServiceThroughArray.subtract(a, b);
        TabulatedFunction resultSubtractThroughLinkedList = operationServiceThroughLinkedList.subtract(a, b);
        assertTrue(resultSubtractThroughArray instanceof ArrayTabulatedFunction);
        int i = 0;
        for (Point point : resultSubtractThroughArray) {
            assertEquals(point.x, firstX[i]);
            assertEquals(point.y, secondY[i++]);
        }
        assertTrue(resultSubtractThroughLinkedList instanceof LinkedListTabulatedFunction);
        i = 0;
        for (Point point : resultSubtractThroughLinkedList) {
            assertEquals(point.x, firstX[i]);
            assertEquals(point.y, secondY[i++]);
        }
    }

    @Test
    public void testGetFactory() {
        assertTrue((new TabulatedFunctionOperationService()).getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue((new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory())).getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSetFactory() {
        TabulatedFunctionOperationService obj = new TabulatedFunctionOperationService();
        obj.setFactory(new LinkedListTabulatedFunctionFactory());
        assertTrue(obj.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testMultiply() {
        TabulatedFunction resultMultiplyThroughArray = operationServiceThroughArray.multiply(a, b);
        TabulatedFunction resultMultiplyThroughLinkedList = operationServiceThroughLinkedList.multiply(a, b);
        assertTrue(resultMultiplyThroughArray instanceof ArrayTabulatedFunction);
        int i = 0;
        for (Point point : resultMultiplyThroughArray) {
            assertEquals(point.x, firstX[i]);
            assertEquals(point.y, firstY[i] * secondY[i++]);
        }
        assertTrue(resultMultiplyThroughLinkedList instanceof LinkedListTabulatedFunction);
        i = 0;
        for (Point point : resultMultiplyThroughLinkedList) {
            assertEquals(point.x, firstX[i]);
            assertEquals(point.y, firstY[i] * secondY[i++]);
        }
    }

    @Test
    public void testDivide() {
        TabulatedFunction resultDivideThroughArray = operationServiceThroughArray.divide(a, b);
        TabulatedFunction resultDivideThroughLinkedList = operationServiceThroughLinkedList.divide(a, b);
        assertTrue(resultDivideThroughArray instanceof ArrayTabulatedFunction);
        int i = 0;
        for (Point point : resultDivideThroughArray) {
            assertEquals(point.x, firstX[i++]);
            assertEquals(point.y, 2.);
        }
        assertTrue(resultDivideThroughLinkedList instanceof LinkedListTabulatedFunction);
        i = 0;
        for (Point point : resultDivideThroughLinkedList) {
            assertEquals(point.x, firstX[i++]);
            assertEquals(point.y, 2.);
        }
    }
}