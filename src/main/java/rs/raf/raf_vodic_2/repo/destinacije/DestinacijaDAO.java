package rs.raf.raf_vodic_2.repo.destinacije;

import rs.raf.raf_vodic_2.rest_api.DbHelper2;

import java.sql.*;
import java.util.ArrayList;

public class DestinacijaDAO extends DbHelper2 implements DestinacijaRepoInterfejs {

    @Override
    public Destinacija addDestinacija(Destinacija destinacija) {
        String sql = "INSERT INTO destinacije (naziv, opis) VALUES (?, ?)";
        PreparedStatement stmt = null;
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, destinacija.getNaziv());
            stmt.setString(2, destinacija.getOpis());
            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                destinacija.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry"))
                destinacija.setNaziv(null);
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
            this.closeResultSet(rs);
        }

        return destinacija;
    }

    @Override
    public Destinacija getDestinacijaById(Long id) {
        String sql = "SELECT * FROM destinacije WHERE id = ?";
        Destinacija destinacija = null;
        PreparedStatement stmt = null;
        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
//                destinacija = new Destinacija();
//                destinacija.setId(rs.getLong("id"));
//                destinacija.setNaziv(rs.getString("naziv"));
//                destinacija.setOpis(rs.getString("opis"));

                destinacija = new Destinacija(
                        rs.getLong("id"),
                        rs.getString("naziv"),
                        rs.getString("opis"),
                        new ArrayList<>()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
            this.closeResultSet(rs);
        }

        return destinacija;
    }

    @Override
    public ArrayList<Destinacija> getAllDestinacije() {
        ArrayList<Destinacija> destinacijeLista = new ArrayList<>();
        String sql = "SELECT * FROM destinacije";
        Statement stmt = null;
        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = this.newConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                Destinacija destinacija = new Destinacija();
                destinacija.setId(rs.getLong("id"));
                destinacija.setNaziv(rs.getString("naziv"));
                destinacija.setOpis(rs.getString("opis"));
                destinacijeLista.add(destinacija);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
            this.closeResultSet(rs);
        }

        return destinacijeLista;
    }

    @Override
    public Destinacija updateDestinacija(Destinacija destinacija) {
        String sql = "UPDATE destinacije SET naziv = ?, opis = ? WHERE id = ?";
        PreparedStatement stmt = null;
        Connection connection = null;

        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, destinacija.getNaziv());
            stmt.setString(2, destinacija.getOpis());
            stmt.setLong(3, destinacija.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            if (e.getMessage().contains("Duplicate entry"))
                destinacija.setNaziv(null);
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
        }

        return destinacija;
    }

    public void deleteDestinacija(Long id) {
        String sql = "DELETE FROM destinacije WHERE id = ?";
        PreparedStatement stmt = null;
        Connection connection = null;

        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
        }
    }

}
