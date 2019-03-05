package logic.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.model.ShoppingCart;
import logic.model.User;

/**
 *
 * @author Martin Frederiksen
 */
public class CheckoutCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");

        if (user.getBalance() >= sc.calculate()) {
            user.setBalance(user.getBalance() - sc.calculate());
        } else {
            request.setAttribute("errormessage", "Balance to low");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
