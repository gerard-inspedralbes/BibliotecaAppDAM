package DAO;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOBibliotecaBBDD implements DAOBiblioteca{

    Connection conn = DBConnection.getInstance().getConnection();

    @Override
    public ArrayList<Llibre> getLlibres() {
        ArrayList<Llibre> llibres = new ArrayList<>();

        String sql = "SELECT id, titol, autor, disponible FROM llibres";

        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titol = rs.getString("titol");
                String autor = rs.getString("autor");
                boolean disponible = rs.getBoolean("disponible");

                // Constructor amb ID (recomanat)
                Llibre llibre = new Llibre(id, titol, autor, disponible);

                llibres.add(llibre);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obtenint llibres", e);
        }

        return llibres;
    }

    @Override
    public void prestarLlibre(Llibre l, int idUsr) throws LlibreNoDisponibleException {

        String checkSql = "SELECT disponible FROM llibres WHERE id = ?";
        String updateSql = "UPDATE llibres SET disponible = false WHERE id = ?";
        String insertSql = "INSERT INTO prestecs (id_usuari, id_llibre) VALUES (?, ?)";

        try {
            PreparedStatement checkPs = conn.prepareStatement(checkSql);
            checkPs.setInt(1, l.getId());
            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                boolean disponible = rs.getBoolean("disponible");

                if (!disponible) {
                    throw new LlibreNoDisponibleException("El llibre no està disponible");
                }

                // Inserir préstec
                PreparedStatement insertPs = conn.prepareStatement(insertSql);
                insertPs.setInt(1, idUsr);
                insertPs.setInt(2, l.getId());
                insertPs.executeUpdate();

                // Actualitzar llibre
                PreparedStatement updatePs = conn.prepareStatement(updateSql);
                updatePs.setInt(1, l.getId());
                updatePs.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en prestar llibre", e);
        }
    }

    @Override
    public ArrayList<Usuari> getUsuaris() {
        ArrayList<Usuari> usuaris = new ArrayList<>();

        String sql = "SELECT id, tipus, nom FROM usuaris";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String tipus = rs.getString("tipus");
                String nom = rs.getString("nom");

                if (tipus.equals("ALUMNE")) {
                    usuaris.add(new Alumne(id, nom));
                } else if (tipus.equals("PROFESSOR")) {
                    usuaris.add(new Professor(id, nom));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obtenint usuaris", e);
        }

        return usuaris;
    }

    @Override
    public boolean retornarPrestec(int idUsr, int idLlib) {

        String deleteSql = "DELETE FROM prestecs WHERE id_usuari = ? AND id_llibre = ?";
        String updateSql = "UPDATE llibres SET disponible = true WHERE id = ?";

        try {
            PreparedStatement deletePs = conn.prepareStatement(deleteSql);
            deletePs.setInt(1, idUsr);
            deletePs.setInt(2, idLlib);

            int rows = deletePs.executeUpdate();

            if (rows > 0) {
                PreparedStatement updatePs = conn.prepareStatement(updateSql);
                updatePs.setInt(1, idLlib);
                updatePs.executeUpdate();
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retornant préstec", e);
        }

        return false;
    }

    @Override
    public ArrayList<Llibre> getPrestecs(int idUsr) {
        ArrayList<Llibre> llibres = new ArrayList<>();

        String sql = """
        SELECT l.id, l.titol, l.autor, l.disponible
        FROM llibres l
        JOIN prestecs p ON l.id = p.id_llibre
        WHERE p.id_usuari = ?
    """;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsr);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titol = rs.getString("titol");
                String autor = rs.getString("autor");
                boolean disponible = rs.getBoolean("disponible");

                llibres.add(new Llibre(id, titol, autor, disponible));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error obtenint préstecs", e);
        }

        return llibres;
    }
}
