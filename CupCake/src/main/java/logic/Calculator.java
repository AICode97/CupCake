package logic;

import java.util.List;
import logic.model.LineItem;
import logic.model.ShoppingCart;

/**
 *
 * @author Martin Frederiksen
 */
public class Calculator {
    
    public static int calculate(ShoppingCart sc){
        List<LineItem> li = sc.getLineItems();
        int price = 0;
        for(LineItem i : li){
            price += i.getPrice() * i.getQuantity();
        }
        
        return price;
    }
}
