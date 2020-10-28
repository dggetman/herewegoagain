package ru.ssau.tk._onimeshki_._herewegoagain_.functions;

public interface TabulatedFunction extends MathFunction, Iterable<Point> {

    int getCount();

    double getX(int index);

    double getY(int index);

    void setY(int index, double value);

    int indexOfX(double x);

    int indexOfY(double y);

    double leftBound();

    double rightBound();

    double apply(double x);
}
