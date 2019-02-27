package presentation.view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andreas Vikke
 */
public class FrontPageCommand extends Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Cupcake</title>");
                out.println("</head>");
                out.println("<body>");
                
                out.println("<h1>CupCake Front Page</h1>");
                out.println("<a href=\"" + request.getContextPath()+ "/register\"><button>Register</button></a>");
                out.println("<a href=\"" + request.getContextPath()+ "/login\"><button>Login</button></a>");
                
                out.println("</body>");
                out.println("</html>");
            }
    }

}
