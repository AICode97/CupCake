package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringJoiner;

/**
 *
 * @author Martin Frederiksen && Andreas Vikke
 */
public class DBConnector {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://andreasvikke.dk:3306/cupcake";
    private static final String USER = "transformer";
    private static final String PASSWORD = "f7qGtArm";
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
    
    public static void main(String[] args) {
        //Test connection
        try {
            String sql = "SELECT * FROM users";
            ResultSet rs = getConnection().prepareStatement(sql).executeQuery();
            while (rs.next()) {
                StringJoiner sj = new StringJoiner(" - ");
                sj.add(rs.getString("username")).add(rs.getString("email")).add(rs.getString("password")).add(rs.getString("balance"));
                System.out.println(sj.toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
