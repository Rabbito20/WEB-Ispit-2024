package rs.raf.raf_vodic_2.rest_api;

import rs.raf.raf_vodic_2.repo.clanci.Clanak;
import rs.raf.raf_vodic_2.repo.clanci.ClanakDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/a1")
public class ClanaciResource {

    private ClanakDAO clanakDAO = null;

    public ClanaciResource() {
        try {
            Connection connection = DbHelper.getConnection();
            clanakDAO = new ClanakDAO(connection);
        } catch (SQLException e) {
            System.err.println("ClanciResource() -> Connection problem");
            e.printStackTrace();
        }
    }

    //    clanakDAO = new ClanakDAO(connection);

    @GET
    @Produces("text/plain")
    public ArrayList<Clanak> getAllclanci() throws SQLException {
        return clanakDAO.getAllClanci();
    }
}
