package data;

import java.sql.Connection;
import presentation.model.ShoppingCart;

/**
 *
 * @author Martin Frederiksen
 */
public class InvoiceMapper implements iInvoiceMapper {
    DBConnector connector;

    public InvoiceMapper() {
        connector = new DBConnector();
    }
    
    
    
    @Override
    public void AddShoppingCartData(ShoppingCart sc) {
        Connection connection = connector.getConnection();
        String query = "INSER INTO invoice VALUES( )";
        
    }
    
}
