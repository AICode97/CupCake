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
import logic.UserFacade;
import data.models.enums.RoleEnum;
import data.models.User;

/**
 *
 * @author Andreas Vikke
 */
public class LoginCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            boolean valid = UserFacade.validateUser(username, password);

            if (valid) {
                User user = UserFacade.getUser(username);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                if(user.getRole() == RoleEnum.ADMIN) {
                    response.addHeader("redirect", request.getContextPath() + "/admin");
                    request.getRequestDispatcher("/admin").forward(request, response);
                } else {
                    response.addHeader("redirect", request.getContextPath() + "/customer");
                    request.getRequestDispatcher("/customer").forward(request, response);
                }
            } else {
                throw new CommandException("Incorrect username and/or password");
            }
        } catch(SQLException | UserException | NoSuchAlgorithmException ex) {
            throw new CommandException(ex.getMessage());
        }
    }
}
