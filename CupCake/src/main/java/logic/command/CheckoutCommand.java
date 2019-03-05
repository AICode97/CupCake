package logic.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.CupcakeController;
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

        ShoppingCart sc = new ShoppingCart();
        CupcakeController ccc = new CupcakeController();

        sc.addLineItem(new LineItem(ccc.getCupcakePart(CupcakePartEnum.TOP, 1), ccc.getCupcakePart(CupcakePartEnum.TOP, 1), 2));
        sc.addLineItem(new LineItem(ccc.getCupcakePart(CupcakePartEnum.TOP, 1), ccc.getCupcakePart(CupcakePartEnum.TOP, 1), 2));
        sc.addLineItem(new LineItem(ccc.getCupcakePart(CupcakePartEnum.TOP, 2), ccc.getCupcakePart(CupcakePartEnum.TOP, 3), 2));
        sc.addLineItem(new LineItem(ccc.getCupcakePart(CupcakePartEnum.TOP, 2), ccc.getCupcakePart(CupcakePartEnum.TOP, 3), 2));
        sc.addLineItem(new LineItem(ccc.getCupcakePart(CupcakePartEnum.TOP, 1), ccc.getCupcakePart(CupcakePartEnum.TOP, 1), 2));
        sc.addLineItem(new LineItem(ccc.getCupcakePart(CupcakePartEnum.TOP, 5), ccc.getCupcakePart(CupcakePartEnum.TOP, 8), 2));
        sc.addLineItem(new LineItem(ccc.getCupcakePart(CupcakePartEnum.TOP, 5), ccc.getCupcakePart(CupcakePartEnum.TOP, 7), 2));

        if (user.getBalance() >= sc.calculate()) {
            uc.changeBalance(user, sc.calculate());
            String username = user.getUsername();
            session.setAttribute("user", uc.getUser(username));
            request.getRequestDispatcher("/cart").forward(request, response);
        } else {
            request.setAttribute("errormessage", "Balance to low");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
