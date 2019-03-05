package logic.model;

import logic.model.enums.CupcakePartEnum;

/**
 *
 * @author Andreas Vikke
 */
public class CupcakePart {
    private int id;
    private CupcakePartEnum part;
    private String name;
    private double price;

    public CupcakePart(int id, CupcakePartEnum part, String name, double price) {
        this.id = id;
        this.part = part;
        this.name = name;
        this.price = price;
    }
    
    public int getId() {
        return id;
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
