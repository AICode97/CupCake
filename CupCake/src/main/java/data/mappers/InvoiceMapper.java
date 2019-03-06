package data.mappers;

import data.DBConnector;
import data.interfaces.IInvoiceMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.CupcakeController;
import logic.model.Invoice;
import logic.model.LineItem;
import logic.model.ShoppingCart;
import logic.model.enums.CupcakePartEnum;

/**
 *
 * @author Martin Frederiksen
 */
public class InvoiceMapper implements IInvoiceMapper{
    DBConnector connector;
    
    public InvoiceMapper() {
        connector = new DBConnector();
    }
    

    @Override
    public List<Invoice> getAllInvoices() throws SQLException {
        Connection connection = connector.getConnection();
        List<Invoice> invoices = new ArrayList();
        String quary = "SELECT * FROM invoices;";
        PreparedStatement ps = connection.prepareStatement(quary);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            invoices.add(new Invoice(rs.getInt("invoiceId"), rs.getInt("orderId"), rs.getInt("price"), rs.getDate("date")));
        }
        if (ps != null) {
            ps.close();
        }
        if (rs != null) {
            rs.close();
        }
        return invoices;
    }

    @Override
    public Invoice getInvoiceById(int invoiceId) throws SQLException {
        Connection connection = connector.getConnection();
        Invoice invoice = null;
        String quary = "SELECT * FROM invoices WHERE invoiceId = ?;";
        PreparedStatement ps = connection.prepareStatement(quary);
        ps.setInt(1, invoiceId);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            if(invoiceId == rs.getInt("invoiceId")){
                invoice = new Invoice(invoiceId, rs.getInt("orderId"), rs.getInt("price"), rs.getDate("date"));
            }
        }
        if (ps != null) {
            ps.close();
        }
        if (rs != null) {
            rs.close();
        }
        return invoice;
    }

    @Override
    public void addInvoice(int id, ShoppingCart sc) throws SQLException {
        Connection connection = connector.getConnection();
        String query = "INSERT INTO invoices(orderId, price) VALUES(?,?);";
        PreparedStatement ps = connection.prepareCall(query);
        connection.setAutoCommit(false);
        try {
            ps.setInt(1, id);
            ps.setInt(2, sc.calculate());
            ps.execute();
        connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            connection.rollback();
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            connection.setAutoCommit(true);
            if (ps != null) {
                ps.close();
            }
        }
    }
    
    
    public static void main(String[] args) {
        InvoiceMapper im = new InvoiceMapper();
        ShoppingCart sc = new ShoppingCart();
        CupcakeController cc = new CupcakeController();
        UserMapper um = new UserMapper();
        sc.addLineItem(new LineItem(cc.getCupcakePart(CupcakePartEnum.BOTTOM, 1), cc.getCupcakePart(CupcakePartEnum.TOP, 3), 10));
        sc.addLineItem(new LineItem(cc.getCupcakePart(CupcakePartEnum.BOTTOM, 5), cc.getCupcakePart(CupcakePartEnum.TOP, 5), 8));
        try{
            for(Invoice i : im.getAllInvoices()){
                System.out.println(i.getInvoiceID());
            }
            Invoice invoice = im.getInvoiceById(3);
            System.out.println(invoice.getPrice());
            
            //im.addInvoice(5, sc);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
}
