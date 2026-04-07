package DAO;

import model.Llibre;
import model.LlibreNoDisponibleException;
import model.Prestec;
import model.Usuari;

import java.util.ArrayList;

public interface DAOBiblioteca {

    public ArrayList<Llibre> getLlibres();
    public void prestarLlibre(Llibre l, int idUsr) throws LlibreNoDisponibleException;
    public ArrayList<Usuari> getUsuaris();
    public boolean retornarPrestec(int idUsr, int idLlib);
    public ArrayList<Llibre> getPrestecs(int idUsr);

}
