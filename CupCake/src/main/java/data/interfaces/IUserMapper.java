package data.interfaces;

import java.sql.SQLException;
import java.util.List;
import logic.model.User;

/**
 *
 * @author Martin Frederiksen
 */
public interface IUserMapper {
    int addUser(String username, String email, String password) throws SQLException;
    List<User> getUsers() throws SQLException;
    User getUser(String username) throws SQLException;
    boolean validateUser(String username, String password) throws SQLException;
    int changePassword(String username, String password) throws SQLException;
    void addBalance(User user, int balance) throws SQLException;
    void checkout(User user, int balance) throws SQLException;
}
