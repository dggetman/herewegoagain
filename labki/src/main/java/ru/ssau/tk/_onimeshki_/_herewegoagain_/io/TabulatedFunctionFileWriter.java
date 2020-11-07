package ru.ssau.tk._onimeshki_._herewegoagain_.io;

import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {
        File outFile = new File("out/function.txt");
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(new SqrtFunction(), 0, 10, 11);
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(new SqrFunction(), 0, 10, 11);

       /* try (BufferedWriter fileWriterFirst = new BufferedWriter(new FileWriter(outFile));
             BufferedWriter fileWriterSecond = new BufferedWriter(new FileWriter(outFile))) {
            FunctionsIO.writeTabulatedFunction(fileWriterFirst, arrayTabulatedFunction);
            FunctionsIO.writeTabulatedFunction(fileWriterSecond, linkedListTabulatedFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
