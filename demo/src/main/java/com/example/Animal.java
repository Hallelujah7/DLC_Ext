package com.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Animal {

    public String name;
    public HashMap<String, ArrayList<ArrayList<Double>>> data = new HashMap<String, ArrayList<ArrayList<Double>>>();
    public ArrayList<Behaviour> behaviours = new ArrayList<Behaviour>();

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
    public HashMap<String, ArrayList<ArrayList<Double>>> getdata() {
        return data;
    }


     /**
      * 
      */
    public void getDistances() {

        Parser.writeToFile(name + "\n", "Distances_log.txt");
        String[] parts = Parser.parts.toArray(new String[Parser.parts.size()]);
        for (int i = 0; i < data.keySet().size(); i++) {
            ArrayList<ArrayList<Double>> coordinates = data.get(parts[i]);
            double distance = 0;

            if (coordinates.size() <= 1) {
                distance = 0;
                Parser.writeToFile(parts[i] + ":" + distance + "\n", "Distances_log.txt");
            } else {
                Parser.writeToFile(parts[i] + "\n", "Distances_log.txt");
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

                    if (j != 0 && j % (Parser.TIME_BINS * 60) == 0) {
                        Parser.writeToFile(
                           // (j / Parser.TIME_BINS * 60) + "," +
                                distance + "," + "\n", "Distances_log.txt");
                    }

                }
                Parser.writeToFile(parts[i] + " total distance:" + distance + "\n", "Output_log.txt");
            }
        }

    }

    /**
     * 
     * @param name
     * @param bodypart
     * @param parameter
     * @return
     */
    public Behaviour makeBehaviour(String name, String bodypart, int parameter) {
        Behaviour behaviour = new Behaviour(name, bodypart, parameter);
        behaviours.add(behaviour);
        return behaviour;
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
