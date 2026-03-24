package model;

import java.util.List;

public abstract class Usuari implements Comparable<Usuari> {
    private static int contadorUsuaris = 0;
    private int id;
    private String nom;

    public Usuari(String nom) {
        contadorUsuaris++;
        this.id = contadorUsuaris;
        this.nom = nom;
    }

    public abstract int getMaxPrestects();

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", nom='" + nom + '\'';
    }

    @Override
    public int compareTo(Usuari usuari) {
        return this.nom.length()-usuari.nom.length();
        //return this.nom.compareTo(usuari.nom.toString());
    }


}
