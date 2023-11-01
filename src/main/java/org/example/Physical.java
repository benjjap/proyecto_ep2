package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;
import static java.lang.Math.pow;


public class Physical {
    private int height;
    private int weight;
    private double bmi;
    private boolean exercise;
    public String file;

    public Physical() {
        this.height = 0;
        this.weight = 0;
        this.bmi = 0;
        this.exercise = false;

    }

    public Physical(int height, int weight, boolean exercise) {
        this.height = height;
        this.weight = weight;
        this.exercise = exercise;
        this.bmi = calculate_bmi();
    }

    public Physical(String file) {
        this.file = file;
    }

    //Getters and setters

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public boolean getExercise() {
        return exercise;
    }

    public void setExercise(boolean exercise) {
        this.exercise = exercise;
    }


    public double calculate_bmi() {
        return this.weight / pow((this.height), 2);
    }


    public static void agregarPhysical(ArrayList<Physical> physicalList) {
        Scanner scanner = new Scanner(System.in);
        Physical physical = new Physical();

        System.out.println("Ingrese los datos del paciente:");

        System.out.print("Altura (en cm): ");
        int height = scanner.nextInt();
        physical.setHeight(height);

        System.out.print("Peso (en kg): ");
        int weight = scanner.nextInt();
        physical.setWeight(weight);

        System.out.print("¿Hace ejercicio? (true/false): ");
        boolean exercise = scanner.nextBoolean();
        physical.setExercise(exercise);

        physical.setBmi(physical.calculate_bmi());


        physicalList.add(physical);

        scanner.close();
    }

    public void read_physical_data(ArrayList <Physical> physical_list) {
        File file = new File(this.file);
        try {
            FileReader inputfile = new FileReader(file);
            CSVReader reader = new CSVReader(inputfile);

            String[] nextRecord;

            int i = 0;
            while ((nextRecord = reader.readNext()) != null) {
                if(i>0){
                    boolean do_exercise = Boolean.parseBoolean(nextRecord[2]);
                    int weight = Integer.parseInt(nextRecord[0].trim());
                    int height = Integer.parseInt(nextRecord[1].trim());
                    Physical patient_physique = new Physical(weight, height, do_exercise);
                    physical_list.add(patient_physique);
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

    }


}
