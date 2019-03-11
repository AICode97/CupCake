package data.mappers;


import data.DataSourceMySql;
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
    private DatabaseConnector connector = new DatabaseConnector();;

    public UserMapper(DataSource ds) {
        connector.setDataSource(ds);
    }

    @Override
    public int addUser(String username, String email, String password) throws SQLException {
        connector.open();
        String quary = "INSERT INTO users(username, email, password) VALUES(?,?,?);";
        PreparedStatement ps = connector.prepareStatement(quary);
        try {
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
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

    public static void main(String[] args) {
        UserMapper um = new UserMapper(new DataSourceMySql().getDataSource());
        List<User> users = new ArrayList();
        try {
            User user = um.getUser("Martin");
            //um.changePassword(user.getUsername(), "1234");
            //System.out.println(um.validateUser(user.getUsername(), "1234"));
            //um.checkout(user, 100);
            /*users = um.getUsers();
            for(User u : users){
                System.out.println(u.getUsername());
            }*/
            //um.addUser("MartinTest", "JegKoderFlestLinjer@pwned.io", "Mojn");
            //System.out.println(user.getBalance());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
