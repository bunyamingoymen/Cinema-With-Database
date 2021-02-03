package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private Connection connection;

    public Connection connect() throws SQLException {

        if (this.connection == null || this.connection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
                String host = "localhost";
                String port = "5432";
                String db_name = "Sinema";
                String username = "postgres";
                String password = "1234";
                this.connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + db_name + "", "" + username + "", "" + password + "");

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return this.connection;
    }
}
