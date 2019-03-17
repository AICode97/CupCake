package logic.command;

import data.DataSourceMySql;
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
 * @author William Sehested Huusfeldt
 */
public class DeleteLineItemCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShoppingCart sc;
        CupcakeController cc = new CupcakeController(new DataSourceMySql().getDataSource());
        
        CupcakePart bottom = cc.getCupcakePart(Integer.parseInt(request.getParameter("bottom")));
        CupcakePart top = cc.getCupcakePart(Integer.parseInt(request.getParameter("top")));
        int qty = Integer.parseInt(request.getParameter("qty"));
        
        LineItem li = new LineItem(bottom, top, qty);

        if (session.getAttribute("ShoppingCart") != null) {
            sc = (ShoppingCart) session.getAttribute("ShoppingCart");
            sc.removeLineItem(li);
            
            response.addHeader("success", "Successfully deleted item.");
            request.getRequestDispatcher("/cart").forward(request, response);
        } else {
            response.addHeader("error", "Nothing to delete.");
            request.setAttribute("errormessage", "Nothing to delete.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        
        
    }

}
