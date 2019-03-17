package data.interfaces;

import java.sql.SQLException;
import java.util.List;
import logic.model.Order;
import logic.model.ShoppingCart;
import logic.model.User;

/**
 *
 * @author Martin Frederiksen
 */
public interface IOrderMapper {
    void addOrder(ShoppingCart sc, User user) throws SQLException;
    List<Order> getOrders() throws SQLException;
    List<Order> getOrdersByUser(String username) throws SQLException;
    Order getOrderById(int id) throws SQLException;
    
}
