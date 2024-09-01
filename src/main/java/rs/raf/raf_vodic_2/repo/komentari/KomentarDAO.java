package rs.raf.raf_vodic_2.repo.komentari;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KomentarDAO {
    private Connection connection;

    public KomentarDAO(Connection connection) {
        this.connection = connection;
    }

    public void addKomentar(Komentar komentar) throws SQLException {
        String sql =
                "INSERT INTO komentari (id_autora, tekst, datum_kreiranja, clanak_id) VALUES (?, ? ,?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, komentar.getAutorId());
            stmt.setString(2, komentar.getTekst());
            stmt.setString(3, komentar.getDatumKreiranja());
            stmt.setLong(4, komentar.getClanakId());
            stmt.executeUpdate();
        }
    }

    public Komentar getKomentarById(Long id) throws SQLException {
        String sql = "SELECT * FROM komentari WHERE id = ?";
        Komentar komentar = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    komentar = new Komentar();
                    komentar.setId(rs.getLong("id"));
                    komentar.setAutorId(rs.getLong("id_autora"));
                    komentar.setTekst(rs.getString("tekst"));
                    komentar.setDatumKreiranja(rs.getString("datum_kreiranja"));
                    komentar.setClanakId(rs.getLong("clanak_id"));
                }
            }
        }

        return komentar;
    }

    //  Mozda ne treba da se updateuje bilo kakav id???
    public void updateKomentar(Komentar komentar) throws SQLException {
        String sql = "UPDATE komentari SET id_autora = ?, tekst = ?, datum_kreiranja = ?, clanak_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, komentar.getAutorId());
            stmt.setString(2, komentar.getTekst());
            stmt.setString(3, komentar.getDatumKreiranja());
            stmt.setLong(4, komentar.getClanakId());
            stmt.executeUpdate();
        }
    }

    public void deleteKomentar(Long id) throws SQLException {
        String sql = "DELETE FROM komentari WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public ArrayList<Komentar> getAllKomentari() throws SQLException {
        ArrayList<Komentar> komentariList = new ArrayList<>();
        String sql = "";

        try (
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            if (rs.next()) {
                Komentar komentar = new Komentar();
                komentar.setId(rs.getLong("id"));
                komentar.setAutorId(rs.getLong("id_autora"));
                komentar.setTekst(rs.getString("tekst"));
                komentar.setDatumKreiranja(rs.getString("datum_kreiranja"));
                komentar.setClanakId(rs.getLong("clanak_id"));

                komentariList.add(komentar);
            }
        }

        return komentariList;
    }

}
