package data.mappers;

import data.DataSourceMySql;
import data.DatabaseConnector;
import data.interfaces.IInvoiceMapper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
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
    DatabaseConnector connector = new DatabaseConnector();
    
    public void setDataSource(DataSource ds){
        connector.setDataSource(ds);
    }

    @Override
    public List<Invoice> getAllInvoices() throws SQLException {
        connector.open();
        List<Invoice> invoices = new ArrayList();
        String quary = "SELECT * FROM invoices;";
        PreparedStatement ps = connector.prepareStatement(quary);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            invoices.add(new Invoice(rs.getInt("invoiceId"), rs.getInt("orderId"), rs.getInt("price"), rs.getDate("date")));
        }
        connector.close();
        return invoices;
    }

    @Override
    public Invoice getInvoiceById(int invoiceId) throws SQLException {
        connector.open();
        Invoice invoice = null;
        String quary = "SELECT * FROM invoices WHERE invoiceId = ?;";
        PreparedStatement ps = connector.prepareStatement(quary);
        ps.setInt(1, invoiceId);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            if(invoiceId == rs.getInt("invoiceId")){
                invoice = new Invoice(invoiceId, rs.getInt("orderId"), rs.getInt("price"), rs.getDate("date"));
            }
        }
        connector.close();
        return invoice;
    }

    @Override
    public void addInvoice(int id, ShoppingCart sc) throws SQLException {
        connector.open();
        String query = "INSERT INTO invoices(orderId, price) VALUES(?,?);";
        PreparedStatement ps = connector.prepareStatement(query);
        connector.setAutoCommit(false);
        try {
            ps.setInt(1, id);
            ps.setInt(2, sc.calculate());
            ps.execute();
        connector.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            connector.rollback();
            if (connector != null) {
                connector.rollback();
            }
        } finally {
            connector.setAutoCommit(true);
            connector.close();
        }
    }
    
    
    public static void main(String[] args) {
        InvoiceMapper im = new InvoiceMapper();
        im.setDataSource(new DataSourceMySql().getDataSource());
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
            im.addInvoice(8, sc);
            
            //im.addInvoice(5, sc);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
}
