package rs.raf.raf_vodic_2.repo.clanci;

import rs.raf.raf_vodic_2.filters.Global;
import rs.raf.raf_vodic_2.repo.destinacije.Destinacija;
import rs.raf.raf_vodic_2.rest_api.DbHelper2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ClanakDAO extends DbHelper2 implements ClanakRepoInterface {

    @Override
    public Clanak addClanak(Clanak clanak) {
        String sql =
                "INSERT INTO clanci (naslov, tekst, vreme_kreiranja, autor_id, destinacija_id, broj_poseta) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, clanak.getNaslov());
            stmt.setString(2, clanak.getTekst());
//            stmt.setDate(3, clanak.getDatumKreiranja());
            stmt.setDate(3, Global.localDateToDate(LocalDate.now()));
            stmt.setLong(4, clanak.getAutorId());
            stmt.setLong(5, clanak.getDestinacijaId());
            stmt.setInt(6, clanak.getBrojPoseta());
            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();
            if (rs.next())
                clanak.setId(rs.getLong(1));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
            this.closeResultSet(rs);
        }
        return clanak;
    }

    @Override
    public Clanak getClanakById(Long id) {
        Clanak clanak = null;
        String sql = "SELECT * FROM clanci WHERE id = ?";
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                clanak = new Clanak();
                clanak.setId(rs.getLong("id"));
                clanak.setNaslov(rs.getString("naslov"));
                clanak.setTekst(rs.getString("tekst"));
                clanak.setDatumKreiranja(rs.getDate("datum_kreiranja").toLocalDate());
                clanak.setBrojPoseta(rs.getInt("br_poseta"));
                clanak.setAutorId(rs.getLong("autor_id"));
                clanak.setDestinacijaId(rs.getLong("destinacija_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
            this.closeResultSet(rs);
        }

        return clanak;
    }

    @Override
    public Clanak updateClanak(Clanak clanak) {
//        String sql =
//                "UPDATE clanci SET naslov = ?, tekst = ?, vreme_kreiranja = ?, br_poseta = ?, autor_id = ?, destinacija_id = ?";
        String sql =
                "UPDATE clanci SET naslov = ?, tekst = ?, autor_id = ?, destinacija_id = ? WHERE id = ?";

        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, clanak.getNaslov());
            stmt.setString(2, clanak.getTekst());
            stmt.setLong(3, clanak.getAutorId());
            stmt.setLong(4, clanak.getDestinacijaId());
            stmt.setLong(5, clanak.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
        }

        return clanak;
    }

    @Override
    public void deleteClanak(Long id) {
        String sql = "DELETE FROM clanci WHERE id = ?";
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
    public ArrayList<Clanak> getAllClanciUnordered() {
        ArrayList<Clanak> listaClanaka = new ArrayList<>();
//        String sql = "SELECT * FROM clanci";    //  Preventivno, mada moze i null da stoji
        String sql = "SELECT * FROM clanci JOIN destinacija ON destinacija_id ORDER BY vreme_kreiranja DESC LIMIT ?";
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, 10);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Clanak clanak = new Clanak();
                clanak.setId(rs.getLong("id"));
                clanak.setNaslov(rs.getString("naslov"));
                clanak.setTekst(rs.getString("tekst"));
                clanak.setDatumKreiranja(rs.getDate("datum_kreiranja").toLocalDate());
                clanak.setBrojPoseta(rs.getInt("br_poseta"));
                clanak.setAutorId(rs.getLong("autor_id"));
                clanak.setDestinacijaId(rs.getLong("destinacija_id"));
                clanak.setDestinacija(
                        new Destinacija(
                                rs.getLong("destinacije.id"),
                                rs.getString("naziv"),
                                rs.getString("opis")
                        )
                );
                listaClanaka.add(clanak);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
            this.closeResultSet(rs);
        }

        return listaClanaka;
    }

    @Override
    public ArrayList<Clanak> getAllClanci(String numOfElmentsToReturn, String najcitaniji) {
        ArrayList<Clanak> listaClanaka = new ArrayList<>();
//        String sql = "SELECT * FROM clanci";    //  Preventivno, mada moze i null da stoji
        String sql = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = this.newConnection();

            if (numOfElmentsToReturn != null) {
                sql = "SELECT * FROM clanci JOIN destinacija ON destinacija_id ORDER BY vreme_kreiranja DESC LIMIT ?";
                stmt = connection.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(numOfElmentsToReturn));
            } else if (najcitaniji != null) {
                sql = "SELECT * FROM clanci JOIN destinacija ON destinacija_id = destinacije.id ORDER BY vreme_kreiranja > (now() - interval 1 month) ORDER BY br_poseta DESC limit ?";
                stmt = connection.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(najcitaniji));
            } else {
                sql = "SELECT * FROM clanci JOIN destinacije ON destinacija_id = destinacije.id ORDER BY vreme_kreiranja ASC ";
                stmt = connection.prepareStatement(sql);
            }
            rs = stmt.executeQuery();

            while (rs.next()) {
                Clanak clanak = new Clanak();
                clanak.setId(rs.getLong("id"));
                clanak.setNaslov(rs.getString("naslov"));
                clanak.setTekst(rs.getString("tekst"));
                clanak.setDatumKreiranja(rs.getDate("datum_kreiranja").toLocalDate());
                clanak.setBrojPoseta(rs.getInt("br_poseta"));
                clanak.setAutorId(rs.getLong("autor_id"));
                clanak.setDestinacijaId(rs.getLong("destinacija_id"));
                clanak.setDestinacija(
                        new Destinacija(
                                rs.getLong("destinacije.id"),
                                rs.getString("naziv"),
                                rs.getString("opis")
                        )
                );
                listaClanaka.add(clanak);

                /*
                listaClanaka.add(
                        new Clanak(
                                rs.getLong("id"),
                                rs.getString("naslov"),
                                rs.getString("tekst"),
                                rs.getLong("autor_id"),
                                rs.getLong("destinacija_id"),
                                rs.getInt("br_poseta"),
                                rs.getDate("datum_kreiranja").toLocalDate(),
                                new Destinacija(
                                        rs.getLong("destinacije.id"),
                                        rs.getString("naziv"),
                                        rs.getString("opis")
                                )
                        )
                );
                */
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
            this.closeResultSet(rs);
        }
        return listaClanaka;
    }

    @Override
    public void incrementBrPoseta(Long id) {
        String sql = "UPDATE clanci SET br_poseta = br_poseta + 1 WHERE id = ?";
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = this.newConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(stmt);
        }
    }

}
