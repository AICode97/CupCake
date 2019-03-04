package presentation.view;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.model.RoleEnum;
import logic.model.User;
import presentation.view.*;

/**
 *
 * @author Andreas Vikke
 */
public abstract class Command {

    public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    public static Command from(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command c;
        String path = request.getPathInfo().substring(1);
        switch (path) {
            case "register":
                c = new RegisterCommand();
                break;
            case "login":
                c = new LoginCommand();
                break;
            case "products":
                c = new ProductsCommand();
                break;
            case "admin":
                HttpSession session = request.getSession();
                if(session.getAttribute("user") == null) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
                    dispatcher.forward(request, response);
                    c = null;
                }
                else if(((User)session.getAttribute("user")).getRole() == RoleEnum.ADMIN) {
                    c = new AdminPageCommand();
                } else {
                    c = new FrontPageCommand();
                }
                break;
            case "customer":
                HttpSession customerSession = request.getSession();
                if (customerSession.getAttribute("user") == null) {
                    RequestDispatcher customerDispatcher = request.getRequestDispatcher("/login");
                    customerDispatcher.forward(request, response);
                    c = null;
                } else {
                    c = new CustomerCommand();
                }
                break;

            default:
                c = new FrontPageCommand();
        }
        return c;
    }
}
