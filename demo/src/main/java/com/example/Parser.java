package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Parser {

    public String name;
    public static Set<String> products = new HashSet<>();
    public static Set<String> animals = new HashSet<>();
    public static Set<String> parts = new HashSet<>();
    public static Set<Integer> originYears = new HashSet<>();
    public static Set<Integer> developmentYears = new HashSet<>();
    public static HashMap<String, Product> allProducts = new HashMap<String, Product>();

    public Parser(String filename) {
        this.name = filename;
    }

    /**
     * 
     * @param filename
     * @throws IOException
     */
    public static void parse(String filename) throws IOException {

        ArrayList<String> content = (ArrayList<String>) Files
                .readAllLines(Paths.get(System.getProperty("user.dir") + File.separator + filename));

        HashSet<String> nameRow = getAnimals(content.get(1));
        HashSet<String> partRow = getParts(content.get(2));

        System.out.println(animals);
        System.out.println(parts);

        // TODO: Check th file is not empty
        // TODO: Remove empty lines (have size of 1)
        // NB: The empty bits are equal to ""

        // for (int j = 1; j < content.size(); j++) {

        // // Get every row of the file
        // String[] row = content.get(j).replaceAll("\"", " ").split(",");

        // if (row == null) {
        // System.out.println("Error message: " + "Empty row, Value Ignored in line" +
        // j);
        // continue;
        // }

        // // Check validity of origin or development year
        // if (row[1] == null || row[2] == null) {
        // System.out.println("Error message: " + "Invalid value of Origin year [" +
        // row[1] + "] or development year ["
        // + row[2] + "], Value Ignored in line " + j);
        // continue;
        // }

        // int originYear;
        // int developmentYear;

        // try {
        // originYear = Integer.valueOf(row[1].trim());
        // developmentYear = Integer.valueOf(row[2].trim());
        // } catch (NumberFormatException e) {
        // // handle exception
        // System.out.println("Error message: " + "Invalid types of Origin year [" +
        // row[1] + "] or development year ["
        // + row[2] + "], Values Ignored in line " + j);
        // continue;
        // }

        // // Check validity of origin or development year
        // if (originYear < 0 || developmentYear < 0) {
        // System.out.println("Error message: " + "Invalid value of Origin year [" +
        // row[1] + "] or development year ["
        // + row[2] + "], Value Ignored");
        // continue;
        // }

        // // Check if valid combination of origin and development years
        // if (originYear > developmentYear) {
        // System.out.println("Error message: " + "Origin Year [" + row[1] +
        // "] greater than Development Year ["
        // + row[2] + "] Value Ignored in line " + j);
        // continue;
        // }

        // ArrayList<Integer> keys = new ArrayList<Integer>();
        // keys.add(originYear);
        // keys.add(developmentYear);

        // double payment;
        // try {
        // payment = Double.valueOf(row[3]);
        // } catch (NumberFormatException e) {
        // // handle exception
        // System.out.println("Error message: " + "Invalid types of Origin year [" +
        // row[1] + "] or development year ["
        // + row[2] + "], Values Ignored in line" + j);
        // continue;

        // }

        // // Update the origin and development year lists
        // originYears.add(originYear);
        // developmentYears.add(developmentYear);

        // makeProduct(row[0], keys, payment);

        // }

    }

    /**
     * 
     * @param productName
     * @param yearPairs
     * @param payment
     */

    private static void makeProduct(String productName, ArrayList<Integer> yearPairs, double payment) {

        // Create product objects or update their records
        if (!products.contains(productName)) {
            Product product = new Product(productName);
            allProducts.put(productName, product);
            product.getRecords().put(yearPairs, payment);
            products.add(productName);
        } else {
            allProducts.get(productName).getRecords().put(yearPairs, payment);
            products.add(productName);
        }

    }

    /**
     * 
     * @return
     */
    public Set<String> getProducts() {
        return products;
    }

    /**
     * 
     * @return
     */
    public HashMap<String, Product> getAllProducts() {
        return allProducts;
    }

    /**
     * 
     * @return
     */
    public Set<Integer> getOriginYears() {
        return originYears;
    }

    /**
     * 
     * @return
     */
    public Set<Integer> getDevelopmentYears() {
        return developmentYears;
    }

    /**
     * 
     * @param line
     * @return
     */
    private static HashSet<String> getAnimals(String line) {

        String[] namesRow = line.replaceAll("\"", " ").split(",");
        List<String> list = Arrays.asList(namesRow);
        HashSet<String> nameSet = new HashSet<>();
        nameSet.addAll(list);

        nameSet.remove("individuals");
        animals = nameSet;
        return nameSet;
    }

    /**
     * 
     * @param line
     * @return
     */
    private static HashSet<String> getParts(String line) {

        String[] partsRow = line.replaceAll("\"", " ").split(",");
        List<String> list = Arrays.asList(partsRow);
        HashSet<String> partSet = new HashSet<>();
        partSet.addAll(list);

        partSet.remove("bodyparts");
        parts = partSet;
        return partSet;
    }

    /**
     * 
     * @param file
     * @param animal
     * @return
     */
    private static HashSet<String> getTracks(ArrayList<String> file, int animal) {

        int index = animal * parts.size() * 3 + 1;
        String[] nameRow = file.get(1).replaceAll("\"", " ").split(",");
        String name = nameRow[index];
        Animal newAnimal = new Animal(name);
        HashMap<String, ArrayList<ArrayList<Double>>> partTracks = new HashMap<String, ArrayList<ArrayList<Double>>>();

        // For every body part labelled for animal at index get the bodyparts and their
        // coordinates in all the frames
        for (int i = index; i < index + 13; i = i + 3) {
            String[] partRow = file.get(2).replaceAll("\"", " ").split(",");
            String part = partRow[index];

            ArrayList<ArrayList<Double>> partCoordinates = new ArrayList<ArrayList<Double>>();

            // Traverse through the file and get the coordinates for the bodypart at index i
            for (int j = 4; j < file.size(); j++) {
                String[] valuesRow = file.get(j).replaceAll("\"", " ").split(",");
                ArrayList<Double> coordinates = new ArrayList<Double>();

                if (!valuesRow[i].equals("") && !valuesRow[i + 1].equals("")) {
                    coordinates.add(Double.parseDouble(valuesRow[i]));
                    coordinates.add(Double.parseDouble(valuesRow[i + 1]));
                }

                partCoordinates.add(coordinates);
            }

            newAnimal.records.put(part, partCoordinates);
        }

    }

}