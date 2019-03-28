package logic.model;

import java.sql.Date;

/**
 *
 * @author Martin Frederiksen
 */
public class Invoice {
    private int invoiceId;
    private int orderId;
    private int price;
    private Date orderDate;
    private ShoppingCart shoppingCart;

    public Invoice(int invoiceId, int orderId, int price, Date orderDate) {
        this.invoiceId = invoiceId;
        this.orderId = orderId;
        this.price = price;
        this.orderDate = orderDate;
    } 
    
    public int getInvoiceId() {
        return invoiceId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getPrice() {
        return price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
