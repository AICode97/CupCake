package logic.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.CupcakeController;
import logic.OrderController;
import logic.UserController;
import logic.model.LineItem;
import logic.model.ShoppingCart;
import logic.model.User;
import logic.model.enums.CupcakePartEnum;

/**
 *
 * @author Martin Frederiksen
 */
public class CheckoutCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        //ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");
        UserController uc = new UserController();
        OrderController oc = new OrderController();

        ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");

        if (user.getBalance() >= sc.calculate()) {
            uc.changeBalance(user, sc.calculate());
            String username = user.getUsername();
            oc.addOrder(sc, user);
            sc = null;
            session.setAttribute("user", uc.getUser(username));
            session.setAttribute("ShoppingCart", sc);
            request.getRequestDispatcher("/cart").forward(request, response);
        } else {
            request.setAttribute("errormessage", "Balance to low");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
