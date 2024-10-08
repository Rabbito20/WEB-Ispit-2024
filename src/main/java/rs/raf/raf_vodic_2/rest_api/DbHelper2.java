package rs.raf.raf_vodic_2.rest_api;

import java.sql.*;
import java.util.Optional;

public class DbHelper2 {

    public DbHelper2() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection newConnection() throws SQLException {
        return DriverManager
                .getConnection("jdbc:mysql://" + this.getHost() + ":" + this.getPort() + "/" + this.getDbName(), this.getUsername(), this.getPassword());
    }

    protected void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                Optional.of(connection).get().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                Optional.of(statement).get().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                Optional.of(rs).get().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getHost() {
        return "localhost";
    }

    private int getPort() {
        return 3306;
    }

    private String getDbName() {
        return "raf_vodic_schema";
    }

    private String getUsername() {
        return "root";
    }

    private String getPassword() {
        return "root";
    }
}
