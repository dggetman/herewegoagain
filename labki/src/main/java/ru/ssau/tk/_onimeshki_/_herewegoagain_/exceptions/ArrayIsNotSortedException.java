package ru.ssau.tk._onimeshki_._herewegoagain_.exceptions;

import java.io.Serializable;

public class ArrayIsNotSortedException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -1296633046662392056L;

    public ArrayIsNotSortedException() {
    }

    public ArrayIsNotSortedException(String message) {
        super(message);
    }
}
