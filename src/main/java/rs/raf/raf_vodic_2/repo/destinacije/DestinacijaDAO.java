package rs.raf.raf_vodic_2.repo.destinacije;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DestinacijaDAO {

    private Connection connection;

    public DestinacijaDAO(Connection connection) {
        this.connection = connection;
    }

    public void addDestinacija(Destinacija destinacija) throws SQLException {
        String sql = "INSERT INTO destinacije (naziv, opis) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, destinacija.getNaziv());
            stmt.setString(2, destinacija.getOpis());
            stmt.executeUpdate();
        }
    }

    public Destinacija getDestinacijaById(Long id) throws SQLException {
        String sql = "SELECT * FROM destinacije WHERE id = ?";
        Destinacija destinacija = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    destinacija = new Destinacija();
                    destinacija.setId(rs.getLong("id"));
                    destinacija.setNaziv(rs.getString("naziv"));
                    destinacija.setOpis(rs.getString("opis"));
                }
            }
        }

        return destinacija;
    }

    public void updateDestinacija(Destinacija destinacija) throws SQLException {
        String sql = "UPDATE destinacije SET naziv = ?, opis = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, destinacija.getNaziv());
            stmt.setString(2, destinacija.getOpis());
            stmt.setLong(3, destinacija.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteDestinacija(Long id) throws SQLException {
        String sql = "DELETE FROM destinacije WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public ArrayList<Destinacija> getAllDestinacije() throws SQLException {
        ArrayList<Destinacija> destinacijeLista = new ArrayList<>();
        String sql = "SELECT * FROM destinacije";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            if (rs.next()) {
                Destinacija destinacija = new Destinacija();
                destinacija.setId(rs.getLong("id"));
                destinacija.setNaziv(rs.getString("naziv"));
                destinacija.setOpis(rs.getString("opis"));
                destinacijeLista.add(destinacija);
            }
        }

        return destinacijeLista;
    }

}
