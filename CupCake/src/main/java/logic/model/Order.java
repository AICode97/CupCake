package logic.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Frederiksen
 */
public class Order {
    private int orderId;
    private String username;
    private Date orderDate;
    private List<LineItem> lineItems;

    public Order(int orderId, String username, Date orderDate, List<LineItem> lineItems) {
        this.orderId = orderId;
        this.username = username;
        this.orderDate = orderDate;
        lineItems = new ArrayList();
        this.lineItems = lineItems;
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
    
    public List<LineItem> getLineItems(){
        return lineItems;
    }
}
