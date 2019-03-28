package logic;

import data.mappers.OrderMapper;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import logic.model.Order;
import logic.model.ShoppingCart;
import logic.model.User;

/**
 *
 * @author Martin Frederiksen
 */
public class OrderController {

    private OrderMapper om;

    public OrderController(DataSource ds) {
        om = new OrderMapper(ds);
    }

    public List<Order> getAllOrders() {
        try {
            return om.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Order getOrderById(int id) {
        try {
            return om.get(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Order> getOrderByUser(String username) {
        try {
            return om.getAllByUser(username);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void addOrder(ShoppingCart sc, User user) {
        try {
            Order order = new Order(0, user.getUsername(), null, null);
            order.setShoppingCart(sc);
            om.add(order);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
