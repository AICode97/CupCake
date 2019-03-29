package data.mappers;

import data.DatabaseConnector;
import data.exceptions.UserException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import data.models.enums.RoleEnum;
import data.models.User;
import java.sql.Statement;

/**
 *
 * @author Martin Frederiksen
 */
public class UserMapper implements DataMapperInterface<User, String> {

    private DatabaseConnector connector = new DatabaseConnector();

    public UserMapper(DataSource ds) {
        connector.setDataSource(ds);
    }

    /**
     * Adds a new User to the Database
     * @param user User
     * @throws SQLException SQLException
     */
    @Override
    public void add(User user) throws SQLException, UserException {
        try {
            connector.open();
            String quary = "INSERT INTO users(username, email, password, role) VALUES(?,?,?,?);";
            PreparedStatement ps = connector.prepareStatement(quary);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole().toString());
            connector.setAutoCommit(false);
            ps.executeUpdate();
            connector.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (connector != null) {
                connector.rollback();
            }
            throw new UserException(ex.getMessage());
        } finally {
            connector.close();
        }
    }

    /**
     * Returns a list of all Users in the Database
     * @return List of Users
     * @throws SQLException SQLException
     */
    @Override
    public List<User> getAll() throws SQLException, UserException {
        try {
            connector.open();
            List<User> users = new ArrayList();
            String quary = "SELECT username, email, balance, role FROM users;";
            Statement stmt = connector.createStatement();
            ResultSet rs = stmt.executeQuery(quary);

            while (rs.next()) {
                users.add(new User(rs.getString("username"), null, rs.getString("email"), rs.getInt("balance"), RoleEnum.valueOf(rs.getString("role"))));
            }
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new UserException(ex.getMessage());
        } finally {
            connector.close();
        }
    }

    /**
     * Returns specific User from Database
     * @param username Username of specific User
     * @return Specific User
     * @throws SQLException SQLException
     */
    @Override
    public User get(String username) throws SQLException, UserException {
        try {
            connector.open();
            String quary = "SELECT username, email, balance, role FROM users WHERE username = ?;";
            PreparedStatement ps = connector.prepareStatement(quary);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            User user = null;
            while (rs.next()) {
                if (username.equalsIgnoreCase(rs.getString("username"))) {
                    user = new User(username, rs.getString("email"), null, rs.getInt("balance"), RoleEnum.valueOf(rs.getString("role")));
                }
            }
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new UserException(ex.getMessage());
        } finally {
            connector.close();
        }
    }

    /**
     * Validates the User from the Database
     * @param username Username to validate
     * @param password Password to validate
     * @return Boolean (True = Valid)
     * @throws SQLException SQLException
     */
    public boolean validateUser(String username, String password) throws SQLException, UserException {
       try {
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
            return valid;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new UserException(ex.getMessage());
        } finally {
            connector.close();
        }
    }

    /**
     * Changes Password of specific User in Database
     * @param username Username of specific User
     * @param password New Password of specific User
     * @return Error integer
     * @throws SQLException SQLException
     */
    public int changePassword(String username, String password) throws SQLException, UserException {
        try {
            connector.open();
            String quary = "UPDATE users SET password = ? WHERE username = ?;";
            PreparedStatement ps = connector.prepareStatement(quary);
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
            throw new UserException(ex.getMessage());
        } finally {
            connector.close();
        }
    }

    /**
     * Adds a balance to a specific User
     * @param user Specific User
     * @param balance Balance to add
     * @throws SQLException SQLException
     */
    public void addBalance(User user, int balance) throws SQLException, UserException {
        connector.open();
        String quary = "UPDATE users SET balance = ? WHERE username = ?;";
        PreparedStatement ps = connector.prepareStatement(quary);
        try {
            ps.setInt(1, user.getBalance() + balance);
            ps.setString(2, user.getUsername());
            connector.setAutoCommit(false);
            ps.execute();
            connector.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (connector != null) {
                connector.rollback();
            }
            throw new UserException(ex.getMessage());
        } finally {
            connector.close();
        }
    }

    /**
     * Removes Balance from specific User after Checkout
     * @param user Specific User
     * @param balance Balance to remove
     * @throws SQLException SQLException
     */
    public void checkout(User user, int balance) throws SQLException, UserException {
        connector.open();
        String quary = "UPDATE users SET balance = ? WHERE username = ?;";
        PreparedStatement ps = connector.prepareStatement(quary);
        try {
            ps.setInt(1, user.getBalance() - balance);
            ps.setString(2, user.getUsername());
            connector.setAutoCommit(false);
            ps.execute();
            connector.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (connector != null) {
                connector.rollback();
            }
            throw new UserException(ex.getMessage());
        } finally {
            connector.close();
        }
    }
}
