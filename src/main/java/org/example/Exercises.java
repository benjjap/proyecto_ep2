package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Exercises {

    private String exercise_name;
    private String type;
    private String description;
    private DataExercises data;
    public String file;


    public Exercises(){
        this.exercise_name = null;
        this.type = null;
        this.description = null;
    }

    public Exercises(String file){
        this.file = file;
    }

    public Exercises(String exercise, String type, String description) {
        this.exercise_name = exercise;
        this.type = type;
        this.description = description;

    }

    public String getExercise() {
        return exercise_name;
    }

    public void setExercise(String exercise) {
        this.exercise_name = exercise;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DataExercises getData() {
        return data;
    }

    public void setData(DataExercises data) {
        this.data = data;
    }

    public void addExercise(ArrayList<Exercises> exercisesList){
        Scanner sc = new Scanner(System.in);

        Exercises exercise = new Exercises();
        DataExercises dataExercises = new DataExercises();



        System.out.println("Ingrese el nombre del Ejercicio");
        String name = sc.nextLine();
        exercise.setExercise(name);

        System.out.println("Ingrese el tipo de Ejercicio");
        String type = sc.nextLine();
        exercise.setType(type);

        System.out.println("Ingrese una descripcion del Ejercicio");
        String descrip = sc.nextLine();
        exercise.setDescription(descrip);

        exercise.setData(dataExercises);

        System.out.println("Ingrese las repeticiones");
        int rep = sc.nextInt();
        exercise.data.setRepetitions(rep);

        System.out.println("Ingrese la media de calorias quemadas");
        int cal = sc.nextInt();
        exercise.data.setCaloriesBurn(cal);

        System.out.println("Ingrese el tiempo de ejecucion del Ejercicio");
        int timer = sc.nextInt();
        exercise.data.setTimeExercise(timer);



        exercisesList.add(exercise);
    }

    public void showExercise(ArrayList<Exercises> exercisesList){
        System.out.println("Listado de Ejercicios:\n");

        for(Exercises exercise : exercisesList){
            System.out.println("\nNombre del ejercicio: "+ exercise.getExercise());
            System.out.println("Tipo de ejercicio: "+ exercise.getType());
            System.out.println("Descripcion del ejercicio: "+ exercise.getDescription());
            System.out.println("Repeticiones del ejercicio: "+ exercise.getData().getRepetitions());
            System.out.println("Calorias quemadas al finalizar el ejercicio: "+ exercise.getData().getCaloriesBurn());
            System.out.println("Tiempo del ejercicio:\n "+ exercise.getData().getTimeExercise());

        }
    }

    public void read_exercises(ArrayList<Exercises> exercises_list) {
        File file = new File(this.file);
        try {
            FileReader inputfile = new FileReader(file);
            CSVReader reader = new CSVReader(inputfile);

            String[] nextRecord;

            int i=0;
            while ((nextRecord = reader.readNext()) != null) {

                exercises_list.add(new Exercises(nextRecord[0],nextRecord[1],nextRecord[2]));
                i++;
            }


        }catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

// no
    // escribe con comentarios fllojo conch pa mare
    // pq eri super aweonao pareci mina
    // te gustaria que fuera mina
    // pa darnos besitos
    public static void updateExercise(ArrayList<Exercises> exercisesList){
        Exercises exercises1 = new Exercises();
        DataExercises dataExercises = new DataExercises();
        Scanner sc = new Scanner(System.in);
        exercises1.showExercise(exercisesList);
        System.out.println("Ingrese el nombre del ejercicio que desea modificar");
        String nameMod = sc.nextLine();
        for(Exercises exercises : exercisesList){
            if(exercises.getExercise().equals(nameMod)){
                System.out.println("Ingrese el nuevo tipo de Ejercicio");
                String type = sc.nextLine();
                exercises.setType(type);

                System.out.println("Ingrese una nueva descripion del ejercicio");
                String descrip = sc.nextLine();
                exercises.setDescription(descrip);

                exercises.setData(dataExercises);

                System.out.println("Ingrese las nuevas repeticiones");
                int rep = sc.nextInt();
                exercises.data.setRepetitions(rep);

                System.out.println("Ingrese una nueva media de calorias quemadas");
                int cal = sc.nextInt();
                exercises.data.setCaloriesBurn(cal);

                System.out.println("Ingrese el nuevo tiempo de ejecucion al ejercicio:");
                int timer = sc.nextInt();
                exercises.data.setTimeExercise(timer);
                return;
            }
        }
        System.out.println("El nombre del ejercicio no existe");
    }

    // Funcion para enlazar los datos de los ejercicios

    public static void link_exercises(ArrayList<Exercises> exercises_list, ArrayList<DataExercises> data_exercise_list){
        int i = 0;
        for(Exercises exercise:exercises_list) {
            exercise.data = data_exercise_list.get(i);
            i++;
        }
    }

    public static void deleteExercise(ArrayList<Exercises> exercisesList) {
        Exercises exercises1 = new Exercises();
        Scanner r = new Scanner(System.in);

        System.out.println("Lista de ejercicios actuales: \n");
        exercises1.showExercise(exercisesList);

        System.out.println("Ingrese nombre de ejercicio para eliminar: ");
        String ejercicioEliminar = r.nextLine();

        for (int i = 0; i < exercisesList.size(); i++) {
            Exercises exercise = exercisesList.get(i);
            if (exercise.getExercise().equals(ejercicioEliminar)) {
                exercisesList.remove(i);
                System.out.println("Se eliminÃ³ el ejercicio con nombre "+ejercicioEliminar);
                return;
            }
            
            
            
        }
        System.out.println("No se encontro el ejercicio con nombre: "+ejercicioEliminar);
    }

}


