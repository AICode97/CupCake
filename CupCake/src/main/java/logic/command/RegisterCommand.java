package logic.command;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.UserController;
import logic.model.User;

/**
 *
 * @author Andreas Vikke
 */
public class RegisterCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (username.isEmpty() || username == null || password.isEmpty() || password == null || email.isEmpty() || password == null) {
            request.setAttribute("errormessage", "All fields needs to be filled");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } else {
            UserController rc = new UserController();
            int result = rc.addUser(username, email, password);
            if (result == -1) {
                request.setAttribute("errormessage", "User with same username or email is already registered");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            } else {
                User u = rc.getUser(username);

                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Cupcake</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>User: " + u.getUsername() + " was suscessfully registered</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        }
    }
}
