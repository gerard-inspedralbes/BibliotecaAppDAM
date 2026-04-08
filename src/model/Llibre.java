package model;

public class Llibre {

    private int id;
    private String titol;
    private String autor;
    boolean disponible;

    public Llibre(int id, String titol, String autor, boolean disponible) {
        this.id = id;
        this.titol = titol;
        this.autor = autor;
        this.disponible = disponible;
    }

    public int getId() {
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

    public void setDisponible(boolean disponible) {
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

    public String toCSV(){
        return this.id+";"+this.autor+";"+this.titol+";"+ this.disponible;
    }

    public static Llibre fromCSV(String linia){
        String[] p = linia.split(";");

        int id = Integer.parseInt(p[0]);
        String autor = p[1];
        String titol = p[2];
        boolean disponible = Boolean.parseBoolean(p[3]);

        return new Llibre(id, titol, autor, disponible);
    }
}
