package model;

public class Prestec {
    private static int contadorPrestecs = 0;
    private int id;
    private Llibre llibre;
    private Usuari usuari;


    public Prestec(Llibre llibre, Usuari usuari) {
        contadorPrestecs++;
        this.id = contadorPrestecs;
        this.llibre = llibre;
        this.usuari = usuari;
    }


    public String toCSV() {
        return this.usuari.getId() + ";" + this.llibre.getId();
    }



    public Llibre getLlibre() {
        return llibre;
    }

    public Usuari getUsuari() {
        return usuari;
    }

}
