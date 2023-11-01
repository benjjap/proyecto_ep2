package org.example;

import com.opencsv.exceptions.CsvValidationException;
import javax.lang.model.type.ArrayType;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import org.example.NewJFrame;

public class Main {

    public static void menuAdd(ArrayList<Patient> patient_list, ArrayList<Exercises> exercisesList){
        Scanner sc = new Scanner(System.in);
        Patient patient = new Patient();
        Exercises exercises1 = new Exercises();
        int num;

        do{
            System.out.println("0- Salir\n1- Agregar Persona\n2- Agregar Ejercicio\n");
            System.out.println("Ingrese opcion:");
            num = sc.nextInt();

            switch(num){
                case 0:{
                    System.out.println("Saliendo del menu agregar");
                    break;
                }
                case 1:{
                    patient.addPatient(patient_list);
                    break;
                }
                case 2: {
                    exercises1.addExercise(exercisesList);
                    break;
                }
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        }while(num != 0);
    }
    public static void menuDelete(ArrayList<Patient> patient_list, ArrayList<Exercises> exercisesList){
        Scanner sc = new Scanner(System.in);

        Patient patient = new Patient();
        Exercises exercises1 = new Exercises();

        int num;

        do{
            System.out.println("0- Salir\n1- Eliminar Personas\n2- Eliminar Ejercicios\n");
            System.out.println("Ingrese opcion:");
            num = sc.nextInt();

            switch(num){
                case 0:{
                    System.out.println("Saliendo del menu Eliminar");
                    break;
                }
                case 1:{
                    patient.deletePatient(patient_list);
                    break;
                }
                case 2: {
                    exercises1.deleteExercise(exercisesList);
                    break;
                }
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        }while(num != 0);
    }

    public static void menuShow(ArrayList<Patient> patient_list, ArrayList<Exercises> exercisesList){
        Scanner sc = new Scanner(System.in);

        Patient patient = new Patient();
        Exercises exercises1 = new Exercises();

        int num;

        do{
            System.out.println("0- Salir\n1- Mostrar Personas\n2- Mostrar Ejercicios\n");
            System.out.println("Ingrese opcion:");
            num = sc.nextInt();

            switch(num){
                case 0:{
                    System.out.println("Saliendo del menu Mostrar");
                    break;
                }
                case 1:{
                    patient.showPatient(patient_list);
                    break;
                }
                case 2: {
                    exercises1.showExercise(exercisesList);
                    break;
                }
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        }while(num != 0);
    }

    public static void menuUpdate(ArrayList<Patient> patientList, ArrayList<Exercises> exercisesList){
        Scanner sc = new Scanner(System.in);

        Patient patient = new Patient();
        Exercises exercises1 = new Exercises();

        int num;

        do{
            System.out.println("0- Salir\n1- Actualizar paciente\n2- Actualizar ejercicio\n");
            System.out.println("Ingrese opcion:");
            num = sc.nextInt();

            switch(num){
                case 0:{
                    System.out.println("Saliendo del menu Actualizar");
                    break;
                }
                case 1:{
                    patient.updatePatient(patientList);
                    break;
                }
                case 2: {
                    exercises1.updateExercise(exercisesList);
                    break;
                }
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        }while(num != 0);
    }

    //Variables globales para leer datos desde CSV
    public static Exercises exercises;
    public static Patient patient;
    public static Physical physical;
    public static DataExercises exercises_data;

    public static void main(String[] args) {
        
        NewJFrame in = new NewJFrame();
        in.setVisible(true);

        // Definimos los Arraylist de cada clase

        ArrayList<Patient> patient_list = new ArrayList<>();
        ArrayList<Physical> physicalList = new ArrayList<>();
        ArrayList<Exercises> exercisesList = new ArrayList<>();
        ArrayList<DataExercises> dataExercisesList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Carga de datos desde excel

        patient = new Patient("src\\main\\java\\org\\example\\test\\patient.csv");
        patient.read_patient(patient_list);
        physical = new Physical("src\\main\\java\\org\\example\\test\\patient_physical_data.csv");
        physical.read_physical_data(physicalList);
        exercises = new Exercises("src\\main\\java\\org\\example\\test\\exercises.csv");
        exercises.read_exercises(exercisesList);
        exercises_data = new DataExercises("src\\main\\java\\org\\example\\test\\data_exercises.csv");
        exercises_data.read_exercises_data(dataExercisesList);

        // Enlace de datos

        patient.link_patient(patient_list,physicalList);
        exercises.link_exercises(exercisesList,dataExercisesList);

        System.out.println("Datos desde Excel cargados correctamente!\n");

        int option;

        // Inicio Menu

        while (true){
            System.out.println("Bienvenid@, que accion desear realizar?\n");
            System.out.print("1- Agregar datos\n2- Eliminar datos\n3- Mostrar datos\n4- Actualizar Datos\n0- Salir\n");
            System.out.print("\nIngrese una opcion: ");
            option = scanner.nextInt();
            switch(option) {
                case 0:{
                    return;
                }
                case 1:{
                    menuAdd(patient_list, exercisesList);
                    break;
                }
                case 2: {
                    menuDelete(patient_list, exercisesList);
                    break;
                }
                case 3:{
                    menuShow(patient_list, exercisesList);
                    break;
                }
                case 4:{
                    menuUpdate(patient_list, exercisesList);
                    break;
                }
                default:
                    System.out.println("Por favor, ingrese una opcion valida");
            }
        }
    }
}