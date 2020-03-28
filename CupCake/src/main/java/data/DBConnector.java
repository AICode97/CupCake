package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringJoiner;

/**
 *
 * @author Martin Frederiksen - Andreas Vikke
 */
public class DBConnector {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://andreasvikke.dk:3306/cupcake";
    private static final String USER = "root";
    private static final String PASSWORD = "klon3903202";
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        }
        return connection;
    }
}
