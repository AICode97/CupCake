package data;

import java.sql.SQLException;
import logic.model.LineItem;
import logic.model.ShoppingCart;

/**
 *
 * @author Asger Hermind Sørensen
 */
public interface IInvoiceMapper {
    void addOrder(ShoppingCart sc) throws SQLException;
    void addOrderLine(int id, LineItem li) throws SQLException;
    void addInvoice(int id, ShoppingCart sc) throws SQLException;
}
