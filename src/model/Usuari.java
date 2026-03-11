package model;

public class Usuari {
    private static int contadorUsuaris = 0;
    private int id;
    private String nom;

    public Usuari(String nom) {
        contadorUsuaris++;
        this.id = contadorUsuaris;
        this.nom = nom;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Usuari{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }


}
