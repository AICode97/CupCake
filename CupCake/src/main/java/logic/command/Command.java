package logic.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andreas Vikke
 */
public abstract class Command {

    public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    public static Command from(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command c;
        String path = request.getParameter("command");

        switch (path) {
            case "login":
                c = new LoginCommand();
                break;
            case "register":
                c = new RegisterCommand();
                break;
            case "logout":
                c = new LogoutCommand();
                break;
            case "changePassword":
                c = new ChangePasswordCommand();
                break;
            case "checkout":
                c = new CheckoutCommand();
                break;
            case "addProduct":
                c = new ProductCommand();
                break;
            case "addBalance":
                c = new AddBalanceCommand();
                break;
            default:
                c = null;
        }
        return c;
    }
}
