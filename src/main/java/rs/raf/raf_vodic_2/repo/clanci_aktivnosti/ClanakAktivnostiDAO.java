package rs.raf.raf_vodic_2.repo.clanci_aktivnosti;

import rs.raf.raf_vodic_2.repo.aktivnosti.Aktivnost;
import rs.raf.raf_vodic_2.rest_api.DbHelper2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClanakAktivnostiDAO extends DbHelper2 implements ClanakAktivnostiRepoInterface {

    @Override
    public ArrayList<Aktivnost> getAllAktivnostiForClanakId(Long clanakId) {
//        int id = Integer.parseInt(clanakId.toString());
        ArrayList<Aktivnost> listaAktivnosti = new ArrayList<>();
        String sql = "SELECT * FROM clanak_aktivnosti WHERE clanak_id = ?";
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, clanakId);
            rs = stmt.executeQuery();

            List<Long> aktivnostiIds = new ArrayList<>();
            StringBuilder sql2 = new StringBuilder();

            while (rs.next()) {
                Long id = rs.getLong("aktivnost_id");
                aktivnostiIds.add(id);

                sql2.append("?, ");     //  primer "id = (?, ?, ?, ?, ...)"
            }

            //  Uzimamo sve o aktivnostima to znamo na osnovu ID-a
            if (!aktivnostiIds.isEmpty()) {
                sql2 = new StringBuilder(sql2.substring(0, sql2.length() - 2)); //  Zavrsava se sa "?, ", pa moramo da otklonimo to

                stmt = connection.prepareStatement("SELECT * FROM aktivnosti WHERE id IN (" + sql2 + ")");
                for (int i = 0; i < aktivnostiIds.size(); i++) {
                    stmt.setLong(i + 1, aktivnostiIds.get(i));
                }

                rs = stmt.executeQuery();

                while (rs.next()) {
                    listaAktivnosti.add(new Aktivnost(
                            rs.getLong("id"),
                            rs.getString("naziv")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
            this.closeResultSet(rs);
        }

        return listaAktivnosti;
    }

    @Override
    public void addAllConnections(Long clanakId, List<Long> listaIdAktivnosti) {
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO clanak_aktivnosti (clanak_id, aktivnost_id) VALUES (?, ?)";

        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);
            String[] generatedColumns = {"id"};
            for (Long aktivnostId : listaIdAktivnosti) {
                stmt = connection.prepareStatement(sql, generatedColumns);
                stmt.setLong(1, clanakId);
                stmt.setLong(1, aktivnostId);
                stmt.executeQuery();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
        }
    }
}

