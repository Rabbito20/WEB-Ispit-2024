package rs.raf.raf_vodic_2.repo.clanci_aktivnosti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClanakAktivnostiDAO {
    private Connection connection;

    public ClanakAktivnostiDAO(Connection connection) {
        this.connection = connection;
    }

    public void addClanakAktivnosti(ClanakAktivnosti clanakAktivnosti) throws SQLException {
        String sql = "INSERT INTO clanak_aktivnosti (clanak_id, aktivnost_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, clanakAktivnosti.getClanakId());
            stmt.setLong(2, clanakAktivnosti.getAktivnostId());
            stmt.executeUpdate();
        }
    }

    public void deleteClanakAktivnosti(Long clankId, Long aktivnostId) throws SQLException {
        String sql = "DELETE FROM clanak_aktivnosti WHERE clanak_id = ? AND aktivnost_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, clankId);
            stmt.setLong(2, aktivnostId);
            stmt.executeUpdate();
        }
    }

    //  Vraca listu aktivnosti clanka
    public ArrayList<ClanakAktivnosti> getAktivnostiByClanakId(Long clanakId) throws SQLException {
        String sql = "SELECT * FROM clanak_aktivnosti WHERE clanak_id = ?";
        ArrayList<ClanakAktivnosti> aktivnostiLista = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, clanakId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ClanakAktivnosti aktivnost = new ClanakAktivnosti();
                    aktivnost.setClanakId(rs.getLong("clanak_id"));
                    aktivnost.setAktivnostId(rs.getLong("aktivnost_id"));
                    aktivnostiLista.add(aktivnost);
                }
            }
        }
        return aktivnostiLista;
    }

    //  Ovo mozda nije dobro ili ce morati da se prepravi??? (Sigurno, tako da // TODO :/)
    //  Svi clanci sa zadatom aktivnoscu
    public ArrayList<ClanakAktivnosti> getClanciByAktivnostId(Long aktivnostId) throws SQLException {
        String sql = "SELECT * FROM clanak_aktivnosti WHERE aktivnosti_id = ?";
        ArrayList<ClanakAktivnosti> clanciLista = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, aktivnostId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ClanakAktivnosti clanak = new ClanakAktivnosti();
                    clanak.setClanakId(rs.getLong("clanak_id"));
                    clanak.setAktivnostId(rs.getLong("aktivnost_id"));
                    clanciLista.add(clanak);
                }
            }
        }
        return clanciLista;
    }

}

