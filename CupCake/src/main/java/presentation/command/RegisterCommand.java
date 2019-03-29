package presentation.command;

import data.DataSourceMySql;
import data.exceptions.CommandException;
import data.exceptions.CupcakeException;
import data.exceptions.UserException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.UserFacade;
import data.models.enums.RoleEnum;

/**
 *
 * @author Andreas Vikke
 */
public class RegisterCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");

            if (username.isEmpty() || username == null || password.isEmpty() || password == null || email.isEmpty() || password == null) {
                throw new CommandException("All fields needs to be filled.");
            } else {
                try {
                    UserFacade.addUser(username, email, password, RoleEnum.CUSTOMER);
                    response.addHeader("redirect", request.getContextPath() + "/login");
                    request.getRequestDispatcher("/login").forward(request, response);
                } catch (SQLException ex) {
                    throw new CommandException("User with same username or email is already registered");
                }
            }
        } catch(UserException ex) {
            throw new CommandException(ex.getMessage());
        }
    }
}
