package rs.raf.raf_vodic_2.repo.komentari;

import rs.raf.raf_vodic_2.filters.Global;
import rs.raf.raf_vodic_2.rest_api.DbHelper2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class KomentarDAO extends DbHelper2 implements KomentarRepoInterface {

    @Override
    public ArrayList<Komentar> getAllKomentariForClanakById(Long clanakId) {
        ArrayList<Komentar> komentariLista = new ArrayList<>();
        String sql = "SELECT * FROM komentari WHERE clanak_id = ?";
        PreparedStatement stmt = null;
        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, clanakId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                komentariLista.add(new Komentar(
                        rs.getLong("id"),
                        rs.getLong("id_autora"),
                        rs.getString("tekst"),
                        rs.getDate("datum_kreiranja").toLocalDate(),
                        rs.getLong("clanak_id")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
            this.closeResultSet(rs);
        }

        return komentariLista;
    }

    @Override
    public Komentar addKomentar(Komentar komentar) {
        String sql =
                "INSERT INTO komentari (id_autora, tekst, datum_kreiranja, clanak_id) VALUES (?, ? ,?, ?)";
        PreparedStatement stmt = null;
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, komentar.getAutorId());
            stmt.setString(2, komentar.getTekst());
//            stmt.setDate(3, komentar.getDatumKreiranja());
            stmt.setDate(3, Global.localDateToDate(LocalDate.now()));
            stmt.setLong(4, komentar.getClanakId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if (rs.next())
                komentar.setId(rs.getLong(1));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
            this.closeResultSet(rs);
        }

        return komentar;
    }

    /*
    public Komentar getKomentarById(Long id) {
        String sql = "SELECT * FROM komentari WHERE id = ?";
        Komentar komentar = null;
        PreparedStatement stmt = null;
        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                komentar = new Komentar();
                komentar.setId(rs.getLong("id"));
                komentar.setAutorId(rs.getLong("id_autora"));
                komentar.setTekst(rs.getString("tekst"));
                komentar.setDatumKreiranja(rs.getDate("datum_kreiranja").toLocalDate());
                komentar.setClanakId(rs.getLong("clanak_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
            this.closeResultSet(rs);
        }

        return komentar;
    }
    */

    @Override
    public void deleteKomentar(Long id) {
        String sql = "DELETE FROM komentari WHERE id = ?";
        PreparedStatement stmt = null;
        Connection connection = null;

        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
        }
    }

}
