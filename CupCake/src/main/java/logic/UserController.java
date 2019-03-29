package logic;

import data.DataSourceMySql;
import data.exceptions.UserException;
import data.models.User;
import data.mappers.UserMapper;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import javax.xml.bind.DatatypeConverter;
import data.models.enums.RoleEnum;

/**
 *
 * @author William Hussfeldt - Martin Frederiksen
 */
public class UserController {

    private static final UserMapper um = new UserMapper(new DataSourceMySql().getDataSource());

    public static List<User> getUsers() throws SQLException, UserException {
        return um.getAll();
    }

    public static User getUser(String username) throws SQLException, UserException {
        return um.get(username);
    }

    public  static void addUser(String username, String email, String password, RoleEnum role) throws SQLException, UserException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            String passwordHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            
            User user = new User(username, passwordHash, email, 0, role);
            
            um.add(user);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }

    public static int changePassword(String username, String newPassword) throws UserException, SQLException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(newPassword.getBytes());
            byte[] digest = md.digest();
            String passwordHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

            return um.changePassword(username, passwordHash);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static void addBalance(User user, int balance) throws SQLException, UserException {
        um.addBalance(user, balance);
    }

    public static void changeBalance(User user, int balance) throws SQLException, UserException {
        um.checkout(user, balance);
    }
    
    public static boolean validateUser(String username, String password) throws SQLException, UserException, NoSuchAlgorithmException {
        if(username == null || password == null || username.equals("") || password.equals("")) 
            return false;   
        
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String passwordHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

        return um.validateUser(username, passwordHash);
    }
}
