package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import presentation.model.User;

/**
 *
 * @author Martin Frederiksen
 */
public class UserMapper implements IUserMapper {

    private DBConnector connector;

    public UserMapper() {
        connector = new DBConnector();
    }

    @Override
    public int addUser(String username, String email, String password) throws SQLException {
        Connection connection = connector.getConnection();
        String quary = "INSERT INTO users(username, email, password) VALUES(?,?,?);";
        PreparedStatement ps = connection.prepareCall(quary);
        try {
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            connection.setAutoCommit(false);
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            connection.setAutoCommit(true);
            if (ps != null) {
                ps.close();
            }
        }
        return -1;
    }

    @Override
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList();
        String quary = "SELECT * FROM users;";
        Statement stmt = connector.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(quary);

        while (rs.next()) {
            users.add(new User(rs.getString("username"), rs.getString("email"), rs.getDouble("balance")));
        }
        return users;
    }

    @Override
    public User getUser(String username) throws SQLException {
        String quary = "SELECT * FROM users WHERE username = ?;";
        PreparedStatement ps = connector.getConnection().prepareStatement(quary);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        User user = null;
        while (rs.next()) {
            if (username.equalsIgnoreCase(rs.getString("username"))) {
                user = new User(username, rs.getString("email"), rs.getDouble("balance"));
            }
        }
        return user;
    }
    
    @Override
    public boolean validateUser(String username, String password) throws SQLException {
        String quary = "SELECT username, email FROM users WHERE (username = ? OR email = ?)AND password = ?;";
        PreparedStatement ps = connector.getConnection().prepareStatement(quary);
        ps.setString(1, username);
        ps.setString(2, username);
        ps.setString(3, password);
        ResultSet rs = ps.executeQuery();
        boolean valid = false;
        while (rs.next()) {
            if (username.equalsIgnoreCase(rs.getString("username")) || username.equalsIgnoreCase(rs.getString("email"))) {
                valid = true;
            }
        }
        return valid;
    }

    public static void main(String[] args) {
        List<User> users = new ArrayList();
        UserMapper um = new UserMapper();
        try {
            users = um.getUsers();
            for (User u : users) {
                System.out.println(u.getUsername());
            }
            /*um.addUser("Asger", "AsgerErHerIkke@gmail.dk", "1234");
            User user = um.getUser("vikke");
            User user1 = um.getUser("William");
            System.out.println(user.getPassword());
            System.out.println(user1.getPassword());
            System.out.println(user1.getBalance());*/
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
