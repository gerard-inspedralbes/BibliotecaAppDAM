package model;

public class Prestec {
    private static int contadorPrestecs = 0;
    private int id;
    private Llibre llibre;
    private Usuari usuari;


    protected Prestec(Llibre llibre, Usuari usuari) {
        contadorPrestecs++;
        this.id = contadorPrestecs;
        this.llibre = llibre;
        this.usuari = usuari;
    }


    protected Llibre getLlibre() {
        return llibre;
    }

    protected Usuari getUsuari() {
        return usuari;
    }

}
