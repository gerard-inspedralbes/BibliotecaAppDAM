package model;

public class Professor extends Usuari {
    private static int MAX_PRESTECS = 5;
    public Professor(String nom) {
        super(nom);
    }

    @Override
    public int getMaxPrestects() {
        return MAX_PRESTECS;
    }

    @Override
    public String toString() {
        return "Professor{"+super.toString()+"}";
    }
}
