package ru.ssau.tk._onimeshki_._herewegoagain_.io;

import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(new SqrtFunction(), 0, 10, 11);
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(new SqrFunction(), 0, 10, 11);

        try (BufferedWriter fileWriterFirst = new BufferedWriter(new FileWriter("output/arr function.txt"));
             BufferedWriter fileWriterSecond = new BufferedWriter(new FileWriter("output/link function.txt"))) {
            FunctionsIO.writeTabulatedFunction(fileWriterFirst, arrayTabulatedFunction);
            FunctionsIO.writeTabulatedFunction(fileWriterSecond, linkedListTabulatedFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
