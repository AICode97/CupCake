package logic.command;

import data.DataSourceMySql;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.UserController;
import logic.model.enums.RoleEnum;

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
            UserController rc = new UserController(new DataSourceMySql().getDataSource());
            try {
                rc.addUser(username, email, password, RoleEnum.CUSTOMER);
                response.addHeader("redirect", request.getContextPath() + "/login");
                request.getRequestDispatcher("/login").forward(request, response);
            } catch (SQLException ex) {
                ex.printStackTrace();
                response.addHeader("error", "User with same username or email is already registered");
                request.setAttribute("errormessage", "User with same username or email is already registered");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        }
    }
}
