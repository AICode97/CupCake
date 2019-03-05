package logic.command;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.UserController;
import logic.ValidateUserController;
import logic.model.User;

/**
 *
 * @author William Sehested Huusfeldt
 */
public class ChangePasswordCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String newPassword2 = request.getParameter("newPassword2");

        UserController uc = new UserController();

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        boolean valid = new ValidateUserController().validateUser(u.getUsername(), currentPassword);

        if (newPassword2.isEmpty() || newPassword2 == null || currentPassword.isEmpty() || currentPassword == null
                || newPassword.isEmpty() || newPassword == null) {
            
            request.setAttribute("errormessage", "All fields needs to be filled.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } else if (!newPassword.equals(newPassword2)) {
            request.setAttribute("errormessage", "New passwords doesn't match.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } else if (valid) {
            int result = uc.changePassword(u.getUsername(), newPassword);
            if (result == -1) {
                request.setAttribute("errormessage", "Something went wrong. Please try again.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            } else {
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Cupcake</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Password was successfully changed.</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        } else {
            request.setAttribute("errormessage", "Current Password is not correct");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

}
