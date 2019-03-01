package data;

import java.sql.Connection;
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
    public void AddShoppingCartData(ShoppingCart sc) {
        Connection connection = connector.getConnection();
        String query = "INSER INTO invoice VALUES(?,?,?,?)";
        
    }
    
}
