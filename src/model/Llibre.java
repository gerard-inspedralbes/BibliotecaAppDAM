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
        return new Llibre(p[1],p[2],Boolean.parseBoolean(p[3]));
    }
}
