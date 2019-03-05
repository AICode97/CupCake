package data.mappers;

import data.DBConnector;
import data.interfaces.IOrderMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logic.model.CupcakePart;
import logic.model.LineItem;
import logic.model.Order;
import logic.model.ShoppingCart;
import logic.model.User;
import logic.model.enums.CupcakePartEnum;

/**
 *
 * @author Martin Frederiksen
 */
public class OrderMapper implements IOrderMapper {

    DBConnector connector;

    public OrderMapper() {
        connector = new DBConnector();
    }

    @Override
    public void addOrder(ShoppingCart sc, User user) throws SQLException {
        Connection connection = connector.getConnection();
        String query = "INSERT INTO orders(username) VALUES(?);";
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        int result = 0;
        ResultSet rs = null;

        try {
            ps.setString(1, user.getUsername());
            result = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            result = rs.getInt(1);
            for (LineItem li : sc.getLineItems()) {
                addOrderLine(result, li);
            }
            addInvoice(result, sc);
            connection.setAutoCommit(false);

        } catch (SQLException ex) {
            ex.printStackTrace();
            connection.rollback();
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            connection.setAutoCommit(true);
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    @Override
    public void addOrderLine(int id, LineItem li) throws SQLException {
        Connection connection = connector.getConnection();
        String query = "INSERT INTO orderLines VALUES(?,?,?,?,?);";
        PreparedStatement ps = connection.prepareStatement(query);
        try {
            ps.setInt(1, id);
            ps.setInt(2, li.getTop().getId());
            ps.setInt(3, li.getBottom().getId());
            ps.setInt(4, li.getQuantity());
            ps.setDouble(5, li.getPrice() * li.getQuantity());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            connection.rollback();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public void addInvoice(int id, ShoppingCart sc) throws SQLException {
        Connection connection = connector.getConnection();
        String query = "INSERT INTO invoices(orderId, price) VALUES(?,?);";
        PreparedStatement ps = connection.prepareCall(query);
        try {
            ps.setInt(1, id);
            ps.setInt(2, sc.calculate());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            connection.rollback();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList();
        Connection connection = connector.getConnection();
        String quary = "SELECT * FROM orders;";
        PreparedStatement ps = connection.prepareStatement(quary);
        ResultSet rs = ps.executeQuery(quary);

        while (rs.next()) {
            orders.add(new Order(rs.getInt("orderId"), rs.getString("username"), rs.getDate("date"), getLineItemsById(rs.getInt("orderId"))));
            
        }
        if (ps != null) {
            ps.close();
        }
        if (rs != null) {
            rs.close();
        }
        return orders;
    }

    @Override
    public Order getOrderById(int id) throws SQLException {
        Order order = null;
        Connection connection = connector.getConnection();
        String quary = "SELECT * FROM orders WHERE orderId = ?;";
        PreparedStatement ps = connection.prepareStatement(quary);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            if (id == rs.getInt("orderId")) {
                order = new Order(id, rs.getString("username"), rs.getDate("date"), getLineItemsById(id));
                
                
            }
        }
        if (ps != null) {
            ps.close();
        }
        if (rs != null) {
            rs.close();
        }
        return order;
    }

    @Override
    public List<Order> getOrderByUser(String username) throws SQLException {
        List<Order> orders = new ArrayList();
        Connection connection = connector.getConnection();
        String quary = "SELECT * FROM orders WHERE username = ?;";
        PreparedStatement ps = connection.prepareStatement(quary);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            orders.add(new Order(rs.getInt("orderId"), username, rs.getDate("date"), getLineItemsById(rs.getInt("orderId"))));
        }
        if (ps != null) {
            ps.close();
        }
        if (rs != null) {
            rs.close();
        }
        return orders;
    }

    @Override
    public List<LineItem> getLineItemsById(int id) throws SQLException {
        List<LineItem> lineItems = new ArrayList();
        Connection connection = connector.getConnection();
        String quary = "SELECT cupcakeTopId, cupcakeBottomId, qty FROM orderLines WHERE orderId = ?;";
        PreparedStatement ps = connection.prepareStatement(quary);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        CupcakeMapper cm = new CupcakeMapper();

        while (rs.next()) {
            lineItems.add(new LineItem(cm.getCupcakePartById(CupcakePartEnum.BOTTOM, rs.getInt("cupcakeBottomId")), cm.getCupcakePartById(CupcakePartEnum.TOP, rs.getInt("cupcakeTopId")), rs.getInt("qty")));
        }
        if (ps != null) {
            ps.close();
        }
        if (rs != null) {
            rs.close();
        }
        return lineItems;
    }
    

    public static void main(String[] args) {
        /*OrderMapper im = new OrderMapper();
        ShoppingCart sc = new ShoppingCart();
        CupcakeController cc = new CupcakeController();
        UserMapper um = new UserMapper();
        sc.setLineItem(new LineItem(cc.getCupcakePart(CupcakePartEnum.BOTTOM, 1), cc.getCupcakePart(CupcakePartEnum.TOP, 3), 10));
        sc.setLineItem(new LineItem(cc.getCupcakePart(CupcakePartEnum.BOTTOM, 5), cc.getCupcakePart(CupcakePartEnum.TOP, 5), 8));
        try {
            User user = um.getUser("vikke");
            im.addOrder(sc, user);
            System.out.println("order added");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }*/

        OrderMapper im = new OrderMapper();
        try {
            
            /*for(LineItem i : im.getLineItemsById(1)){
                System.out.println(i.getQuantity());
            }*/
            
            for (Order o : im.getAllOrders()) {
                System.out.println(o.getOrderId());
            }
            System.out.println(im.getOrderById(1).getUsername());
            for (Order o : im.getOrderByUser("vikke")) {
                System.out.println(o.getOrderId());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
