package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Biblioteca {
    private ArrayList<Llibre> llibres;
    private ArrayList<Usuari> usuaris;
    private HashMap<Usuari, ArrayList<Prestec>> prestecs;

    public Biblioteca(ArrayList<Llibre> llibres, ArrayList<Usuari> usuaris) {
        this.llibres = llibres;
        this.usuaris = usuaris;
        this.prestecs = new HashMap<>();
    }

    public ArrayList<Usuari> getUsuaris() {
        return usuaris;
    }

    public ArrayList<Llibre> getLlibres() {
        return llibres;
    }

    public boolean prestar(Llibre l, int idUsr) {
        if (!l.isDisponible()) return false;

        for (Usuari u : usuaris) {
            if (u.getId() == idUsr) {
                Prestec p = new Prestec(l, u);
                // si l'usuari no té préstecs encara
                if (!prestecs.containsKey(u)) {
                    prestecs.put(u, new ArrayList<>());
                }
                prestecs.get(u).add(p);

                // marcar llibre com prestat
                l.setDisponible(false);
                return true;
            }

        }
        // no hem trobat l'usuari
        return false;
    }

    public boolean retornar(int idUsr, int idLlib) {

        Usuari usuariTrobat = null;

        for (Usuari u : usuaris) {
            if (u.getId() == idUsr) {
                usuariTrobat = u;
                break;
            }
        }

        if (usuariTrobat == null) {
            return false;
        }

        ArrayList<Prestec> llistaPrestecs = prestecs.get(usuariTrobat);

        if (llistaPrestecs == null || llistaPrestecs.isEmpty()) {
            return false;
        }

        for (Prestec p : llistaPrestecs) {

            if (p.getLlibre().getid() == idLlib) {

                p.getLlibre().setDisponible(true);
                llistaPrestecs.remove(p);

                //llibre retornat correctament
                return true;
            }
        }
        // aquest usuari no te aquest llibre prestat
        return false;
    }

    public Llibre[] getPrestecsUsuari(Usuari u) {
        ArrayList<Prestec> llistaPrestecs = prestecs.get(u);

        if (llistaPrestecs == null || llistaPrestecs.isEmpty()) {
            // Si no té préstecs, retornem un array buit
            return new Llibre[0];
        }

        // Crear array de llibres
        Llibre[] llibresPrestats = new Llibre[llistaPrestecs.size()];

        for (int i = 0; i < llistaPrestecs.size(); i++) {
            llibresPrestats[i] = llistaPrestecs.get(i).getLlibre();
        }

        return llibresPrestats;
    }
}


