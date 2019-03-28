package presentation.command;

import data.DataSourceMySql;
import data.exceptions.CommandException;
import data.exceptions.UserException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.UserController;
import data.models.User;

/**
 *
 * @author Martin Frederiksen
 */
public class AddBalanceCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            String username = user.getUsername();
            
            int balance;
            balance = (request.getParameter("balance").isEmpty()) ? 0 : Integer.parseInt(request.getParameter("balance"));
            UserController.addBalance(user, balance);
            user = UserController.getUser(username);
            session.setAttribute("user", user);

            response.sendRedirect(request.getContextPath() + "/cart");
        } catch(SQLException | UserException ex) {
            throw new CommandException(ex.getMessage());
        }
    }

}
