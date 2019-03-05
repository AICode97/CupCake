package logic;

import data.mappers.OrderMapper;
import java.sql.SQLException;
import java.util.List;
import logic.model.Order;
import logic.model.ShoppingCart;
import logic.model.User;

/**
 *
 * @author Martin Frederiksen
 */
public class OrderController {
public List<Order> getAllOrders() {
        try {
            return new OrderMapper().getAllOrders();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Order getOrderById(int id){
        try {
            return new OrderMapper().getOrderById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<Order> getOrderByUser(String username){
        try {
            return new OrderMapper().getOrderByUser(username);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void addOrder(ShoppingCart sc, User user){
        try {
            new OrderMapper().addOrder(sc, user);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
