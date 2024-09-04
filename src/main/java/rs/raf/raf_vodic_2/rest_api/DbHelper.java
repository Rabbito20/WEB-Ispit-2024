package rs.raf.raf_vodic_2.rest_api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {

    //  jdbc:mysql://localhost:3306/raf_vodic_schema
    private static final String URL = "jdbc:mysql://" + getHost() + ":" + getPort() + "/" + getDbName();
    private static final String USER = getUsername();
    private static final String PASSWORD = getPassword();



    protected static String getHost() {
        return "localhost";
    }

    private static int getPort() {
        return 3306;
    }

    private static String getDbName() {
        return "raf_vodic_schema";
    }

    private static String getUsername() {
        return "root";
    }

    private static String getPassword() {
        return "root";
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
