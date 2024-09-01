package rs.raf.raf_vodic_2.repo.aktivnosti;

import java.sql.*;
import java.util.ArrayList;

public class AktivnostDAO {

    private Connection connection;

    public AktivnostDAO(Connection connection) {
        this.connection = connection;
    }

    public void addAktivnost(Aktivnost aktivnost) throws SQLException {
        String sql = "INSERT INTO aktivnosti (naziv) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, aktivnost.getNaziv());
            stmt.executeUpdate();

            //  Postavljamo ID nove aktivnosti
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    aktivnost.setId(generatedKeys.getLong(1));
                }
            }
        }
    }

    public void updateAktivnosti(Aktivnost aktivnost) throws SQLException {
        String sql = "UPDATE aktivnosti SET naziv = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aktivnost.getNaziv());
            stmt.setLong(2, aktivnost.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteAktivnost(Long id) throws SQLException {
        String sql = "DELETE FROM aktivnosti WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public ArrayList<Aktivnost> getAllAktivnosti() throws SQLException {
        ArrayList<Aktivnost> aktivnosti = new ArrayList<>();
        String sql = "SELECT * FROM aktivnosti";
        try (
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                Aktivnost aktivnost = new Aktivnost();
                aktivnost.setId(rs.getLong("id"));
                aktivnost.setNaziv(rs.getString("naziv"));
                aktivnosti.add(aktivnost);
            }
        }

        return aktivnosti;
    }

    public Aktivnost findAktivnostById(Long id) throws SQLException {
        String sql = "SELECT * FROM aktivnosti WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Aktivnost aktivnost = new Aktivnost();
                    aktivnost.setId(rs.getLong("id"));
                    aktivnost.setNaziv(rs.getString("naziv"));
                    return aktivnost;
                }
            }
        }
        return null;
    }

}
