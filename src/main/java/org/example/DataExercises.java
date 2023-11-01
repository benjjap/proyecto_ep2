package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class DataExercises {
    private int repetitions;
    private int caloriesBurn;
    private int exercise_time;
    public String file;

    public DataExercises(){
        this.repetitions = 0;
        this.caloriesBurn = 0;
        this.exercise_time = 0;
    }

    public DataExercises(String file){
        this.file = file;
    }

    public DataExercises(int repetitions, int caloriesBurn, int exercise_time) {
        this.repetitions = repetitions;
        this.caloriesBurn = caloriesBurn;
        this.exercise_time = exercise_time;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getCaloriesBurn() {
        return caloriesBurn;
    }

    public void setCaloriesBurn(int caloriesBurn) {
        this.caloriesBurn = caloriesBurn;
    }

    public int getTimeExercise() {
        return exercise_time;
    }

    public void setTimeExercise(int timeExercise) {
        this.exercise_time = timeExercise;
    }


    public void addDataExercise(ArrayList<DataExercises> dataExercisesList) {
        Scanner sc = new Scanner(System.in);

        DataExercises dataExercise = new DataExercises();

        System.out.println("Ingrese los datos del nuevo Ejercicio");

        System.out.println("Ingrese las repeticiones");
        int rep = sc.nextInt();
        dataExercise.setRepetitions(rep);

        System.out.println("Ingrese la media de calorias quemadas");
        int cal = sc.nextInt();
        dataExercise.setCaloriesBurn(cal);

        System.out.println("Ingrese una descripcion del Ejercicio");
        int timer = sc.nextInt();
        dataExercise.setTimeExercise(timer);

        dataExercisesList.add(dataExercise);

    }


    public void read_exercises_data(ArrayList <DataExercises> data_exercises_list) {
        File file = new File(this.file);
        try {
            FileReader inputfile = new FileReader(file);
            CSVReader reader = new CSVReader(inputfile);

            String[] nextRecord;

            int i = 0;
            while ((nextRecord = reader.readNext()) != null) {
                if(i>0){
                    int reps = Integer.valueOf(nextRecord[0]);
                    int kcal = Integer.valueOf(nextRecord[1]);
                    int time = Integer.valueOf(nextRecord[2]);
                    DataExercises exercises_data = new DataExercises(reps, kcal, time);
                    data_exercises_list.add(exercises_data);
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

