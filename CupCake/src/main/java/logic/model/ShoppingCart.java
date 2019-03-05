package logic.model;

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
        List<LineItem> cloneItems = new ArrayList(items.size());
        for(LineItem l : items){
            cloneItems.add(l);
        }
        if (items.isEmpty()) {
            lineItems.add(li);
        } else {
            for (LineItem i : cloneItems) {
                if (i.getBottom().getName().equals(li.getBottom().getName()) && i.getTop().getName().equals(li.getTop().getName())) {
                    i.addQuantity(li.getQuantity());
                    lineItems = cloneItems;
                }else {
                    lineItems.add(li);
                }
            }
        }
    }

    public static void main(String[] args) {
        ShoppingCart sc = new ShoppingCart();
        CupcakeController cc = new CupcakeController();
        sc.setLineItem(new LineItem(cc.getCupcakePart(CupcakePartEnum.BOTTOM, 3), cc.getCupcakePart(CupcakePartEnum.TOP, 4), 1));
        sc.setLineItem(new LineItem(cc.getCupcakePart(CupcakePartEnum.BOTTOM, 3), cc.getCupcakePart(CupcakePartEnum.TOP, 4), 2));
        for (LineItem item : sc.getLineItems()) {
            System.out.println(item.getQuantity());
        }
    }
}
