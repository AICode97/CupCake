package presentation.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import presentation.view.*;

/**
 *
 * @author Andreas Vikke
 */
public abstract class Command {
     public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    
    public static Command from(HttpServletRequest request) {
        Command c;
        String path = request.getPathInfo().substring(1);
        switch (path) {
            case "register":
                c = new RegisterCommand();
                break;
            case "login":
                c = new LoginCommand();
                break;
            default:
                c = new FrontPageCommand();
        }
        return c;
    }
}
