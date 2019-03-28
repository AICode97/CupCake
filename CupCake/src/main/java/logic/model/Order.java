package logic.model;

import java.sql.Date;
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
    private ShoppingCart shoppingCart;

    public Order(int orderId, String username, Date orderDate, List<LineItem> lineItems) {
        this.orderId = orderId;
        this.username = username;
        this.orderDate = orderDate;
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
    
    public int getTotalPrice() {
        int total = 0;
        for(LineItem li : lineItems)
            total += li.getPrice();
        return total;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
