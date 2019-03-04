package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logic.model.RoleEnum;
import logic.model.User;

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
        String quary = "SELECT username, email, balance, role FROM users;";
        Statement stmt = connector.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(quary);

        while (rs.next()) {
            users.add(new User(rs.getString("username"), rs.getString("email"), rs.getDouble("balance"), RoleEnum.valueOf(rs.getString("role"))));
        }
        return users;
    }

    @Override
    public User getUser(String username) throws SQLException {
        String quary = "SELECT username, email, balance, role FROM users WHERE username = ?;";
        PreparedStatement ps = connector.getConnection().prepareStatement(quary);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        User user = null;
        while (rs.next()) {
            if (username.equalsIgnoreCase(rs.getString("username"))) {
                user = new User(username, rs.getString("email"), rs.getDouble("balance"), RoleEnum.valueOf(rs.getString("role")));
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

    @Override
    public int changePassword(String username, String password) throws SQLException {
        Connection connection = connector.getConnection();
        String quary = "UPDATE users SET password = ? WHERE username = ?;";
        PreparedStatement ps = connector.getConnection().prepareStatement(quary);
        try {
            ps.setString(1, password);
            ps.setString(2, username);
            connection.setAutoCommit(false);
            int result = ps.executeUpdate();
            connection.commit();
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

}
