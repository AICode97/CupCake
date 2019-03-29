package logic;

import data.DataSourceMySql;
import data.exceptions.OrderException;
import data.mappers.OrderMapper;
import java.sql.SQLException;
import java.util.List;
import data.models.Order;
import data.models.ShoppingCart;
import data.models.User;

/**
 *
 * @author Martin Frederiksen
 */
public class OrderFacade {

    private static final OrderMapper om = new OrderMapper(new DataSourceMySql().getDataSource());

    public static List<Order> getAllOrders() throws SQLException, OrderException {
        return om.getAll();
    }

    public static Order getOrderById(int id) throws SQLException, OrderException {
        return om.get(id);
    }

    public static List<Order> getOrderByUser(String username) throws SQLException, OrderException {
        return om.getAllByUser(username);
    }

    public static void addOrder(ShoppingCart sc, User user) throws SQLException, OrderException {
        Order order = new Order(0, user.getUsername(), null, null);
        order.setShoppingCart(sc);
        om.add(order);
    }
}
