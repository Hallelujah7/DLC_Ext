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

}
