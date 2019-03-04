package data;

import java.sql.SQLException;
import presentation.model.LineItem;
import presentation.model.ShoppingCart;

/**
 *
 * @author Asger Hermind Sørensen
 */
public interface IInvoiceMapper {
    void addOrder(ShoppingCart sc) throws SQLException;
    void addOrderLine(int id, LineItem li) throws SQLException;
    void addInvoice(int id, ShoppingCart sc) throws SQLException;
}
