package logic.model;

/**
 *
 * @author Andreas Vikke
 */
public class CupcakePart {
    private CupcakePartEnum part;
    private String name;
    private double price;

    public CupcakePart(CupcakePartEnum part, String name, double price) {
        this.part = part;
        this.name = name;
        this.price = price;
    }

    public CupcakePartEnum getPart() {
        return part;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "CupcakePart{" + "part=" + part + ", name=" + name + ", price=" + price + '}';
    }
}
