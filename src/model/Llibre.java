package model;

public class Llibre {
    private static int contadorLlibres = 0;
    private int id;
    private String titol;
    private String autor;
    boolean disponible;

    public Llibre(String titol, String autor, boolean disponible) {
        contadorLlibres++;
        this.id = contadorLlibres;
        this.titol = titol;
        this.autor = autor;
        this.disponible = disponible;
    }

    public int getid() {
        return id;
    }

    protected String getTitol() {
        return titol;
    }

    protected String getAutor() {
        return autor;
    }

    public boolean isDisponible() {
        return disponible;
    }

    protected void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Llibre{" +
                "id=" + id +
                ", titol='" + titol + '\'' +
                ", autor='" + autor + '\'' +
                ", disponible=" + disponible +
                '}';
    }
}
