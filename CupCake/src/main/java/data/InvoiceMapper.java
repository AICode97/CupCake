package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import presentation.model.ShoppingCart;

/**
 *
 * @author Martin Frederiksen
 */
public class InvoiceMapper implements IInvoiceMapper {
    DBConnector connector;

    public InvoiceMapper() {
        connector = new DBConnector();
    }
    
    public static void main(String[] args) {
        
    }
    
    @Override
    public void AddShoppingCartData(ShoppingCart sc) throws SQLException{
        Connection connection = connector.getConnection();
        String query = "INSER INTO invoice VALUES(?,?,?,?)";
        PreparedStatement ps = connection.prepareCall(query);
        
        
    }
    
}
