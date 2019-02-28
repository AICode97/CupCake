package logic;

import data.UserMapper;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author William Hussfeldt & Martin Frederiksen & Andreas Vikke
 */
public class ValidateUserController {

    public static boolean validateUser(String username, String password) {
        if(username == null || password == null || username.equals("") || password.equals("")) return false;
        UserMapper um = new UserMapper();
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

    public static void main(String[] args) {
        ValidateUserController vuc = new ValidateUserController();
        System.out.println(vuc.validateUser("vikke", "1234"));
    }
}
