package presentation.command;

import data.DataSourceMySql;
import data.exceptions.CommandException;
import data.exceptions.UserException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.UserController;
import data.models.User;

/**
 *
 * @author William Sehested Huusfeldt
 */
public class ChangePasswordCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        try {
            response.setContentType("text/html;charset=UTF-8");

            String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("newPassword");
            String newPassword2 = request.getParameter("newPassword2");
        
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("user");

            boolean valid = UserController.validateUser(u.getUsername(), currentPassword);

            if (newPassword2.isEmpty() || newPassword2 == null || currentPassword.isEmpty() || currentPassword == null
                    || newPassword.isEmpty() || newPassword == null) {
                throw new CommandException("All fields needs to be filled.");
            } else if (!newPassword.equals(newPassword2)) {
                throw new CommandException("New passwords doesn't match.");
            } else if (valid) {
                int result = UserController.changePassword(u.getUsername(), newPassword);
                if (result == -1) {
                    throw new CommandException("Something went wrong. Please try again.");
                } else {
                    response.addHeader("success", "Password was successfully changed.");
                    request.getRequestDispatcher("/").forward(request, response);
                }
            } else {
                throw new CommandException("Current Password is not correct");
            }
        } catch(SQLException | UserException | NoSuchAlgorithmException ex) {
            throw new CommandException(ex.getMessage());
        }
    }

}
