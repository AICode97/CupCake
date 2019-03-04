package logic;

import logic.model.User;
import data.UserMapper;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author William Hussfeldt & Martin Frederiksen
 */
public class UserController {

    public List<User> getUsers() {
        try {
            return new UserMapper().getUsers();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public User getUser(String username) {
        try {
            return new UserMapper().getUser(username);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int addUser(String username, String email, String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            String passwordHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
         
            return new UserMapper().addUser(username, email, passwordHash);
        } catch (SQLException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    public int changePassword(String username, String currentPassword, String newPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            md.update(currentPassword.getBytes());
            byte[] digest = md.digest();
            String passwordHash1 = DatatypeConverter.printHexBinary(digest).toUpperCase();
            
            md.update(newPassword.getBytes());
            digest = md.digest();
            String passwordHash2 = DatatypeConverter.printHexBinary(digest).toUpperCase();
            
            return new UserMapper().changePassword(username, currentPassword);
        } catch (SQLException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
    
    public static void main(String[] args) {
        UserController uc = new UserController();
        List<User> users = uc.getUsers();
        for(User u : users){
            System.out.println(u.getUsername());
        }
        /*User user = uc.getUser("Asger");
        System.out.println(user.getBalance());
        uc.addUser("William", "ErDuFærdig?@gmail.com", "1234");*/
        System.out.println(uc.getUser("William").getEmail());
    }
    
    
}
