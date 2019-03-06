package logic.model;

import java.sql.Date;

/**
 *
 * @author Martin Frederiksen
 */
public class Invoice {
    private int invoiceID;
    private int orderID;
    private int price;
    private Date orderDate;

    public int getInvoiceID() {
        return invoiceID;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getPrice() {
        return price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Invoice(int invoiceID, int orderID, int price, Date orderDate) {
        this.invoiceID = invoiceID;
        this.orderID = orderID;
        this.price = price;
        this.orderDate = orderDate;
    } 
}
