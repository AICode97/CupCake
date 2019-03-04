package data;

import java.sql.SQLException;
import logic.model.ShoppingCart;

/**
 *
 * @author Asger Hermind Sørensen
 */
public interface IInvoiceMapper {
    public void AddShoppingCartData(ShoppingCart sc) throws SQLException;
}
