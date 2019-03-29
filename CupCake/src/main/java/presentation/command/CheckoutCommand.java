package presentation.command;

import data.DataSourceMySql;
import data.exceptions.CommandException;
import data.exceptions.OrderException;
import data.exceptions.UserException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.OrderFacade;
import logic.UserFacade;
import data.models.ShoppingCart;
import data.models.User;

/**
 *
 * @author Martin Frederiksen
 */
public class CheckoutCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        try {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");

        if (user.getBalance() >= sc.calculate()) {
            UserFacade.changeBalance(user, sc.calculate());
            String username = user.getUsername();
            OrderFacade.addOrder(sc, user);
            sc = null;
            session.setAttribute("user", UserFacade.getUser(username));
            session.setAttribute("ShoppingCart", sc);
            request.getRequestDispatcher("/cart").forward(request, response);
        } else {
            throw new CommandException("Balance to low");
        }
        } catch(SQLException | UserException | OrderException ex) {
            throw new CommandException(ex.getMessage());
        }
    }
}
