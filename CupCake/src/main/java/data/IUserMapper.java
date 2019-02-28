package data;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Martin Frederiksen
 */
public interface IUserMapper {
    void addUser() throws SQLException;
    List<User> getUsers() throws SQLException;
    User getUser(String username) throws SQLException;
}
