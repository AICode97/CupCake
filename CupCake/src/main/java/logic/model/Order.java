package logic.model;

import java.sql.Date;

/**
 *
 * @author Martin Frederiksen
 */
public class Order {
    private int orderId;
    private String username;
    private Date orderDate;

    public Order(int orderId, String username, Date orderDate) {
        this.orderId = orderId;
        this.username = username;
        this.orderDate = orderDate;
    }
    
    public int getOrderId() {
        return orderId;
    }

    public String getUsername() {
        return username;
    }

    public Date getOrderDate() {
        return orderDate;
    }   
}
