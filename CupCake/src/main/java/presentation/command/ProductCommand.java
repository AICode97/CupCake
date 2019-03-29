package presentation.command;

import data.DataSourceMySql;
import data.exceptions.CommandException;
import data.exceptions.CupcakeException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.CupcakeFacade;
import data.models.CupcakePart;
import data.models.LineItem;
import data.models.ShoppingCart;

/**
 *
 * @author Martin Frederiksen
 */
public class ProductCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        try {
            CupcakePart bottom = CupcakeFacade.getCupcakePart(Integer.parseInt(request.getParameter("bottom")));
            CupcakePart top = CupcakeFacade.getCupcakePart(Integer.parseInt(request.getParameter("top")));
            int qty = Integer.parseInt(request.getParameter("qty"));
            HttpSession session = request.getSession();
            ShoppingCart sc;

            if (session.getAttribute("ShoppingCart") != null) {
                sc = (ShoppingCart) session.getAttribute("ShoppingCart");
            } else {
                sc = new ShoppingCart();
                session.setAttribute("ShoppingCart", sc);
            }
            sc.addLineItem(new LineItem(bottom, top, qty));

            response.sendRedirect(request.getContextPath() + "/shop");
        } catch(SQLException | CupcakeException ex) {
            throw new CommandException(ex.getMessage());
        }
    }
}
