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
     * 
     * @param originYear
     * @param finalYear
     * @param n
     * @return
     */
    // public String AnimalToString(int originYear, int finalYear, int n) {

    // int count = n;

    // // Construct output string
    // StringBuilder line = new StringBuilder();
    // line.append(this.name + ",");

    // for (int j = originYear; j < finalYear + 1; j++) {
    // ArrayList<Double> payments = new ArrayList<Double>();

    // for (int k = 0; k < count; k++) {

    // ArrayList<Integer> yearPair = new ArrayList<Integer>();
    // yearPair.add(j);
    // yearPair.add(j + k);

    // if (this.getRecords().keySet().contains(yearPair)) {

    // double payment = this.getRecords().get(yearPair);
    // int paymentCount = payments.size();

    // if (payments.size() > 0) {
    // payments.add(payment + payments.get(paymentCount - 1));
    // } else {
    // payments.add(payment);
    // }

    // if (j == finalYear && k == count - 1) {
    // line.append(isWholeEnd(payments.get(payments.size() - 1)));
    // } else {
    // line.append(isWhole(payments.get(payments.size() - 1)));
    // }

    // } else {
    // int paymentCount = payments.size();

    // if (payments.size() > 0) {
    // payments.add(payments.get(paymentCount - 1));
    // if (j == finalYear && k == count - 1) {
    // line.append(isWholeEnd(payments.get(payments.size() - 1)));
    // } else {
    // line.append(isWhole(payments.get(payments.size() - 1)));
    // }
    // } else {
    // payments.add(0.0);
    // if (j == finalYear && k == count - 1) {
    // line.append(isWholeEnd(payments.get(payments.size() - 1)));
    // } else {
    // line.append(isWhole(payments.get(payments.size() - 1)));
    // }
    // }

    // }

    // }
    // count--;
    // }
    // return line.toString();
    // }

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
     * @param payment
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
                        distance += euclideanDistance(x, y, xPrime, yPrime);
                        coordinate = newCoordinate;
                    }

                }
                Parser.writeToFile(parts[i] + ":" + distance + "\n", "Distances_log.txt");
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
