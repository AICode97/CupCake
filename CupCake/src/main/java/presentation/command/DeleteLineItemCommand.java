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
import logic.CupcakeController;
import data.models.CupcakePart;
import data.models.LineItem;
import data.models.ShoppingCart;

/**
 *
 * @author William Sehested Huusfeldt
 */
public class DeleteLineItemCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CommandException {
        try {
            HttpSession session = request.getSession();
            ShoppingCart sc;

            CupcakePart bottom = CupcakeController.getCupcakePart(Integer.parseInt(request.getParameter("bottom")));
            CupcakePart top = CupcakeController.getCupcakePart(Integer.parseInt(request.getParameter("top")));
            int qty = Integer.parseInt(request.getParameter("qty"));

            LineItem li = new LineItem(bottom, top, qty);

            if (session.getAttribute("ShoppingCart") != null) {
                sc = (ShoppingCart) session.getAttribute("ShoppingCart");
                sc.removeLineItem(li);

                response.addHeader("success", "Successfully deleted item.");
                request.getRequestDispatcher("/cart").forward(request, response);
            } else {
                throw new CommandException("Nothing to delete.");
            }
        } catch(SQLException | CupcakeException ex) {
            throw new CommandException(ex.getMessage());
        }
    }
}
