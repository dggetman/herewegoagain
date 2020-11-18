package ru.ssau.tk._onimeshki_._herewegoagain_.operations;

import org.testng.annotations.Test;
import ru.ssau.tk._onimeshki_._herewegoagain_.exceptions.InconsistentFunctionsException;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory.*;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {
    double[] firstX = new double[]{1., 2., 3., 4.};
    double[] firstY = new double[]{2., 4., 6., 8.};
    double[] secondX = new double[]{1., 2., 3., 5.};
    double[] secondY = new double[]{1., 2., 3., 4.};

    private TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
    private TabulatedFunctionOperationService operationServiceThroughArray = new TabulatedFunctionOperationService();
    private TabulatedFunctionOperationService operationServiceThroughLinkedList = new TabulatedFunctionOperationService(linkedListFactory);
    private TabulatedFunction a = new ArrayTabulatedFunctionFactory().create(firstX, firstY);
    private TabulatedFunction b = linkedListFactory.create(firstX, secondY);

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
        int i = 0;
        for (Point point : resultSumThroughArray) {
            assertEquals(point.x, firstX[i]);
            assertEquals(point.y, firstY[i] + secondY[i++]);
        }
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
        TabulatedFunction resultSumThroughArray = operationServiceThroughArray.subtract(a, b);
        TabulatedFunction resultSumThroughLinkedList = operationServiceThroughLinkedList.subtract(a, b);
        int i = 0;
        for (Point point : resultSumThroughArray) {
            assertEquals(point.x, firstX[i]);
            assertEquals(point.y, secondY[i++]);
        }
        i = 0;
        for (Point point : resultSumThroughLinkedList) {
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
        TabulatedFunction resultSumThroughArray = operationServiceThroughArray.multiply(a, b);
        TabulatedFunction resultSumThroughLinkedList = operationServiceThroughLinkedList.multiply(a, b);
        int i = 0;
        for (Point point : resultSumThroughArray) {
            assertEquals(point.x, firstX[i]);
            assertEquals(point.y, firstY[i] * secondY[i++]);
        }
        i = 0;
        for (Point point : resultSumThroughLinkedList) {
            assertEquals(point.x, firstX[i]);
            assertEquals(point.y, firstY[i] * secondY[i++]);
        }
    }

    @Test
    public void testDivide() {
        TabulatedFunction resultSumThroughArray = operationServiceThroughArray.divide(a, b);
        TabulatedFunction resultSumThroughLinkedList = operationServiceThroughLinkedList.divide(a, b);
        int i = 0;
        for (Point point : resultSumThroughArray) {
            assertEquals(point.x, firstX[i++]);
            assertEquals(point.y, 2.);
        }
        i = 0;
        for (Point point : resultSumThroughLinkedList) {
            assertEquals(point.x, firstX[i++]);
            assertEquals(point.y, 2.);
        }
    }
}