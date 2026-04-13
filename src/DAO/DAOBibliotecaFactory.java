package DAO;

public class DAOBibliotecaFactory {

    public static DAOBiblioteca getDAO(TipusDAO tipus) {

        switch (tipus) {

            case FILE:
                return new DAOBibliotecaFile();

            case BBDD:
                return new DAOBibliotecaBBDD();

            default:
                throw new IllegalArgumentException("Tipus de DAO no suportat");
        }
    }
}