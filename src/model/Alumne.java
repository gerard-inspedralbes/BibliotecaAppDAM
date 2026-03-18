package model;

public class Alumne extends Usuari {
    private static int MAX_PRESTECS = 2;
    public Alumne(String nom) {
        super(nom);
    }

    @Override
    public int getMaxPrestects() {
        return MAX_PRESTECS;
    }

    @Override
    public String toString() {
        return "Alumne{"+super.toString()+"}";
    }


}
