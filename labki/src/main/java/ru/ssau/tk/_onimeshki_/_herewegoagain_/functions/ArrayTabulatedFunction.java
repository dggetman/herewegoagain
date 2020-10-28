package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import ru.ssau.tk._onimeshki_._herewegoagain_.exceptions.InterpolationException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable {
    private double[] xValues;
    private double[] yValues;
    private int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Less than minimum length");
        }
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Less than minimum length");
        }
        if ((xFrom >= xTo) || (xFrom < 0) | (xTo < 0)) {
            throw new IllegalArgumentException("Incorrect parameter values");
        }
        this.count = count;
        xValues = new double[count];
        yValues = new double[count];

        double step = (xTo - xFrom) / (count - 1);
        double xMomentValue = xFrom;

        for (int i = 0; i < count; i++) {
            xValues[i] = xMomentValue;
            yValues[i] = source.apply(xMomentValue);
            xMomentValue += step;
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        yValues[index] = value;
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (xValues[i] == x) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (yValues[i] == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    public int floorIndexOfX(double x) {
        if (x < xValues[0]) {
            throw new IllegalArgumentException("X is less than the left border");
        }
        for (int i = 0; i + 1 < count; i++) {
            if (xValues[i + 1] > x) {
                return i;
            }
        }
        return count;
    }

    @Override
    public double extrapolateLeft(double x) {
        if (count == 1) {
            return x;
        }
        return interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    public double extrapolateRight(double x) {
        if (count == 1) {
            return x;
        }
        return interpolate(x, xValues[count - 2], xValues[count - 1], yValues[count - 2], yValues[count - 1]);
    }

    @Override
    public double interpolate(double x, int floorIndex) {
        if (x < xValues[floorIndex] || xValues[floorIndex + 1] < x) {
            throw new InterpolationException("X is out of bounds of interpolation");
        }
        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
    }

    @Override
    public void insert(double x, double y) {
        int indexOfX = indexOfX(x);
        if (indexOfX != -1) {
            setY(indexOfX, y);
        }
        double[] newXValues = new double[count + 1];
        double[] newYValues = new double[count + 1];
        System.arraycopy(xValues, 0, newXValues, 0, count);
        System.arraycopy(yValues, 0, newYValues, 0, count);
        newXValues[count] = x;
        newYValues[count] = y;
        this.xValues = newXValues;
        this.yValues = newYValues;
        count++;
    }

    @Override
    public void remove(int index) {
        double[] xTempValues = new double[count - 1];
        double[] yTempValues = new double[count - 1];

        System.arraycopy(xValues, 0, xTempValues, 0, index);
        System.arraycopy(yValues, 0, yTempValues, 0, index);
        System.arraycopy(xValues, index + 1, xTempValues, index, count - index - 1);
        System.arraycopy(yValues, index + 1, yTempValues, index, count - index - 1);
        this.xValues = xTempValues;
        this.yValues = yTempValues;
        count--;
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<>() {
            private int i = 0;

            public boolean hasNext() {
                return i < count;
            }

            public Point next() {
                if (!hasNext()) {
                    throw new UnsupportedOperationException();
                }

                Point point = new Point(xValues[i], yValues[i]);
                i++;
                return point;
            }
        };
    }
}
