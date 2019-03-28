package data.mappers;

import data.DatabaseConnector;
import data.interfaces.DataMapperInterface;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import logic.model.Invoice;

/**
 *
 * @author Martin Frederiksen
 */
public class InvoiceMapper implements DataMapperInterface<Invoice, Integer> {
    DatabaseConnector connector = new DatabaseConnector();
    
    public InvoiceMapper(DataSource ds) {
        connector.setDataSource(ds);
    }

    /**
     * Adds an Invoice to the Database based on a OrderId
     * @param invoice Invoice
     * @throws SQLException SQLException
     */
    @Override
    public void add(Invoice invoice) throws SQLException {
        connector.open();
        String query = "INSERT INTO invoices(orderId, price) VALUES(?,?);";
        PreparedStatement ps = connector.prepareStatement(query);
        try {
            ps.setInt(1, invoice.getOrderId());
            ps.setInt(2, invoice.getShoppingCart().calculate());
            connector.setAutoCommit(false);
            ps.execute();
            connector.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            connector.rollback();
            if (connector != null) {
                connector.rollback();
            }
        } finally {
            connector.close();
        }
    }    
    
    /**
     * Returns a list of all Invoices from the Database
     * @return List of Invoices
     * @throws SQLException SQLException
     */
    @Override
    public List<Invoice> getAll() throws SQLException {
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

    /**
     * Returns a specific Invoice from the Database
     * @param invoiceId Specific Invoice Id
     * @return Specific Invoice
     * @throws SQLException SQLException
     */
    @Override
    public Invoice get(Integer invoiceId) throws SQLException {
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
}
