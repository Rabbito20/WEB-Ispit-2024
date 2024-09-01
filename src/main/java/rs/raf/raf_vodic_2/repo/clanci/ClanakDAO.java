package rs.raf.raf_vodic_2.repo.clanci;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClanakDAO {
    private Connection connection;

    public ClanakDAO(Connection connection) {
        this.connection = connection;
    }

    public void addClanak(Clanak clanak) throws SQLException {
        String sql =
                "INSERT INTO clanci (naslov, tekst, datum_kreiranja, autor_id, destinacija_id, broj_poseta) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, clanak.getNaslov());
            stmt.setString(2, clanak.getTekst());
            stmt.setString(3, clanak.getDatumKreiranja());
            stmt.setLong(4, clanak.getAutorId());
            stmt.setLong(5, clanak.getDestinacijaId());
            stmt.setInt(6, clanak.getBrojPoseta());
            stmt.executeUpdate();
        }
    }

    public Clanak getClanakById(Long id) throws SQLException {
        Clanak clanak = null;
        String sql = "SELECT * FROM clanci WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    clanak = new Clanak();
                    clanak.setId(rs.getLong("id"));
                    clanak.setNaslov(rs.getString("naslov"));
                    clanak.setTekst(rs.getString("tekst"));
                    clanak.setDatumKreiranja(rs.getString("datum_kreiranja"));
                    clanak.setBrojPoseta(rs.getInt("br_poseta"));
                    clanak.setAutorId(rs.getLong("autor_id"));
                    clanak.setDestinacijaId(rs.getLong("destinacija_id"));
                }
            }
        }

        return clanak;
    }

    public void updateClanak(Clanak clanak) throws SQLException {
        String sql =
                "UPDATE clanci SET naslov = ?, tekst = ?, vreme_kreiranja = ?, br_poseta = ?, autor_id = ?, destinacija_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, clanak.getNaslov());
            stmt.setString(2, clanak.getTekst());
            stmt.setString(3, clanak.getDatumKreiranja());
            stmt.setInt(4, clanak.getBrojPoseta());
            stmt.setLong(5, clanak.getAutorId());
            stmt.setLong(6, clanak.getDestinacijaId());
            stmt.setLong(7, clanak.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteClanak(Long id) throws SQLException {
        String sql = "DELETE FROM clanci WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public ArrayList<Clanak> getAllClanci() throws SQLException {
        ArrayList<Clanak> clanci = new ArrayList<>();
        String sql = "SELECT * FROM clanci";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            if (rs.next()) {
                Clanak clanak = new Clanak();
                clanak.setId(rs.getLong("id"));
                clanak.setNaslov(rs.getString("naslov"));
                clanak.setTekst(rs.getString("tekst"));
                clanak.setDatumKreiranja(rs.getString("datum_kreiranja"));
                clanak.setBrojPoseta(rs.getInt("br_poseta"));
                clanak.setAutorId(rs.getLong("autor_id"));
                clanak.setDestinacijaId(rs.getLong("destinacija_id"));

                clanci.add(clanak);
            }
        }

        return clanci;
    }

}
