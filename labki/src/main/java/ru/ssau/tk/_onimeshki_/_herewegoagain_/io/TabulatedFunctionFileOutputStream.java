package ru.ssau.tk._onimeshki_._herewegoagain_.io;

import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;

import java.io.*;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        File arrayFile = new File("output/array function.bin");
        File listFile = new File("output/linked list function.bin");
        double[] xValues = {1, 2, 3};
        double[] yValues = {11, 22, 33};
        ArrayTabulatedFunction arrayFunc = new ArrayTabulatedFunction(xValues, yValues);
        LinkedListTabulatedFunction linkFunc = new LinkedListTabulatedFunction(xValues, yValues);
        try (BufferedOutputStream outputStreamArr = new BufferedOutputStream(new FileOutputStream(arrayFile));
             BufferedOutputStream outputStreamLink = new BufferedOutputStream(new FileOutputStream(listFile))) {
            FunctionsIO.writeTabulatedFunction(outputStreamArr, arrayFunc);
            FunctionsIO.writeTabulatedFunction(outputStreamLink, linkFunc);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
