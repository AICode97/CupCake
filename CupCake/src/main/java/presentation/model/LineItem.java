package presentation.model;

/**
 *
 * @author Martin Frederiksen
 */
public class LineItem {
    private String bottomName;
    private String topName;
    private int quantity;
    private int invoiceId;

    public LineItem(String bottomName, String topName, int quantity, int invoiceId) {
        this.bottomName = bottomName;
        this.topName = topName;
        this.quantity = quantity;
        this.invoiceId = invoiceId;
    }

    public String getBottom() {
        return bottomName;
    }

    public String getTop() {
        return topName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getInvoiceId() {
        return invoiceId;
    } 
}
