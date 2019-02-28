package presentation.model;

import java.util.ArrayList;
import java.util.List;
import logic.CupcakeController;

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

    public void setLineItem(LineItem li) {
        List<LineItem> items = getLineItems();
        for (LineItem i : items) {
            if (i.getBottom().getName().equals(li.getBottom().getName()) && i.getTop().getName().equals(li.getTop().getName())) {
                i.setQuantity(li.getQuantity());
            } else {
                lineItems.add(li);
            }
        }
    }

    public static void main(String[] args) {
        ShoppingCart sc = new ShoppingCart();
        CupcakeController cc = new CupcakeController();
        sc.setLineItem(new LineItem(cc.getCupcakePart(CupcakePartEnum.BOTTOM, 0), cc.getCupcakePart(CupcakePartEnum.TOP, 0), 1, 1));
        sc.setLineItem(new LineItem(cc.getCupcakePart(CupcakePartEnum.BOTTOM, 0), cc.getCupcakePart(CupcakePartEnum.TOP, 0), 1, 1));
        for (LineItem item : sc.getLineItems()) {
            System.out.println(item.getQuantity());
            System.out.println("BAL");
        }

    }

}
