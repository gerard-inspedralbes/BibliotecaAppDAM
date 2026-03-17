package model;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BibliotecaTest {

    Biblioteca biblioteca;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        biblioteca = inicialitzarBiblioteca();
    }

    @org.junit.jupiter.api.Test
    void prestar() {
        //prepare
        Llibre l = biblioteca.getLlibres().get(0);
        Usuari usr = biblioteca.getUsuaris().get(0);
        //act
        boolean result = biblioteca.prestar(l,usr.getId());
        //assert
        assertTrue(result);
        assertFalse(l.isDisponible());
        assertEquals(1,biblioteca.getPrestecsUsuari(usr).length);
        assertEquals(l, Arrays.stream(biblioteca.getPrestecsUsuari(usr)).toList().get(0));


    }

    @org.junit.jupiter.api.Test
    void prestarLlibreJaPrestat() {
        //prepare
        Llibre l = biblioteca.getLlibres().get(0);
        Usuari usr1 = biblioteca.getUsuaris().get(0);
        Usuari usr2 = biblioteca.getUsuaris().get(1);
        biblioteca.prestar(l, usr1.getId());
        //act
        boolean result = biblioteca.prestar(l, usr2.getId());
        //assert
        assertFalse(result);
        assertEquals(0,biblioteca.getPrestecsUsuari(usr2).length);

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

        String[] noms = {
                "Anna",
                "Marc",
                "Laia",
                "Joan",
                "Clara"
        };

        // ArrayLists del sistema
        ArrayList<Llibre> llibres = new ArrayList<>();
        ArrayList<Usuari> usuaris = new ArrayList<>();

        // Crear 5 llibres
        for (int i = 0; i < titols.length; i++) {
            Llibre llibre = new Llibre( titols[i], autors[i], true);
            llibres.add(llibre);
        }

        // Crear 5 usuaris
        for (int i = 0; i < noms.length; i++) {
            Usuari usuari = new Usuari( noms[i]);
            usuaris.add(usuari);
        }
        return new Biblioteca(llibres,usuaris);
    }
}