package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    public void addUser(String username, String email, String password) throws SQLException {
        String quary = "INSERT INTO users(username, email, password) VALUES(?,?,?);";
        PreparedStatement ps = connector.getConnection().prepareCall(quary);
        ps.setString(1, username);
        ps.setString(2, email);
        ps.setString(3, password);
        ps.executeUpdate();
    }

    @Override
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList();
        String quary = "SELECT * FROM users;";
        Statement stmt = connector.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(quary);

        while (rs.next()) {
            users.add(new User(rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getDouble("balance")));
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
                user = new User(username, rs.getString("email"), rs.getString("password"), rs.getDouble("balance"));
            }
        }
        return user;
    }

    public static void main(String[] args) {
        List<User> users = new ArrayList();
        UserMapper um = new UserMapper();
        try {
            users = um.getUsers();
            for (User u : users) {
                System.out.println(u.getUsername());
            }
            /*um.addUser("Vikke", "vikkedesign@gmail.dk", "1234");
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
