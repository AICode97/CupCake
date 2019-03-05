package logic.model;

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
    
    public int calculate(){
        List<LineItem> li = lineItems;
        int price = 0;
        for(LineItem i : li){
            price += i.getPrice() * i.getQuantity();
        }
        
        return price;
    }

    public static void main(String[] args) {
        ShoppingCart sc = new ShoppingCart();
        CupcakeController cc = new CupcakeController();
        sc.addLineItem(new LineItem(cc.getCupcakePart(CupcakePartEnum.BOTTOM, 3), cc.getCupcakePart(CupcakePartEnum.TOP, 4), 1));
        sc.addLineItem(new LineItem(cc.getCupcakePart(CupcakePartEnum.BOTTOM, 3), cc.getCupcakePart(CupcakePartEnum.TOP, 4), 2));
        for (LineItem item : sc.getLineItems()) {
            System.out.println(item.getQuantity());
        }
    }
}
