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
        om = new OrderMapper();
        om.setDataSource(ds);
    }

    public OrderMapper setDataSource(DataSource ds) {
        om = new OrderMapper();
        om.setDataSource(ds);
        return om;
    }
    
    
public List<Order> getAllOrders() {
        try {
            return om.getAllOrders();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Order getOrderById(int id){
        try {
            return om.getOrderById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<Order> getOrderByUser(String username){
        try {
            return om.getOrderByUser(username);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void addOrder(ShoppingCart sc, User user){
        try {
            om.addOrder(sc, user);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
