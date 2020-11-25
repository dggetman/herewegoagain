package ru.ssau.tk._onimeshki_._herewegoagain_.operations;

import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;

public interface DifferentialOperator<T extends MathFunction> {
    T derive(T function);

}
