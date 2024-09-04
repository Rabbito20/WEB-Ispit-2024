package rs.raf.raf_vodic_2.repo.korisnici;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KorisnikDAO {

    private Connection connection;

    public KorisnikDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean createKorisnik(Korisnik korisnik) throws SQLException {
        String sql =
                "INSERT INTO korisnici (email, ime, prezime, tip_korisnika, status, password) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, korisnik.getEmail());
            stmt.setString(2, korisnik.getIme());
            stmt.setString(3, korisnik.getPrezime());
            stmt.setString(4, korisnik.getTipKorisnika().toString());
            stmt.setBoolean(5, korisnik.isStatus());
            stmt.setString(6, korisnik.getPassword());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Korisnik findKorisnikById(Long id) throws SQLException {
        String sql = "SELECT * FROM korisnici WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToKorisnik(rs);
                }
            }
        }

        return null;
    }

    public Korisnik findKorisnikByEmail(String email) throws SQLException {
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

    public void updateKorisnik(Korisnik korisnik) throws SQLException {
        String sql =
                "UPDATE korisnici SET email = ?, ime = ?, prezime = ?, tip_korisnika = ?, status = ?, lozinka = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, korisnik.getEmail());
            stmt.setString(2, korisnik.getIme());
            stmt.setString(3, korisnik.getPrezime());
            stmt.setString(4, korisnik.getTipKorisnika().toString());
            stmt.setBoolean(5, korisnik.isStatus());
            stmt.setString(6, korisnik.getPassword());
            stmt.setLong(7, korisnik.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteKorisnik(Long id) throws SQLException {
        String sql = "DELETE FROM korisnici WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public ArrayList<Korisnik> findAllKorisnici() throws SQLException {
        ArrayList<Korisnik> korisnici = new ArrayList<>();
        String sql = "SELECT * FROM korisnici";
        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                korisnici.add(mapResultSetToKorisnik(rs));
            }
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
