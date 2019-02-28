package presentation.view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.UserController;
import logic.ValidateUserController;

/**
 *
 * @author Andreas Vikke
 */
public class LoginCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ValidateUserController vuc = new ValidateUserController();
        UserController uc = new UserController();
        
        boolean valid = vuc.validateUser(username, password);

        uc.getUser(username);

        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Cupcake</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>CupCake Login Page</h1>");
            out.println("Username: <br>");
            out.println("<input type=\"text\" name=\"UsernameLogin\"><br>");
            out.println("Password : <br>");
            out.println("<input type=\"password\" name=\"PasswordLogin\"><br>");
            out.println("<input type=\"submit\" name=\"LoginLogin\" value=\"Login\"> <br>");
            if (valid) {
                out.println("<h1>You are now logged in.</h1>");
            } else {
                out.println("<h1>Incorrect username and/or password</h1>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

}
