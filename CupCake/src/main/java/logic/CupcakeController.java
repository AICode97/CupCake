package logic;

import data.mappers.CupcakeMapper;
import java.sql.SQLException;
import java.util.List;
import logic.model.CupcakePart;
import logic.model.enums.CupcakePartEnum;

/**
 *
 * @author Andreas Vikke
 */
public class CupcakeController {

    public List<CupcakePart> getCupcakeParts() {
        try {
            return new CupcakeMapper().getCupcakeParts();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public CupcakePart getCupcakePart(CupcakePartEnum partType, int id) {
        try {
            return new CupcakeMapper().getCupcakePartById(partType, id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void addCupcakePart(CupcakePartEnum partType, String name, int price) {
        try {
            new CupcakeMapper().addCupcakePart(partType, name, price);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
