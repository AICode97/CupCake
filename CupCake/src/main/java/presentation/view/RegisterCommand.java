package presentation.view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.UserController;
import presentation.model.User;

/**
 *
 * @author Andreas Vikke
 */
public class RegisterCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            
            String origin = request.getParameter("origin");
            
            if(origin != null && origin.equals("register")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String email = request.getParameter("email");

                UserController rc = new UserController();
                rc.addUser(username, email, password);
                User u = rc.getUser(username);

                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Cupcake</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>User: " + u.getUsername() +" was suscessfully registered</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            } else {
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Cupcake</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>CupCake Register Page</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
    }

}
