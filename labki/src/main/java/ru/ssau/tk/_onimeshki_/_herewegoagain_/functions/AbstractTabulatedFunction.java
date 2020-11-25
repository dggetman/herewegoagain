package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

import ru.ssau.tk._onimeshki_._herewegoagain_.exceptions.*;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {

    public abstract int getCount();

    public abstract double getX(int index);

    public abstract double getY(int index);

    public abstract void setY(int index, double value);

    public abstract int indexOfX(double x);

    public abstract int indexOfY(double y);

    public abstract double leftBound();

    public abstract double rightBound();

    abstract protected int floorIndexOfX(double x);

    abstract protected double extrapolateLeft(double x);

    abstract protected double extrapolateRight(double x);

    public abstract double interpolate(double x, int floorIndex);

    public double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (rightY - leftY) * (x - leftX) / (rightX - leftX);
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        } else if (indexOfX(x) != -1) {
            return getY(indexOfX(x));
        } else {
            return interpolate(x, floorIndexOfX(x));
        }
    }

    protected static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException("Lengths of arrays are different");
        }
    }

    protected static void checkSorted(double[] xValues) {
        for (int i = 0; i < xValues.length - 1; i++) {
            if (xValues[i] >= xValues[i + 1]) {
                throw new ArrayIsNotSortedException("xValues array isn't sorted");
            }
        }
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(getClass().getSimpleName()).append(" size = ").append(this.getCount()).append("\n");

        for (Point point : this) {
            str.append("[")
                    .append(point.x)
                    .append("; ")
                    .append(point.y)
                    .append("]\n");
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }
}