package logic;

import data.mappers.CupcakeMapper;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import logic.model.CupcakePart;
import logic.model.enums.CupcakePartEnum;

/**
 *
 * @author Andreas Vikke
 */
public class CupcakeController {
    private CupcakeMapper ccm;
    
    public CupcakeController(DataSource ds) {
        ccm = new CupcakeMapper();
        ccm.setDataSource(ds);
    }

    public CupcakeMapper setDataSource(DataSource ds) {
        ccm = new CupcakeMapper();
        ccm.setDataSource(ds);
        return ccm;
    }

    public List<CupcakePart> getCupcakeParts() {
        try {
            return ccm.getCupcakeParts();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public CupcakePart getCupcakePart(CupcakePartEnum partType, int id) {
        try {
            return ccm.getCupcakePartById(partType, id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void addCupcakePart(CupcakePartEnum partType, String name, int price) {
        try {
            ccm.addCupcakePart(partType, name, price);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
