package logic;

import data.UserMapper;
import java.sql.SQLException;
import presentation.model.User;

/**
 *
 * @author willi & Martin Frederiksen
 */
public class ValidateUserController {

    public static boolean validateUser(String username, String password) {
        boolean validate = false;
        UserMapper um = new UserMapper();
        try {
        User user = um.getUser(username);
        if(user.getPassword().equals(password)) validate = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return validate;
    }
        public static void main(String[] args) {
        ValidateUserController vuc = new ValidateUserController();
        System.out.println(vuc.validateUser("vikke", "1234"));
    }
}
