package logic.model;

import data.DataSourceMySql;
import logic.model.enums.CupcakePartEnum;
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

    public void addLineItem(LineItem li) {
        for (LineItem i : lineItems) {
            if (li.getBottom().getId() == i.getBottom().getId() && li.getTop().getId() == i.getTop().getId()) {
                i.addQuantity(li.getQuantity());
                return;
            }
        }
        lineItems.add(li);
    }

    public void removeLineItem(LineItem li) {
        List<LineItem> toRemove = new ArrayList<LineItem>();
        for (LineItem i : lineItems) {
            if (li.getBottom().getId() == i.getBottom().getId() && li.getTop().getId() == i.getTop().getId()) {
                if (i.getQuantity() > li.getQuantity()) {
                    i.subtractQuantity(li.getQuantity());
                } else {
                    toRemove.add(i);
                }
            }
        }
        lineItems.removeAll(toRemove);
    }

    public int calculate() {
        List<LineItem> li = lineItems;
        int price = 0;
        for (LineItem i : li) {
            price += i.getPrice();
        }

        return price;
    }
}
