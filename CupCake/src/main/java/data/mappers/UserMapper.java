package data.mappers;

import data.DatabaseConnector;
import data.interfaces.IUserMapper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import logic.model.enums.RoleEnum;
import logic.model.User;

/**
 *
 * @author Martin Frederiksen
 */
public class UserMapper implements IUserMapper {

    private DatabaseConnector connector = new DatabaseConnector();

    public UserMapper(DataSource ds) {
        connector.setDataSource(ds);
    }

    /**
     * Adds a new User to the Database
     * @param username Username of new User
     * @param email Email of new User
     * @param password Password of new User
     * @return Id of the new User
     * @throws SQLException SQLException
     */
    @Override
    public int addUser(String username, String email, String password, RoleEnum role) throws SQLException {
        connector.open();
        String quary = "INSERT INTO users(username, email, password, role) VALUES(?,?,?,?);";
        PreparedStatement ps = connector.prepareStatement(quary);
        try {
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, role.toString());
            connector.setAutoCommit(false);
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (connector != null) {
                connector.rollback();
            }
        } finally {
            connector.setAutoCommit(true);
            connector.close();
        }
        return -1;
    }

    /**
     * Returns a list of all Users in the Database
     * @return List of Users
     * @throws SQLException SQLException
     */
    @Override
    public List<User> getUsers() throws SQLException {
        connector.open();
        List<User> users = new ArrayList();
        String quary = "SELECT username, email, balance, role FROM users;";
        PreparedStatement ps = connector.prepareStatement(quary);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            users.add(new User(rs.getString("username"), rs.getString("email"), rs.getInt("balance"), RoleEnum.valueOf(rs.getString("role"))));
        }
        connector.close();
        return users;
    }

    /**
     * Returns specific User from Database
     * @param username Username of specific User
     * @return Specific User
     * @throws SQLException SQLException
     */
    @Override
    public User getUser(String username) throws SQLException {
        connector.open();
        String quary = "SELECT username, email, balance, role FROM users WHERE username = ?;";
        PreparedStatement ps = connector.prepareStatement(quary);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        User user = null;
        while (rs.next()) {
            if (username.equalsIgnoreCase(rs.getString("username"))) {
                user = new User(username, rs.getString("email"), rs.getInt("balance"), RoleEnum.valueOf(rs.getString("role")));
            }
        }
        connector.close();
        return user;
    }

    /**
     * Validates the User from the Database
     * @param username Username to validate
     * @param password Password to validate
     * @return Boolean (True = Valid)
     * @throws SQLException SQLException
     */
    @Override
    public boolean validateUser(String username, String password) throws SQLException {
        connector.open();
        String quary = "SELECT username, email FROM users WHERE (username = ? OR email = ?)AND password = ?;";
        PreparedStatement ps = connector.prepareStatement(quary);
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
        connector.close();
        return valid;
    }

    /**
     * Changes Password of specific User in Database
     * @param username Username of specific User
     * @param password New Password of specific User
     * @return Error integer
     * @throws SQLException 
     */
    @Override
    public int changePassword(String username, String password) throws SQLException {
        connector.open();
        String quary = "UPDATE users SET password = ? WHERE username = ?;";
        PreparedStatement ps = connector.prepareStatement(quary);
        try {
            ps.setString(1, password);
            ps.setString(2, username);
            connector.setAutoCommit(false);
            int result = ps.executeUpdate();
            connector.commit();
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (connector != null) {
                connector.rollback();
            }
        } finally {
            connector.setAutoCommit(true);
            connector.close();
        }
        return -1;
    }

    /**
     * Adds a balance to a specific User
     * @param user Specific User
     * @param balance Balance to add
     * @throws SQLException 
     */
    @Override
    public void addBalance(User user, int balance) throws SQLException {
        connector.open();
        String quary = "UPDATE users SET balance = ? WHERE username = ?;";
        PreparedStatement ps = connector.prepareStatement(quary);
        try {
            ps.setInt(1, user.getBalance() + balance);
            ps.setString(2, user.getUsername());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (connector != null) {
                connector.rollback();
            }
        }
        connector.close();
    }

    /**
     * Removes Balance from specific User after Checkout
     * @param user Specific User
     * @param balance Balance to remove
     * @throws SQLException 
     */
    @Override
    public void checkout(User user, int balance) throws SQLException {
        connector.open();
        String quary = "UPDATE users SET balance = ? WHERE username = ?;";
        PreparedStatement ps = connector.prepareStatement(quary);
        try {
            ps.setInt(1, user.getBalance() - balance);
            ps.setString(2, user.getUsername());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (connector != null) {
                connector.rollback();
            }
        }
        connector.close();
    }
}
