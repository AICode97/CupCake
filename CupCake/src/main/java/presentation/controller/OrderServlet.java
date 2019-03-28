package presentation.controller;

import data.DataSourceMySql;
import data.exceptions.OrderException;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.OrderController;
import data.models.Order;
import data.models.User;
import data.models.enums.RoleEnum;

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
        try {
        HttpSession session = request.getSession();
        
        String orderId = request.getParameter("orderId");
        if(orderId != null) {
            Order order = OrderController.getOrderById(Integer.parseInt(orderId));
            session.setAttribute("order", order);
            
            if(order == null) {
                response.addHeader("error", "Order not found.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            } else {
                if (session.getAttribute("user") == null) {
                    request.getRequestDispatcher("/login").forward(request, response);
                } else if (((User) session.getAttribute("user")).getRole() == RoleEnum.ADMIN 
                        || ((User) session.getAttribute("user")).getUsername().endsWith(order.getUsername())) {
                    request.setAttribute("orderId", orderId);
                    request.getRequestDispatcher("/WEB-INF/order.jsp").forward(request, response);
                } else {
                    response.addHeader("error", "Insufficient permisson to access order.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            }
        } else {
            response.addHeader("error", "No Order Id supplied.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        } catch(SQLException | OrderException ex) {
            response.addHeader("error", ex.getMessage());
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
