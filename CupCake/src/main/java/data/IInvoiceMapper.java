package data;

import java.sql.SQLException;
import java.util.List;
import logic.model.LineItem;
import logic.model.Order;
import logic.model.ShoppingCart;
import logic.model.User;

/**
 *
 * @author Asger Hermind Sørensen
 */
public interface IInvoiceMapper {
    void addOrder(ShoppingCart sc, User user) throws SQLException;
    void addOrderLine(int id, LineItem li) throws SQLException;
    void addInvoice(int id, ShoppingCart sc) throws SQLException;
    List<Order> getAllOrders() throws SQLException;
    Order getOrderById(int id) throws SQLException;
    List<Order> getOrderByUser(String username) throws SQLException;
}
