package DAO;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DAOBibliotecaFile implements DAOBiblioteca{
    private File f_llibres = new File("llibres.txt");
    private File f_users = new File("usuaris.txt");
    private File f_prestecs = new File("prestecs.txt");
    @Override
    public ArrayList<Llibre> getLlibres() {
        ArrayList<Llibre> llibres = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f_llibres))) {
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

        ArrayList<Llibre> llibres = getLlibres();
        ArrayList<Prestec> prestecs = getAllPrestecs();

        // Buscar llibre
        Llibre llibreTrobat = null;
        for (Llibre llibre : llibres) {
            if (llibre.getId() == l.getId()) {
                llibreTrobat = llibre;
                break;
            }
        }

        if (llibreTrobat == null || !llibreTrobat.isDisponible()) {
            throw new LlibreNoDisponibleException("Llibre no trobat o no disponible");
        }

        // Comprovar límit usuari
        Usuari u = getUsuariById(idUsr);
        int prestecsUsuari = 0;

        for (Prestec p : prestecs) {
            if (p.getUsuari().getId() == idUsr) prestecsUsuari++;
        }

        if (prestecsUsuari >= u.getMaxPrestects()) {
            throw new LlibreNoDisponibleException("L'usuari ha excedit el limit de prèstecs"); // o millor una altra excepció
        }

        // Fer préstec
        llibreTrobat.setDisponible(false);
        prestecs.add(new Prestec( llibreTrobat,u));

        guardarLlibres(llibres);
        guardarPrestecs(prestecs);
    }

    @Override
    public ArrayList<Usuari> getUsuaris() {
        ArrayList<Usuari> usuaris = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(f_users))) {
            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(";");

                int id = Integer.parseInt(parts[0]);
                String tipus = parts[1];
                String nom = parts[2];

                if (tipus.equals("ALUMNE")) {
                    usuaris.add(new Alumne(id, nom));
                } else if (tipus.equals("PROFESSOR")) {
                    usuaris.add(new Professor(id, nom));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error llegint usuaris", e);
        }

        return usuaris;
    }
    @Override
    public boolean retornarPrestec(int idUsr, int idLlib) {
        return false;
    }

    @Override
    public ArrayList<Llibre> getPrestecs(int idUsr) {
        ArrayList<Llibre> result = new ArrayList<>();

        for (Prestec p : getAllPrestecs()) {
            if (p.getUsuari().getId() == idUsr) {
                result.add(p.getLlibre());
            }
        }

        return result;
    }

    private Usuari getUsuariById(int id) {
        for (Usuari u : getUsuaris()) {
            if (u.getId() == id) return u;
        }
        return null;
    }

    private Llibre getLlibreById(int id) {
        for (Llibre l : getLlibres()) {
            if (l.getId() == id) return l;
        }
        return null;
    }

    private void guardarPrestecs(List<Prestec> prestecs) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f_prestecs))) {
            for (Prestec p : prestecs) {
                bw.write(p.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Prestec> getAllPrestecs() {
        ArrayList<Prestec> prestecs = new ArrayList<>();

        ArrayList<Llibre> llibres = getLlibres();
        ArrayList<Usuari> usuaris = getUsuaris();

        try (BufferedReader br = new BufferedReader(new FileReader(f_prestecs))) {
            String line;

            while ((line = br.readLine()) != null) {
                prestecs.add(prestecFromCSV(line, llibres, usuaris));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return prestecs;
    }

    private void guardarLlibres(List<Llibre> llibres) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f_llibres))) {
            for (Llibre l : llibres) {
                bw.write(l.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error guardant llibres", e);
        }
    }

    private Prestec prestecFromCSV(String linia, List<Llibre> llibres, List<Usuari> usuaris) {
        String[] parts = linia.split(";");

        int idUsuari = Integer.parseInt(parts[0]);
        int idLlibre = Integer.parseInt(parts[1]);

        Llibre l = null;
        Usuari u = null;

        for (Llibre llibre : llibres) {
            if (llibre.getId() == idLlibre) {
                l = llibre;
                break;
            }
        }

        for (Usuari usuari : usuaris) {
            if (usuari.getId() == idUsuari) {
                u = usuari;
                break;
            }
        }

        return new Prestec(l, u);
    }

    private int getNextLlibreId() {
        int max = 0;

        for (Llibre l : getLlibres()) {
            if (l.getId() > max) {
                max = l.getId();
            }
        }

        return max + 1;
    }
}
