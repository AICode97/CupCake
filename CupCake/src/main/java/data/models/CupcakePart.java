package data.models;

import data.models.enums.CupcakePartEnum;

/**
 *
 * @author Andreas Vikke
 */
public class CupcakePart {
    private int id;
    private CupcakePartEnum part;
    private String name;
    private int price;

    public CupcakePart(int id, CupcakePartEnum part, String name, int price) {
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

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "CupcakePart{" + "part=" + part + ", name=" + name + ", price=" + price + '}';
    }
}
