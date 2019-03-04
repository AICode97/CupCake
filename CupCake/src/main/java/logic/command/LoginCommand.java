package logic.command;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.UserController;
import logic.ValidateUserController;
import logic.model.User;

/**
 *
 * @author Andreas Vikke
 */
public class LoginCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserController uc = new UserController();

        boolean valid = new ValidateUserController().validateUser(username, password);

        if (valid) {
            User user = uc.getUser(username);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Cupcake</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Welcome " + user.getUsername() + " You are now logged in.</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            request.setAttribute("error", "Incorrect username and/or password");
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
