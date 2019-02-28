package presentation.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Frederiksen
 */
public class ShoppingCart {
    private List<LineItem> lineItems;

    public ShoppingCart() {
        lineItems = new ArrayList();
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(LineItem li) {
        lineItems.add(li);
    }
}
