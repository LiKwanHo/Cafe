package com.cafedemo.cafeproject.utils;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileWriter {

    public static void writeToFile(String data, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            writer.write(data);
            writer.newLine();
            writer.close();
            System.out.println("wrote: " + data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
