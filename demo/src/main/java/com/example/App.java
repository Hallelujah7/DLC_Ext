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

        System.out.println("Insert name of file:");
        Scanner in = new Scanner(System.in);
        String filename = in.nextLine();
        System.out.println("Enter name of animal to generate distance data for:");
        String animalName = in.nextLine();
        System.out.println("Behaviour name, body parts it's determined by and the distance parameter separated by commas:");
        String[] behaviour = in.nextLine().split(",");
        in.close();


        // Check format
        if (!filename.endsWith(".csv")) {
            throw new IllegalArgumentException(filename);
        }

        // Get cumulative data output file
        Start(filename, animalName, behaviour);

    }

    /**
     * 
     * @param filename
     * @throws IOException
     */

    public static void Start(String filename, String individual, String[] behaviour) throws IOException {

        Parser.parse(filename);
        System.out.println(Parser.allAnimals.keySet());

        if(Parser.animals.contains(individual)){
            Animal animal = Parser.allAnimals.get(individual);
            animal.getDistances();
            animal.makeBehaviour(behaviour[0], behaviour[1], Double.parseDouble(behaviour[2]));
        }else{
            System.out.println("Animal not in file!");
        }
        

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
