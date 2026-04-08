import DAO.DAOBiblioteca;
import DAO.DAOBibliotecaBBDD;
import DAO.DAOBibliotecaFile;
import model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class BibliotecaApp {
    private static Biblioteca biblioteca;
    private static Scanner sc = new Scanner(System.in);
    //static DAOBiblioteca daoBiblioteca = new DAOBibliotecaFile();
    static DAOBiblioteca daoBiblioteca = new DAOBibliotecaBBDD();

    public static void main(String[] args) {


        int opcio;

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
//TODO:
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
        int idUsr = Utils.llegirInt(sc,"ID del Usuari: ",1,llibres.size());
        mostrarLlibres();
        int idLlib = Utils.llegirInt(sc,"ID del llibre a prestar: ",1,llibres.size());

        for (Llibre l : llibres) {

            if (l.getId() == idLlib) {
                try {
                    daoBiblioteca.prestarLlibre(l, idUsr);

                } catch (LlibreNoDisponibleException e) {
                    throw new RuntimeException(e);
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