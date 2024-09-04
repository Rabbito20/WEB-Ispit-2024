package rs.raf.raf_vodic_2.repo.aktivnosti;

import rs.raf.raf_vodic_2.rest_api.DbHelper2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AktivnostDAO extends DbHelper2 implements AktivnostRepoInterface {

    @Override
    public Aktivnost addAktivnost(Aktivnost aktivnost) {
        String sql = "INSERT INTO aktivnosti (naziv) VALUES (?)";
        Connection connection = null;
        PreparedStatement stmt = null;
//        ResultSet rs = null;

        try {
            connection = this.newConnection();
//            String[] generatedColumns = {"id"};

//            stmt = connection.prepareStatement(sql, generatedColumns);
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, aktivnost.getNaziv());
            stmt.executeUpdate();

            //  Postavljamo ID nove aktivnosti
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                aktivnost.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
//            this.closeResultSet(rs);
        }

        return aktivnost;
    }

    @Override
    public Aktivnost updateAktivnost(Aktivnost aktivnost) {
        String sql = "UPDATE aktivnosti SET naziv = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, aktivnost.getNaziv());
            stmt.setLong(2, aktivnost.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
        }

        return aktivnost;
    }

    @Override
    public void deleteAktivnost(Long id) {
        String sql = "DELETE FROM aktivnosti WHERE id = ?";
        Connection connection = null;
        PreparedStatement stmt = null;

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

    @Override
    public List<Aktivnost> getAllAktivnosti() {
        ArrayList<Aktivnost> aktivnostiLista = new ArrayList<>();
        String sql = "SELECT * FROM aktivnosti";
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = this.newConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Aktivnost aktivnost = new Aktivnost();
                aktivnost.setId(rs.getLong("id"));
                aktivnost.setNaziv(rs.getString("naziv"));
                aktivnostiLista.add(aktivnost);
            }
        } catch (SQLException e) {
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
            this.closeResultSet(rs);
        }

        return aktivnostiLista;
    }

    @Override
    public Aktivnost getAktivnostById(Long id) {
        String sql = "SELECT * FROM aktivnosti WHERE id = ?";
        Connection connection = null;
        PreparedStatement stmt = null;
        Aktivnost aktivnost = null;
        ResultSet rs = null;
        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);

            rs = stmt.executeQuery();
            if (rs.next()) {
                aktivnost = new Aktivnost();
                aktivnost.setId(rs.getLong("id"));
                aktivnost.setNaziv(rs.getString("naziv"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
            this.closeResultSet(rs);
        }

        return aktivnost;
    }

    @Override
    public Aktivnost getAktivnostByName(String name) {
        String sql = "SELECT * FROM aktivnosti WHERE naziv = ?";
        Aktivnost aktivnost = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            rs = stmt.executeQuery();

            if (rs.next()) {
                aktivnost = new Aktivnost(
                        rs.getLong("id"),
                        rs.getString("naziv")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
            this.closeResultSet(rs);
        }

        return aktivnost;
    }

}
