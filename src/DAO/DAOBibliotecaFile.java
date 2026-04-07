package DAO;

import model.Llibre;
import model.LlibreNoDisponibleException;
import model.Prestec;
import model.Usuari;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DAOBibliotecaFile implements DAOBiblioteca{
    private File file = new File("llibres.txt");
    @Override
    public ArrayList<Llibre> getLlibres() {
        ArrayList<Llibre> llibres = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                llibres.add(Llibre.fromCSV(line));
            }
        } catch (IOException e) {
            return llibres;
        }
        return llibres;
    }

    @Override
    public void prestarLlibre(Llibre l, int idUsr) throws LlibreNoDisponibleException {

    }

    @Override
    public ArrayList<Usuari> getUsuaris() {
        return null;
    }

    @Override
    public boolean retornarPrestec(int idUsr, int idLlib) {
        return false;
    }

    @Override
    public ArrayList<Prestec> getPrestecs(int idUsr) {
        return null;
    }
}
