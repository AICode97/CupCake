package logic.command;

import data.DataSourceMySql;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.UserController;
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
        uc.setDataSource(new DataSourceMySql().getDataSource());
        
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        
        boolean valid = uc.validateUser(u.getUsername(), currentPassword);

        if (newPassword2.isEmpty() || newPassword2 == null || currentPassword.isEmpty() || currentPassword == null
                || newPassword.isEmpty() || newPassword == null) {
            
            response.addHeader("error", "All fields needs to be filled.");
            request.setAttribute("errormessage", "All fields needs to be filled.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } else if (!newPassword.equals(newPassword2)) {
            response.addHeader("error", "New passwords doesn't match.");
            request.setAttribute("errormessage", "New passwords doesn't match.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } else if (valid) {
            int result = uc.changePassword(u.getUsername(), newPassword);
            if (result == -1) {
                response.addHeader("error", "Something went wrong. Please try again.");
                request.setAttribute("errormessage", "Something went wrong. Please try again.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            } else {
                response.addHeader("success", "Password was successfully changed.");
                request.getRequestDispatcher("/").forward(request, response);
            }
        } else {
            response.addHeader("error", "Current Password is not correct");
            request.setAttribute("errormessage", "Current Password is not correct");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

}
