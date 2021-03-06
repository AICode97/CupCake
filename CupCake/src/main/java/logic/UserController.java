package logic;

import logic.model.User;
import data.mappers.UserMapper;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import javax.xml.bind.DatatypeConverter;
import logic.model.enums.RoleEnum;

/**
 *
 * @author William Hussfeldt - Martin Frederiksen
 */
public class UserController {

    private UserMapper um;

    public UserController(DataSource ds) {
        um = new UserMapper(ds);
    }

    public List<User> getUsers() {
        try {
            return um.getUsers();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public User getUser(String username) {
        try {
            return um.getUser(username);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int addUser(String username, String email, String password, RoleEnum role) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            String passwordHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

            return um.addUser(username, email, passwordHash, role);
        } catch (SQLException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public int changePassword(String username, String newPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(newPassword.getBytes());
            byte[] digest = md.digest();
            String passwordHash2 = DatatypeConverter.printHexBinary(digest).toUpperCase();

            return um.changePassword(username, passwordHash2);
        } catch (SQLException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public void addBalance(User user, int balance) {
        try {
            um.addBalance(user, balance);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void changeBalance(User user, int balance) {
        try {
            um.checkout(user, balance);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean validateUser(String username, String password) {
        if(username == null || password == null || username.equals("") || password.equals("")) return false;
        try {            
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            String passwordHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            
            return um.validateUser(username, passwordHash);
        } catch (SQLException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
