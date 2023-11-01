package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Patient {

    private String rut;
    private String name;
    private String last_name;
    private Date birth_date;
    private Physical physical_state;
    public String file_patient;
    public Scanner scanner = new Scanner(System.in);
    private ArrayList<Exercises> exercises_list = new ArrayList<>();

    public Patient(String rut, String name, String last_name, Date birth_date){
        this.rut = rut;
        this.name = name;
        this.last_name = last_name;
        this.birth_date = birth_date;
    }

    public Patient(){
        this.rut = null;
        this.name = null;
        this.last_name = null;
        this.birth_date = null;
    }

    public Patient(String file) {
        this.file_patient = file;
    }


    //Getters and setters

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public Physical getPhysical_state() {
        return physical_state;
    }

    public void setPhysical_state(Physical physical_state) {
        this.physical_state = physical_state;
    }


// Methods

    public void addPatient(ArrayList<Patient> patientList){
        Scanner sc = new Scanner(System.in);
        Patient patient = new Patient();
        Physical physical = new Physical();

        patient.setPhysical_state(physical);

        System.out.println("Ingrese el Rut del Paciente");
        String rut = sc.nextLine();
        patient.setRut(rut);

        System.out.println("Nombre del Paciente");
        String name = sc.nextLine();
        patient.setName(name);

        System.out.println("Apellido del Paciente");
        String lastName = sc.nextLine();
        patient.setLast_name(lastName);

        System.out.println("Fecha de nacimiento del Paciente");
        String date = sc.nextLine();
        try {
            Date birth_date = new SimpleDateFormat("dd/mm/yyyy").parse(date);
            patient.setBirth_date(birth_date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Ingrese la altura del Paciente (en cm):");
        int height = sc.nextInt();
        patient.physical_state.setHeight(height);

        System.out.println("Ingrese el peso del Paciente (en kg):");
        int weight = sc.nextInt();
        patient.physical_state.setWeight(weight);

        patient.physical_state.setBmi(patient.physical_state.calculate_bmi());

        System.out.println("¿El Paciente hace ejercicio? (true/false):");
        boolean exercise = sc.nextBoolean();
        patient.physical_state.setExercise(exercise);

        patientList.add(patient);

    }

    public void read_patient(ArrayList <Patient> patient_list) {
        File file = new File(this.file_patient);
        try {
            FileReader inputfile = new FileReader(file);
            CSVReader reader = new CSVReader(inputfile);

            String[] nextRecord;

            int i=0;
            while ((nextRecord = reader.readNext()) != null) {

                Date birth_date = new SimpleDateFormat("dd/mm/yyyy").parse(nextRecord[3]);
                patient_list.add(new Patient(nextRecord[0],nextRecord[1],nextRecord[2], birth_date));
                i++;
            }


        }catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void showPatient(ArrayList<Patient> patient_list){
        for (int i = 0; i < patient_list.size(); i++){
            System.out.println();
            System.out.printf("Paciente %d: \n",i+1);
            System.out.printf("Nombre: %s \n",patient_list.get(i).getName(), patient_list.get(i).getLast_name());
            System.out.printf("RUT: %s\n",patient_list.get(i).getRut());
            System.out.println("Fecha de nacimiento: "+patient_list.get(i).getBirth_date());
            System.out.printf("Peso: %d\n",patient_list.get(i).getPhysical_state().getWeight());
            System.out.printf("Altura: %d\n",patient_list.get(i).getPhysical_state().getHeight());
            System.out.printf("IMC actual: %f\n",patient_list.get(i).getPhysical_state().getBmi());
            System.out.println();
        }
    }

    public static void updatePatient(ArrayList<Patient> patientList) {
        Patient patient1 = new Patient();
        Physical physical = new Physical();
        Scanner sc = new Scanner(System.in);
        patient1.showPatient(patientList);
        System.out.println("Ingrese el rut del paciente que desea modificar");
        String rutMod = sc.nextLine();
        for (Patient patient : patientList) {
            if (patient.getRut().equals(rutMod)) {
                System.out.println("Ingrese nuevo nombre para el paciente");
                String name = sc.nextLine();
                patient.setName(name);

                System.out.println("Ingrese nuevo apellido del paciente");
                String lastName = sc.nextLine();
                patient.setLast_name(lastName);

                System.out.println("Ingrese la nueva fecha de nacimiento del paciente");
                String date = sc.nextLine();
                try {
                    Date birth_date = new SimpleDateFormat("dd/mm/yyyy").parse(date);
                    patient.setBirth_date(birth_date);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                patient.setPhysical_state(physical);

                System.out.println("Ingrese la nueva altura del paciente (en cm):");
                int height = sc.nextInt();
                patient.physical_state.setHeight(height);

                System.out.println("Ingrese el nuevo peso del paciente (en kg):");
                int weight = sc.nextInt();
                patient.physical_state.setWeight(weight);

                patient.physical_state.setBmi(patient.physical_state.calculate_bmi());

                System.out.println("¿El nuevo paciente hace ejercicio? (true/false):");
                boolean exercise = sc.nextBoolean();
                patient.physical_state.setExercise(exercise);
                return;
            }

        }
        System.out.println("El rut ingresado no existe");
    }

    // Funcion para enlazar los datos personales del paciente con los datos fisicos

    public static void link_patient(ArrayList<Patient> patient_list, ArrayList<Physical> physical_list){
        int i = 0;
        for(Patient patient : patient_list){
            if (patient != null) {
                patient.setPhysical_state(physical_list.get(i));
            }
            i++;
        }
    }

    public static void deletePatient(ArrayList<Patient> patientList){
        Patient patient1 = new Patient();
        Scanner r = new Scanner(System.in);

        System.out.println("Lista de pacientes en el sistema: \n");
        patient1.showPatient(patientList);

        System.out.println("Ingrese el rut del Paciente para eliminar: ");
        String rutEliminar = r.nextLine();

        for (Patient patient : patientList) {
            if (patient.getRut().equals(rutEliminar)) {
                patientList.remove(patient);
                System.out.println("Paciente con rut "+rutEliminar+" eliminado.");
                return;
            }
            
        }
        System.out.println("No se encontro el rut para eliminar: "+rutEliminar);
        
    }

}
