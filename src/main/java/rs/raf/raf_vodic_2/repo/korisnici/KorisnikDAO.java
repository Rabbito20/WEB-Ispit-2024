package rs.raf.raf_vodic_2.repo.korisnici;

import rs.raf.raf_vodic_2.rest_api.DbHelper2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KorisnikDAO extends DbHelper2 implements KorisnikRepoInterface {

//    private Connection connection;

    /*public KorisnikDAO(Connection connection) {
        this.connection = connection;
    }*/

    @Override
    public Korisnik addKorisnik(Korisnik korisnik) {
        String sql =
                "INSERT INTO korisnici (email, ime, prezime, tip_korisnika, status, password) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, korisnik.getEmail());
            stmt.setString(2, korisnik.getIme());
            stmt.setString(3, korisnik.getPrezime());
            stmt.setString(4, korisnik.getTipKorisnika().toString());
            stmt.setBoolean(5, korisnik.isStatus());
            stmt.setString(6, korisnik.getPassword());
            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            if (rs.next()) korisnik.setId(rs.getLong(1));
//            return true;
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry"))
                korisnik.setEmail(null);
//            return false;
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
            this.closeResultSet(rs);
        }

        return korisnik;
    }

    @Override
    public Korisnik getKorisnikById(Long id) {
        String sql = "SELECT * FROM korisnici WHERE id = ?";
        Korisnik korisnik = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                /*korisnik = new Korisnik(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("tip_korisnika"),
                        rs.getInt("status"),
                        rs.getString("password")
                );*/
                korisnik = mapResultSetToKorisnik(rs);
//                return mapResultSetToKorisnik(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
            this.closeResultSet(rs);
        }

        return korisnik;
    }

    /*
    public Korisnik getKorisnikByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM korisnici WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToKorisnik(rs);
                }
            }
        }

        return null;
    }
    */

    @Override
    public Korisnik getKorisnikByEmail(String email) {
        String sql = "SELECT * FROM korisnici WHERE email = ?";
        Korisnik korisnik = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()) {
                korisnik = new Korisnik();
                korisnik.setId(rs.getLong("id"));
                korisnik.setEmail(rs.getString("email"));
                korisnik.setIme(rs.getString("ime"));
                korisnik.setPrezime(rs.getString("prezime"));
                korisnik.setTipKorisnika(rs.getString("tip_korisnika").toUpperCase());
                korisnik.setStatus(rs.getBoolean("status"));
                korisnik.setPassword(rs.getString("password"));
//                return mapResultSetToKorisnik(rs);
            }

            //  Take password if    ????
            //  if(takePassword) {...}

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
            this.closeResultSet(rs);
        }

        return korisnik;
    }

    @Override
    public Korisnik updateKorisnik(Korisnik korisnik) {
        String sql =
                "UPDATE korisnici SET email = ?, ime = ?, prezime = ?, tip_korisnika = ?, status = ?, lozinka = ?";
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, korisnik.getEmail());
            stmt.setString(2, korisnik.getIme());
            stmt.setString(3, korisnik.getPrezime());
            stmt.setString(4, korisnik.getTipKorisnika().toString().toUpperCase());
            stmt.setBoolean(5, korisnik.isStatus());
            stmt.setString(6, korisnik.getPassword());
//            stmt.setLong(7, korisnik.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry"))
                korisnik.setEmail(null);
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
        }

        return korisnik;
    }

    @Override
    public void deleteKorisnik(Long id) {
        String sql = "DELETE FROM korisnici WHERE id = ?";
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
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
    public ArrayList<Korisnik> getAllKorisnici() {
        ArrayList<Korisnik> korisnici = new ArrayList<>();
        String sql = "SELECT * FROM korisnici";
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                korisnici.add(mapResultSetToKorisnik(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
        }

        return korisnici;
    }

    private Korisnik mapResultSetToKorisnik(ResultSet rs) throws SQLException {
        Korisnik korisnik = new Korisnik();
        korisnik.setId(rs.getLong("id"));
        korisnik.setEmail(rs.getString("email"));
        korisnik.setIme(rs.getString("ime"));
        korisnik.setPrezime(rs.getString("prezime"));
        korisnik.setTipKorisnika(rs.getString("tip_korisnika"));
        korisnik.setStatus(rs.getBoolean("status"));
        korisnik.setPassword(rs.getString("password"));
        return korisnik;
    }

}
