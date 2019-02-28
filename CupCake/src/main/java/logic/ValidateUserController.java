package logic;

import data.UserMapper;
import java.sql.SQLException;

/**
 *
 * @author William Hussfeldt & Martin Frederiksen & Andreas Vikke
 */
public class ValidateUserController {

    public static boolean validateUser(String username, String password) {
        if(username == null || password == null || username.equals("") || password.equals("")) return false;
        UserMapper um = new UserMapper();
        try {
            return um.validateUser(username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        ValidateUserController vuc = new ValidateUserController();
        System.out.println(vuc.validateUser("vikke", "1234"));
    }
}
