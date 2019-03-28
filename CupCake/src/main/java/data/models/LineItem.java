package data.models;

/**
 *
 * @author Martin Frederiksen
 */
public class LineItem {
    private CupcakePart bottom;
    private CupcakePart top;
    private int quantity;

    public LineItem(CupcakePart bottom, CupcakePart top, int quantity) {
        this.bottom = bottom;
        this.top = top;
        this.quantity = quantity;
    }

    public CupcakePart getBottom() {
        return bottom;
    }

    public CupcakePart getTop() {
        return top;
    }
    public void addQuantity(int qty){
        quantity += qty;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public int getPrice(){
        return (bottom.getPrice() + top.getPrice()) * quantity;
    }

    public void subtractQuantity(int qty) {
        quantity -= qty;
    }
    
    
}
