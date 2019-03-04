package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import logic.Calculator;
import logic.CupcakeController;
import presentation.model.CupcakePartEnum;
import presentation.model.LineItem;
import presentation.model.ShoppingCart;

/**
 *
 * @author Martin Frederiksen
 */
public class InvoiceMapper implements IInvoiceMapper {

    DBConnector connector;

    public InvoiceMapper() {
        connector = new DBConnector();
    }

    @Override
    public void addOrder(ShoppingCart sc) throws SQLException {
        Connection connection = connector.getConnection();
        String query = "INSERT INTO orders VALUES();";
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        try {

            int result = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            result = rs.getInt(1);
            for(LineItem li : sc.getLineItems()){
                addOrderLine(result, li);   
            }
            addInvoice(result, sc);
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (connection != null) {
                connection.rollback();
            }
        } finally {
            connection.setAutoCommit(true);
            if (ps != null) {
                ps.close();
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
                //ps.setInt(2, li.getTop());
                //ps.setInt(3, li.getBottom());
                ps.setInt(4, li.getQuantity());
                ps.setDouble(5, li.getPrice());
        } catch (SQLException ex) {
            ex.printStackTrace();
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
            /*int result = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            result = rs.getInt(1);*/
            ps.setInt(1, id);
            ps.setInt(2, Calculator.calculate(sc));
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public static void main(String[] args) {
        //InvoiceMapper im = new InvoiceMapper();
        //im.addInvoice(1, sc);
        
        InvoiceMapper im = new InvoiceMapper();
        ShoppingCart sc = new ShoppingCart();
        CupcakeController cc = new CupcakeController();
        sc.setLineItem(new LineItem(cc.getCupcakePart(CupcakePartEnum.BOTTOM, 1), cc.getCupcakePart(CupcakePartEnum.TOP, 3), 10, 1));
//        sc.setLineItem(new LineItem(cc.getCupcakePart(CupcakePartEnum.BOTTOM, 5), cc.getCupcakePart(CupcakePartEnum.TOP, 5), 8, 1));
        try {
            im.addOrder(sc);
            //im.addInvoice(1, sc);
            System.out.println("invoice added");
            
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }

}
