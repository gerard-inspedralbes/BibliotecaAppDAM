import DAO.*;
import model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class BibliotecaApp {
    private static Biblioteca biblioteca;
    private static Scanner sc = new Scanner(System.in);
    static DAOBiblioteca daoBiblioteca;

    public static void main(String[] args) {

        int opcio;

        System.out.println("\n--- BIBLIOTECA INICI---");
        System.out.println("1. BBDD");
        System.out.println("2. Fitxers");

        opcio = Utils.llegirInt(sc,"Opció: ",1,2);
        if(opcio == 1) {
            daoBiblioteca = DAOBibliotecaFactory.getDAO(TipusDAO.BBDD);
        }else {
            daoBiblioteca = DAOBibliotecaFactory.getDAO(TipusDAO.FILE);
        }

        do {

            System.out.println("\n--- BIBLIOTECA ---");
            System.out.println("1. Mostrar llibres");
            System.out.println("2. Prestar llibre");
            System.out.println("3. Mostrar usuaris");
            System.out.println("4. Retornar préstec");
            System.out.println("5. Mostrar préstecs");
            System.out.println("0. Sortir");

            opcio = Utils.llegirInt(sc,"Opció: ",0,5);

            switch (opcio) {

                case 1:
                    mostrarLlibres();
                    break;

                case 2:
                    prestarLlibre();
                    break;

                case 3:
                    mostrarUsuaris(true);
                    break;

                case 4:
                    retornarLlibre();
                    break;

                case 5:
                    mostrarPrestecs();
                    break;

                case 0:
                    System.out.println("Adeu!");
                    break;
            }

        } while (opcio != 0);

    }

    private static void mostrarPrestecs() {
        System.out.println("\u001B[31m" +
                "Aquesta funcionalitat està pendent d'implementació.\n" + "\u001B[0m" +
                "De moment si vols veure els préstecs pots llistar tots els usuaris (opció 3 del menú)\n" +
                "Sota de cada usuari apareixen els llibres que te prestats");
    }

    private static void mostrarLlibres() {
        for (Llibre l : daoBiblioteca.getLlibres()) {
            System.out.println(l);
        }
    }

    private static void mostrarUsuaris(boolean ambPrestects) {
        ArrayList<Usuari> users = daoBiblioteca.getUsuaris();
        Collections.sort(users);
        for (Usuari u : users) {
            System.out.println(u);
            if (ambPrestects){
                for (Llibre l : daoBiblioteca.getPrestecs(u.getId())){
                    System.out.println(l);
                }
            }
        }
    }

    public static void prestarLlibre() {
        ArrayList<Usuari> usuaris = daoBiblioteca.getUsuaris();
        ArrayList<Llibre> llibres = daoBiblioteca.getLlibres();
        mostrarUsuaris(false);
        int idUsr = Utils.llegirInt(sc,"ID del Usuari: ",1,usuaris.size());
        mostrarLlibres();
        int idLlib = Utils.llegirInt(sc,"ID del llibre a prestar: ",1,llibres.size());

        for (Llibre l : llibres) {

            if (l.getId() == idLlib) {
                try {
                    daoBiblioteca.prestarLlibre(l, idUsr);

                } catch (LlibreNoDisponibleException e) {
                    System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
                }
            }
        }
    }

    public static void retornarLlibre() {

        ArrayList<Usuari> usuaris = daoBiblioteca.getUsuaris();

        mostrarUsuaris(true);

        int idUsr = Utils.llegirInt(sc, "ID del Usuari: ", 1, usuaris.size());

        int idLlib = Utils.llegirInt(sc, "ID del llibre a retornar: ", 1, daoBiblioteca.getLlibres().size());

        if (daoBiblioteca.retornarPrestec(idUsr, idLlib)){
            System.out.println("Llibre retornat correctament");
        }else{
            System.out.println("Erorr en el procès");
        }

    }

}