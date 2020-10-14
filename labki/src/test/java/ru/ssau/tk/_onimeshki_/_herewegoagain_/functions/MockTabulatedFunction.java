package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

public class MockTabulatedFunction extends AbstractTabulatedFunction {
    private final double x0 = 6;
    private final double x1 = 9;
    private final double y0 = 7;
    private final double y1 = 13;

    @Override
    protected int floorIndexOfX(double x) {
        if (x == x1) {
            return 1;
        }
        if (x > x1) {
            return 2;
        } else {
            return 0;
        }
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public double getX(int index) {
        if (index == 1) {
            return (x1);
        }
        return (x0);
    }

    @Override
    public double getY(int index) {
        if (index == 1) {
            return (y1);
        }
        return (y0);
    }

    @Override
    public void setY(int index, double value) {
    }

    @Override
    public int indexOfX(double x) {
        if (x == x1) {
            return (1);
        }
        if (x == x0) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public int indexOfY(double y) {
        if (y == y1) {
            return 1;
        }
        if (y == y0) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public double leftBound() {
        return x0;
    }

    @Override
    public double rightBound() {
        return x1;
    }
}
