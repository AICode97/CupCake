package logic.command;

import data.DataSourceMySql;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.OrderController;
import logic.UserController;
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
        UserController uc = new UserController(new DataSourceMySql().getDataSource());
        uc.setDataSource(new DataSourceMySql().getDataSource());
        OrderController oc = new OrderController(new DataSourceMySql().getDataSource());
        oc.setDataSource(new DataSourceMySql().getDataSource());

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
