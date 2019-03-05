package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import logic.model.RoleEnum;
import logic.model.User;

/**
 *
 * @author Martin Frederiksen
 */
public class UserMapper implements IUserMapper {
    private DBConnector connector;
    //private DatabaseConnector connector;

    public UserMapper() {
        //connector = new DatabaseConnector();
    }
    /*
    public void setDataSource(DataSource ds){
        connector.setDataSource(ds);
    }*/

    @Override
    public int addUser(String username, String email, String password) throws SQLException {
        //connector.open();
        
        Connection connection = connector.getConnection();
        
        String quary = "INSERT INTO users(username, email, password) VALUES(?,?,?);";
        
        //PreparedStatement ps = connector.preparedStatement(quary, Statement.RETURN_GENERATED_KEYS);
        
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
        PreparedStatement ps = connector.getConnection().prepareStatement(quary);
        ResultSet rs = ps.executeQuery(quary);

        while (rs.next()) {
            users.add(new User(rs.getString("username"), rs.getString("email"), rs.getDouble("balance"), RoleEnum.valueOf(rs.getString("role"))));
        }
        if(ps != null){
            ps.close();
        }
        if(rs != null){
            rs.close();
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
        if(ps != null){
            ps.close();
        }
        if(rs != null){
            rs.close();
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
        if(ps != null){
            ps.close();
        }
        if(rs != null){
            rs.close();
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

    public static void main(String[] args) {
        UserMapper um = new UserMapper();
        try{
        um.addUser("Martin", "JegKoderFlestLinjer@pwned.io", "Mojn");
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    
}
