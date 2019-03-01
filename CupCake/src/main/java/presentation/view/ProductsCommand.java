package presentation.view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.Calculator;
import logic.CupcakeController;
import presentation.model.CupcakePart;
import presentation.model.CupcakePartEnum;
import presentation.model.LineItem;
import presentation.model.ShoppingCart;

/**
 *
 * @author Martin Frederiksen
 */
public class ProductsCommand extends Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CupcakeController cc = new CupcakeController();     
        CupcakePart bottom = cc.getCupcakePart(CupcakePartEnum.BOTTOM, Integer.parseInt(request.getParameter("bottom")));
        CupcakePart top = cc.getCupcakePart(CupcakePartEnum.TOP, Integer.parseInt(request.getParameter("top")));
        int qty = Integer.parseInt(request.getParameter("qty"));
        int invoice = Integer.parseInt(request.getParameter("invoice"));
        
        ShoppingCart sc = new ShoppingCart();
        sc.setLineItem(new LineItem(bottom, top, qty, invoice));
        
        HttpSession session = request.getSession();
        session.setAttribute("ShoppingCart", sc);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductControl</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>LineItems</h1>");
            for(LineItem li : sc.getLineItems()){
                out.println("<p><b>Nr: "+ li.getInvoiceId() + "</b></p>");
                out.println("<p>Bund: "+ li.getBottom().getName() + "</p>"); 
                out.println("<p>Top: "+ li.getTop().getName() + "</p>");
                out.println("<p>Mængde: "+ li.getQuantity()+ "</p>");
                out.println("<p>Pris: " + Calculator.calculate(sc));
            }
            out.println("</body>");
            out.println("</html>");
        }
        
        
    }

}
