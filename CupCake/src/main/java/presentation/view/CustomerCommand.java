package presentation.view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.UserController;
import presentation.model.User;

/**
 *
 * @author William Sehested Huusfeldt
 */
public class CustomerCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession customerSession = request.getSession();
        User u = (User)customerSession.getAttribute("user");
        //test

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Cupcake</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>You are logged in as " + u.getUsername() + "</h1>");
            out.println("test");
            out.println("</body>");
            out.println("</html>");
        }
    }
}