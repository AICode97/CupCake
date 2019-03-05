package logic.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
            response.addHeader("error", "All fields needs to be filled.");
            request.setAttribute("errormessage", "All fields needs to be filled");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } else {
            UserController rc = new UserController();
            int result = rc.addUser(username, email, password);
            if (result == -1) {
                response.addHeader("error", "User with same username or email is already registered");
                request.setAttribute("errormessage", "User with same username or email is already registered");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            } else {
                User user = rc.getUser(username);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                
                response.addHeader("redirect", request.getContextPath() + "/customer");
                request.getRequestDispatcher("/").forward(request, response);
            }
        }
    }
}
