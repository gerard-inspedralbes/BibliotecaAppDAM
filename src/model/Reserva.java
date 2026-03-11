package model;

public class Reserva {
    private Usuari user;
    private Llibre llibre;

    public Reserva(Usuari user, Llibre llibre) {
        this.user = user;
        this.llibre = llibre;
    }

    public Usuari getUser() {
        return user;
    }

    public Llibre getLlibre() {
        return llibre;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "user=" + user +
                ", llibre=" + llibre +
                '}';
    }
}

