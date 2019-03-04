/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.Calculator;
import logic.CupcakeController;
import logic.model.CupcakePart;
import logic.model.CupcakePartEnum;
import logic.model.LineItem;
import logic.model.ShoppingCart;

/**
 *
 * @author Andreas Vikke
 */
public class ProductsController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CupcakeController cc = new CupcakeController();     
        CupcakePart bottom = cc.getCupcakePart(CupcakePartEnum.BOTTOM, Integer.parseInt(request.getParameter("bottom")));
        CupcakePart top = cc.getCupcakePart(CupcakePartEnum.TOP, Integer.parseInt(request.getParameter("top")));
        int qty = Integer.parseInt(request.getParameter("qty"));
        int invoice = Integer.parseInt(request.getParameter("invoice"));
        
        ShoppingCart sc = new ShoppingCart();
        sc.setLineItem(new LineItem(bottom, top, qty, invoice));
        
        HttpSession session = request.getSession();
        session.setAttribute("ShoppingCart", sc);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductControl</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>LineItems</h1>");
            for(LineItem li : sc.getLineItems()){
                out.println("<p><b>Nr: "+ li.getInvoiceId() + "</b></p>");
                out.println("<p>Bund: "+ li.getBottom().getName() + "</p>"); 
                out.println("<p>Top: "+ li.getTop().getName() + "</p>");
                out.println("<p>Mængde: "+ li.getQuantity()+ "</p>");
                out.println("<p>Pris: " + Calculator.calculate(sc));
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
