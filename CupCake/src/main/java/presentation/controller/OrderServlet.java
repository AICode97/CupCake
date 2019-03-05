package presentation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.OrderController;
import logic.model.Order;
import logic.model.User;
import logic.model.enums.RoleEnum;

/**
 *
 * @author Andreas Vikke
 */
public class OrderServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderController oc = new OrderController();
        HttpSession session = request.getSession();
        
        String orderId = request.getParameter("orderId");
        if(orderId != null) {
            Order order = oc.getOrderById(Integer.parseInt(orderId));
            session.setAttribute("order", order);
            
            if(order == null) {
                request.setAttribute("errormessage", "Order not found.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            } else {
                if (session.getAttribute("user") == null) {
                    request.getRequestDispatcher("/login").forward(request, response);
                } else if (((User) session.getAttribute("user")).getRole() == RoleEnum.ADMIN 
                        || ((User) session.getAttribute("user")).getUsername().endsWith(order.getUsername())) {
                    request.setAttribute("orderId", orderId);
                    request.getRequestDispatcher("/WEB-INF/order.jsp").forward(request, response);
                } else {
                    request.setAttribute("errormessage", "Insufficient permisson to access order.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            }
        } else {
            request.setAttribute("errormessage", "No Order Id supplied.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
