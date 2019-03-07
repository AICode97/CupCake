package logic.command;

import data.DataSourceMySql;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.UserController;
import logic.model.enums.RoleEnum;
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
        uc.setDataSource(new DataSourceMySql().getDataSource());

        boolean valid = uc.validateUser(username, password);

        if (valid) {
            User user = uc.getUser(username);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            
            if(user.getRole() == RoleEnum.ADMIN) {
                response.addHeader("redirect", request.getContextPath() + "/admin");
                request.getRequestDispatcher("/admin").forward(request, response);
            } else {
                response.addHeader("redirect", request.getContextPath() + "/customer");
                request.getRequestDispatcher("/customer").forward(request, response);
            }
        } else {
            response.addHeader("error", "Incorrect username and/or password");
            request.setAttribute("errormessage", "Incorrect username and/or password");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
