package com.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Animal {

    public String name;
    public HashMap<String, ArrayList<ArrayList<Double>>> records = new HashMap<String, ArrayList<ArrayList<Double>>>();

    /**
     * 
     * @param name
     */
    public Animal(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return
     */
    public HashMap<String, ArrayList<ArrayList<Double>>> getRecords() {
        return records;
    }

    /**
     * Prints int/double values
     * not at the end of a line
     * 
     * @param payment
     * @return
     */

    private String isWhole(double payment) {

        if (payment % 1 == 0) {
            return (" " + (int) (0 + payment) + ",");
        } else {
            return (" " + payment + ",");
        }

    }

    /**
     * Prints int/double values
     * at the end of a line
     * 
     * @param payment
     * @return
     */
    private String isWholeEnd(double payment) {

        if (payment % 1 == 0) {
            return (" " + (int) (0 + payment));
        } else {
            return (" " + payment);
        }

    }

    /**
     * 
     */
    public void getDistances() {

        Parser.writeToFile(name + "\n", "Distances_log.txt");
        String[] parts = Parser.parts.toArray(new String[Parser.parts.size()]);
        for (int i = 0; i < records.keySet().size(); i++) {
            ArrayList<ArrayList<Double>> coordinates = records.get(parts[i]);
            double distance = 0;

            if (coordinates.size() <= 1) {
                distance = 0;
                Parser.writeToFile(parts[i] + ":" + distance + "\n", "Distances_log.txt");
            } else {
                ArrayList<Double> coordinate = new ArrayList<>();
                for (int j = 0; j < coordinates.size(); j++) {
                    if (j == 0) {
                        coordinate = coordinates.get(j);
                    } else {
                        ArrayList<Double> newCoordinate = coordinates.get(j);

                        double x = coordinate.get(0);
                        double y = coordinate.get(1);
                        double xPrime = newCoordinate.get(0);
                        double yPrime = newCoordinate.get(1);
                        if (x != -1 && y != -1 && xPrime != -1 && yPrime != -1) {
                            distance += euclideanDistance(x, y, xPrime, yPrime);
                        }
                        coordinate = newCoordinate;
                    }

                    if (j != 0 && j % Parser.TIME_BINS == 0) {
                        Parser.writeToFile(parts[i] + " distance travelled at second " + (j / Parser.TIME_BINS) + ":"
                                + distance + "\n", "Distances_log.txt");
                    }

                }
                Parser.writeToFile(parts[i] + " total distance:" + distance + "\n", "Output_log.txt");
            }
        }

    }

    /**
     * 
     * @param x
     * @param y
     * @param xPrime
     * @param yPrime
     * @return
     */
    private double euclideanDistance(double x, double y, double xPrime, double yPrime) {

        double distance = Math.sqrt(Math.pow(x - xPrime, 2) + (Math.pow(y - yPrime, 2)));
        return distance;

    }

}
