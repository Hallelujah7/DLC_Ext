package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Behaviour {

    public  String name;
    public  String bodyPart;
    public  int parameter;
    public  String sniffingLabel = "HeadSniffing"; // Nose2Nose (Head Sniffing) The nose of the mouse under
     public ArrayList<Integer>  frames = new ArrayList<>();                                                // analysis points towards the nose of a
                                                         // conspecific at a distance of less than 0.5 cm or directly
                                                         // touches it.

    /**
     * 
     * @param name
     * @param bodyPart
     * @param parameter
     */
    public Behaviour(String name, String bodyPart, int parameter){
        this.name = name;
        this.bodyPart = bodyPart;
        this.parameter = parameter;

        isBehaviour(name, bodyPart, parameter);
    }
   
    /**
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // TODO implement define animal function and generate data for 1 behaviour
        // System.out.println("Enter name of behaviour to generate data for:");
        // Scanner nameInput = new Scanner(System.in);
        // name = nameInput.nextLine();
        // nameInput.close();
        // isBehaviour(name, bodyPart, parameter);

    }

    public void isBehaviour(String behaviour, String bodyPart, int distance_cutoff) {

        ArrayList<Integer> behaviourFrames = new ArrayList<>();

        if (Parser.parts.contains(bodyPart)) {
            String[] animalList = Parser.animals.toArray(new String[Parser.animals.size()]); 
            
            for (int i = 0; i < animalList.length - 1; i++) {
                Animal animal = Parser.allAnimals.get(animalList[i]);
                ArrayList<ArrayList<Double>> positions = animal.data.get(bodyPart);

                for (int j = i + 1; j < animalList.length; j++) {
                    Animal conspecific = Parser.allAnimals.get(animalList[j]);
                    ArrayList<ArrayList<Double>> locations = conspecific.data.get(bodyPart);

                    // System.out.println(locations.toString());
                    for (int k = 0; k < locations.size(); k++) {

                        ArrayList<Double> animalPosition = positions.get(k);
                        ArrayList<Double> conspecificPosition = locations.get(k);

                        // System.out.println(animalPosition.toString());
                        // System.out.println(conspecificPosition.toString());
                        if (animalPosition.contains((double) -1) || conspecificPosition.contains((double) -1)) {
                            continue;
                        } else {
                            double distance = euclideanDistance(animalPosition.get(0), animalPosition.get(1),
                            conspecificPosition.get(0), conspecificPosition.get(1));
                            //   System.out.println(distance); 
                            if (distance < distance_cutoff) {
                                behaviourFrames.add(k);
                            }
                        }
                    }
                }
            }
        }

        Parser.writeToFile("{Behaviour:" + name + behaviourFrames.toString() + "}", "BehaviorFrames_log.txt");
        frames.addAll(behaviourFrames);
    }

    /**
     * 
     * @param x
     * @param y
     * @param xPrime
     * @param yPrime
     * @return
     */
    private static double euclideanDistance(double x, double y, double xPrime, double yPrime) {

        double distance = Math.sqrt(Math.pow(x - xPrime, 2) + (Math.pow(y - yPrime, 2)));
        return distance;

    }

}
