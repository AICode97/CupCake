package logic.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.CupcakeController;
import logic.model.CupcakePart;
import logic.model.LineItem;
import logic.model.ShoppingCart;
import logic.model.enums.CupcakePartEnum;

/**
 *
 * @author Martin Frederiksen
 */
public class ProductCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CupcakeController cc = new CupcakeController();
        CupcakePart bottom = cc.getCupcakePart(CupcakePartEnum.BOTTOM, Integer.parseInt(request.getParameter("bottom")));
        CupcakePart top = cc.getCupcakePart(CupcakePartEnum.TOP, Integer.parseInt(request.getParameter("top")));
        int qty = Integer.parseInt(request.getParameter("qty"));
        HttpSession session = request.getSession();

        if (session.getAttribute("ShoppingCart") != null) {
            ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");
            sc.addLineItem(new LineItem(bottom, top, qty));
        } else {
            ShoppingCart sc = new ShoppingCart();
            sc.addLineItem(new LineItem(bottom, top, qty));
            session.setAttribute("ShoppingCart", sc);
        }
        response.sendRedirect(request.getContextPath() + "/shop");
    }
}
