import model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class BibliotecaApp {
    private static Biblioteca biblioteca;
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        biblioteca = inicialitzarBiblioteca();

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
        //TODO:mostrar tots els prestecs
        System.out.println("TODO:mostrar tots els prestecs");
    }

    private static void mostrarLlibres() {
        for (Llibre l : biblioteca.getLlibres()) {
            System.out.println(l);
        }
    }

    private static void mostrarUsuaris(boolean ambPrestects) {
        ArrayList<Usuari> users = biblioteca.getUsuaris();
        Collections.sort(users);
        for (Usuari u : users) {
            System.out.println(u);
            if (ambPrestects){
                for (Llibre l : biblioteca.getPrestecsUsuari(u)){
                    System.out.println(l);
                }
            }
        }
    }

    public static void prestarLlibre() {
        ArrayList<Usuari> usuaris = biblioteca.getUsuaris();
        ArrayList<Llibre> llibres = biblioteca.getLlibres();
        mostrarUsuaris(false);
        int idUsr = Utils.llegirInt(sc,"ID del Usuari: ",1,llibres.size());
        mostrarLlibres();
        int idLlib = Utils.llegirInt(sc,"ID del llibre a prestar: ",1,llibres.size());

        for (Llibre l : llibres) {

            if (l.getid() == idLlib) {

                if (l.isDisponible()) {
                    if (biblioteca.prestar(l,idUsr)) {
                        System.out.println("Llibre prestat correctament");
                    }else{
                        System.out.println("Error en ele procès");
                    }
                } else {
                    System.out.println("El llibre ja està prestat");
                }

                return;
            }
        }
    }

    public static void retornarLlibre() {

        ArrayList<Usuari> usuaris = biblioteca.getUsuaris();

        mostrarUsuaris(true);

        int idUsr = Utils.llegirInt(sc, "ID del Usuari: ", 1, usuaris.size());

        int idLlib = Utils.llegirInt(sc, "ID del llibre a retornar: ", 1, biblioteca.getLlibres().size());

        if (biblioteca.retornar(idUsr, idLlib)){
            System.out.println("Llibre retornat correctament");
        }else{
            System.out.println("Erorr en el procès");
        }

    }


    private static Biblioteca inicialitzarBiblioteca() {
        // Arrays amb dades inicials
        String[] titols = {
                "1984",
                "El Quijote",
                "Clean Code",
                "Harry Potter",
                "El Hobbit"
        };
        String[] autors = {
                "George Orwell",
                "Miguel de Cervantes",
                "Robert C. Martin",
                "J.K. Rowling",
                "J.R.R. Tolkien"
        };

        String[] alumnes = {
                "Anna",
                "Marc",
                "Laia",
                "Joan",
                "Clara"
        };

        String[] professors = {
                "Carlos",
                "Angela",
                "Gerard"
        };

        // ArrayLists del sistema
        ArrayList<Llibre> llibres = new ArrayList<>();
        ArrayList<Usuari> usuaris = new ArrayList<>();

        // Crear 5 llibres
        for (int i = 0; i < titols.length; i++) {
            Llibre llibre = new Llibre( titols[i], autors[i], true);
            llibres.add(llibre);
        }

        // Crear 5 alumnes
        for (int i = 0; i < alumnes.length; i++) {
            Usuari usuari = new Alumne( alumnes[i]);
            usuaris.add(usuari);
        }

        //crear 3 professors
        for (int i = 0; i < professors.length; i++) {
            Usuari usuari = new Professor( professors[i]);
            usuaris.add(usuari);
        }
        return new Biblioteca(llibres,usuaris);
    }
    
    

}