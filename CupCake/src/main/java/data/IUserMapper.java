package data;

import java.sql.SQLException;
import java.util.List;
import presentation.model.User;

/**
 *
 * @author Martin Frederiksen
 */
public interface IUserMapper {
    void addUser(String username, String email, String password) throws SQLException;
    List<User> getUsers() throws SQLException;
    User getUser(String username) throws SQLException;
}
