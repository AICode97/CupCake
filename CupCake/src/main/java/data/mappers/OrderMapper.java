package data.mappers;

import data.DBConnector;
import data.DataSourceMySql;
import data.DatabaseConnector;
import data.interfaces.IOrderMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import logic.CupcakeController;
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
    private DatabaseConnector connector = new DatabaseConnector();;

    public void setDataSource(DataSource ds){
        connector.setDataSource(ds);
    }

    @Override
    public void addOrder(ShoppingCart sc, User user) throws SQLException {
        connector.open();
        String query = "INSERT INTO orders(username) VALUES(?);";
        PreparedStatement ps = connector.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        int result = 0;
        ResultSet rs = null;

        
        connector.setAutoCommit(false);
        try {
            ps.setString(1, user.getUsername());
            result = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            result = rs.getInt(1);
            for (LineItem li : sc.getLineItems()) {
                addOrderLine(result, li);
            }
            InvoiceMapper im = new InvoiceMapper();
            im.addInvoice(result, sc);
            
            connector.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            connector.rollback();
            if (connector != null) {
                connector.rollback();
            }
        } finally {
            connector.setAutoCommit(true);
            connector.close();
        }
    }

    @Override
    public void addOrderLine(int id, LineItem li) throws SQLException {
        connector.open();
        String query = "INSERT INTO orderLines VALUES(?,?,?,?,?);";
        PreparedStatement ps = connector.prepareStatement(query);
        try {
            ps.setInt(1, id);
            ps.setInt(2, li.getTop().getId());
            ps.setInt(3, li.getBottom().getId());
            ps.setInt(4, li.getQuantity());
            ps.setInt(5, (int) (li.getPrice() * li.getQuantity()));
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            connector.rollback();
        } finally {
            connector.close();
        }
    }

    @Override
    public List<Order> getAllOrders() throws SQLException {
        connector.open();
        List<Order> orders = new ArrayList();
        String quary = "SELECT * FROM orders;";
        PreparedStatement ps = connector.prepareStatement(quary);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            orders.add(new Order(rs.getInt("orderId"), rs.getString("username"), rs.getDate("date"), getLineItemsById(rs.getInt("orderId"))));
            
        }
        connector.close();
        return orders;
    }

    @Override
    public Order getOrderById(int id) throws SQLException {
        connector.open();
        Order order = null;
        String quary = "SELECT * FROM orders WHERE orderId = ?;";
        PreparedStatement ps = connector.prepareStatement(quary);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            if (id == rs.getInt("orderId")) {
                List<LineItem> lis = getLineItemsById(id);
                order = new Order(id, rs.getString("username"), rs.getDate("date"), lis);
            }
        }
        connector.close();
        return order;
    }

    @Override
    public List<Order> getOrderByUser(String username) throws SQLException {
        connector.open();
        List<Order> orders = new ArrayList();
        String quary = "SELECT * FROM orders WHERE username = ?;";
        PreparedStatement ps = connector.prepareStatement(quary);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            orders.add(new Order(rs.getInt("orderId"), username, rs.getDate("date"), getLineItemsById(rs.getInt("orderId"))));
        }
        connector.close();
        return orders;
    }

    @Override
    public List<LineItem> getLineItemsById(int id) throws SQLException {
        connector.open();
        List<LineItem> lineItems = new ArrayList();
        String quary = "SELECT cupcakeTopId, cupcakeBottomId, qty FROM orderLines WHERE orderId = ?;";
        PreparedStatement ps = connector.prepareStatement(quary);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        CupcakeMapper cm = new CupcakeMapper();

        while (rs.next()) {
            lineItems.add(new LineItem(cm.getCupcakePartById(CupcakePartEnum.BOTTOM, rs.getInt("cupcakeBottomId")), cm.getCupcakePartById(CupcakePartEnum.TOP, rs.getInt("cupcakeTopId")), rs.getInt("qty")));
        }
        connector.close();
        return lineItems;
    }
    

    public static void main(String[] args) {
        OrderMapper om = new OrderMapper();
        om.setDataSource(new DataSourceMySql().getDataSource());
        ShoppingCart sc = new ShoppingCart();
        CupcakeController cc = new CupcakeController(new DataSourceMySql().getDataSource());
        cc.setDataSource(new DataSourceMySql().getDataSource());
        UserMapper um = new UserMapper();
        um.setDataSource(new DataSourceMySql().getDataSource());
        sc.addLineItem(new LineItem(cc.getCupcakePart(CupcakePartEnum.BOTTOM, 1), cc.getCupcakePart(CupcakePartEnum.TOP, 3), 10));
        sc.addLineItem(new LineItem(cc.getCupcakePart(CupcakePartEnum.BOTTOM, 5), cc.getCupcakePart(CupcakePartEnum.TOP, 5), 8));
        try {
            User user = um.getUser("vikke");
            //om.addOrder(sc, user);
            //System.out.println("order added");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        /*try {
            
            for(LineItem i : im.getLineItemsById(1)){
                System.out.println(i.getQuantity());
            }
            
            for (Order o : im.getAllOrders()) {
                System.out.println(o.getOrderId());
            }
            System.out.println(im.getOrderById(1).getUsername());
            for (Order o : im.getOrderByUser("vikke")) {
                for(LineItem li : o.getLineItems())
                    System.out.println(li.getBottom());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }*/
    }
    
}
