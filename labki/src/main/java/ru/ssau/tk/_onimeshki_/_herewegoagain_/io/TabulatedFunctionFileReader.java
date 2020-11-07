package ru.ssau.tk._onimeshki_._herewegoagain_.io;

import ru.ssau.tk._onimeshki_._herewegoagain_.functions.*;
import ru.ssau.tk._onimeshki_._herewegoagain_.functions.factory.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TabulatedFunctionFileReader {
    public static void main(String[] args) {
        File inputFile = new File("input/function.txt");
        try (BufferedReader readerArr = new BufferedReader(new FileReader(inputFile));
             BufferedReader readerLink = new BufferedReader(new FileReader(inputFile))) {
            TabulatedFunction arrayFunction = FunctionsIO.readTabulatedFunction(readerArr, new ArrayTabulatedFunctionFactory());
            System.out.println(arrayFunction.toString());

            TabulatedFunction listFunction = FunctionsIO.readTabulatedFunction(readerLink, new LinkedListTabulatedFunctionFactory());
            System.out.println(listFunction.toString());
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
