package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 *
 */
public class App {

    /**
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        String filename = in.nextLine();
        in.close();

        // Check format
        if (!filename.endsWith(".csv")) {
            throw new IllegalArgumentException(filename);
        }

        // Get cumulative data output file
        cumulativeData(filename);

    }

    /**
     * 
     * @param filename
     * @throws IOException
     */

    public static void cumulativeData(String filename) throws IOException {

        Parser parser = new Parser(filename);
        Parser.parse(filename);
        System.out.println(Parser.allAnimals.keySet());

        Animal animal = Parser.allAnimals.get("individual1");
        // TODO: POSE parameters? Research
        animal.getDistances();

    }

    /**
     * 
     * @param output
     */
    public static void writeToFile(String output) {

        File file = new File("Output.csv");
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
