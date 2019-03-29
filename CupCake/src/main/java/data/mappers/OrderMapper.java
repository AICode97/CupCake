package data.mappers;

import data.DataSourceMySql;
import data.DatabaseConnector;
import data.exceptions.CupcakeException;
import data.exceptions.InvoiceException;
import data.exceptions.OrderException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import data.models.Invoice;
import data.models.LineItem;
import data.models.Order;

/**
 *
 * @author Martin Frederiksen
 */
public class OrderMapper implements DataMapperInterface<Order, Integer> {
    private DatabaseConnector connector = new DatabaseConnector();

    public OrderMapper(DataSource ds) {
        connector.setDataSource(ds);
    }
    
    /**
     * Adds a new Order to the Database
     * @param order Order
     * @throws SQLException SQLException
     */
    @Override
    public void add(Order order) throws SQLException, OrderException {
        try {
            connector.open();
            String query = "INSERT INTO orders(username) VALUES(?);";
            PreparedStatement ps = connector.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int result = 0;
            ResultSet rs = null;
            ps.setString(1, order.getUsername());
            
            connector.setAutoCommit(false);
            
            result = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            result = rs.getInt(1);
            connector.commit();
            
            for (LineItem li : order.getShoppingCart().getLineItems()) {
                addOrderLine(result, li);
            }
            
            InvoiceMapper im = new InvoiceMapper(new DataSourceMySql().getDataSource());
            Invoice invoice = new Invoice(-1, result, 0, null);
            invoice.setShoppingCart(order.getShoppingCart());
            im.add(invoice);
        } catch (SQLException | InvoiceException ex) {
            ex.printStackTrace();
            if (connector != null) {
                connector.rollback();
            }
            throw new OrderException(ex.getMessage());
        } finally {
            connector.setAutoCommit(true);
            connector.close();
        }
    }
    
    private void addOrderLine(int id, LineItem li) throws SQLException, OrderException {
        try {
            String query = "INSERT INTO orderLines VALUES(?,?,?,?,?);";
            PreparedStatement ps = connector.prepareStatement(query);
            connector.setAutoCommit(false);
            
            ps.setInt(1, id);
            ps.setInt(2, li.getTop().getId());
            ps.setInt(3, li.getBottom().getId());
            ps.setInt(4, li.getQuantity());
            ps.setInt(5, li.getPrice() * li.getQuantity());
            
            ps.execute();
            connector.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (connector != null) {
                connector.rollback();
            }
            throw new OrderException(ex.getMessage());
        } finally {
            connector.setAutoCommit(true);
        }
    }

    /**
     * Returns all Orders from the Database
     * @return List of Orders
     * @throws SQLException SQLException
     */
    @Override
    public List<Order> getAll() throws SQLException, OrderException {
        try {
            connector.open();
            List<Order> orders = new ArrayList();
            String quary = "SELECT * FROM orders;";
            Statement stmt = connector.createStatement();
            ResultSet rs = stmt.executeQuery(quary);

            while (rs.next()) {
                orders.add(new Order(rs.getInt("orderId"), rs.getString("username"), rs.getDate("date"), getLineItemsById(rs.getInt("orderId"))));
            }
            return orders;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new OrderException(ex.getMessage());
        } finally {
            connector.close();
        }
    }

    /**
     * Returns specific order form the Database
     * @param id Specific Order Id
     * @return Specific Order
     * @throws SQLException SQLException
     */
    @Override
    public Order get(Integer id) throws SQLException, OrderException {
        try {
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
            return order;
        } catch(SQLException ex) {
            ex.printStackTrace();
            throw new OrderException(ex.getMessage());
        }
        finally {
            connector.close();
        }
    }

    /**
     * Returns a List of Users specific Orders from Database
     * @param username Specific Users Username
     * @return List of Orders
     * @throws SQLException SQLException
     */
    public List<Order> getAllByUser(String username) throws SQLException, OrderException {
        try {
            connector.open();
            List<Order> orders = new ArrayList();
            String quary = "SELECT * FROM orders WHERE username = ?;";
            PreparedStatement ps = connector.prepareStatement(quary);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orders.add(new Order(rs.getInt("orderId"), username, rs.getDate("date"), getLineItemsById(rs.getInt("orderId"))));
            }
            return orders;
        } catch(SQLException ex) {
            ex.printStackTrace();
            throw new OrderException(ex.getMessage());
        }
        finally {
            connector.close();
        }
    }

    private List<LineItem> getLineItemsById(int id) throws SQLException, OrderException {
        try {
            connector.open();
            List<LineItem> lineItems = new ArrayList();
            String quary = "SELECT cupcakeTopId, cupcakeBottomId, qty FROM orderLines WHERE orderId = ?;";
            PreparedStatement ps = connector.prepareStatement(quary);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            CupcakeMapper cm = new CupcakeMapper(new DataSourceMySql().getDataSource());

            while (rs.next()) {
                lineItems.add(new LineItem(cm.get(rs.getInt("cupcakeBottomId")), cm.get(rs.getInt("cupcakeTopId")), rs.getInt("qty")));
            }

            return lineItems;
        } catch(SQLException | CupcakeException ex) {
            ex.printStackTrace();
            throw new OrderException(ex.getMessage());
        }
    }
}
