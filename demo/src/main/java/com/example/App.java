package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

        

        // System.out.println(filename);
        // ArrayList<Integer> sortedOrigin = new
        // ArrayList<Integer>(parser.getOriginYears());
        // ArrayList<Integer> sortedDevelopment = new
        // ArrayList<Integer>(parser.getDevelopmentYears());
        // ArrayList<String> productList = new ArrayList<String>(parser.getProducts());

        // Collections.sort(sortedOrigin);
        // Collections.sort(sortedDevelopment);

        // // Output String instantiation
        // StringBuilder output = new StringBuilder();

        // // Construct first line of output
        // // System.out.println(parser.getOriginYears() + ", " +
        // // sortedDevelopment.size());
        // output.append(sortedOrigin.get(0) + ", " + sortedDevelopment.size() + "\n");

        // // Construct the output by line per product
        // for (int i = 0; i < parser.getProducts().size(); i++) {
        // String product = productList.get(i);

        // // No of development years = last development year - earliest origin year
        // // No of origin years = final origin year - earliest origin year
        // int originYear = sortedOrigin.get(0);
        // int finalYear = sortedOrigin.get(sortedOrigin.size() - 1);
        // int n = sortedDevelopment.get(sortedDevelopment.size() - 1) - originYear + 1;

        // System.out.println(parser.getAllProducts().get(product).ProductToString(originYear,
        // finalYear, n));
        // if (i == parser.getProducts().size() - 1) {
        // output.append(parser.getAllProducts().get(product).ProductToString(originYear,
        // finalYear, n));
        // } else {
        // output.append(parser.getAllProducts().get(product).ProductToString(originYear,
        // finalYear, n) + "\n");
        // }

        // }

        // writeToFile(output.toString());
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
