package logic;

import java.util.List;
import presentation.model.LineItem;
import presentation.model.ShoppingCart;

/**
 *
 * @author Martin Frederiksen
 */
public class Calculator {
    
    public static int calculate(ShoppingCart sc){
        List<LineItem> li = sc.getLineItem();
        int price = 0;
        for(LineItem i : li){
            price += i.getBottom().getPrice();
            price += i.getTop().getPrice();
            price *= i.getQuantity();
        }
        
        return price;
    }
}
