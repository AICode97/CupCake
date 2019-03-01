package presentation.view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.UserController;
import logic.ValidateUserController;
import presentation.model.User;

/**
 *
 * @author Andreas Vikke
 */
public class LoginCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String origin = request.getParameter("origin");

        if (origin != null && origin.equals("login")) {
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
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Cupcake</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Incorrect username and/or password</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        } else {
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>CupCake Login</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>CupCake Login Page</h1>");
                out.println("<form action=\"" + request.getContextPath() + "/login\" method=\"GET\">");
                out.println("<input type=\"hidden\" name=\"origin\" value=\"login\">");
                out.println("<input type=\"text\" name=\"username\" placeholder=\"Username...\"><br /><br />");
                out.println("<input type=\"password\" name=\"password\" placeholder=\"Password...\"><br /><br />");
                out.println("<input type=\"submit\" value=\"Login\">");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

}
