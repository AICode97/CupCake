package logic.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.CupcakeController;
import logic.model.LineItem;
import logic.model.ShoppingCart;
import logic.model.User;
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
        CupcakeController cc = new CupcakeController();
        LineItem li = new LineItem(cc.getCupcakePart(CupcakePartEnum.BOTTOM, 1), cc.getCupcakePart(CupcakePartEnum.TOP, 1), 2);

        if (session.getAttribute("ShoppingCart") != null) {
            sc = (ShoppingCart) session.getAttribute("ShoppingCart");
            sc.removeLineItem(li, 2);
        }
        
        
    }

}
