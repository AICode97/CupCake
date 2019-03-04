package data;

import logic.model.CupcakePartEnum;
import logic.model.CupcakePart;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Andreas Vikke
 */
public interface ICupcakeMapper {
    void addCupcakePart(CupcakePartEnum partType, String name, double price) throws SQLException;
    List<CupcakePart> getCupcakeParts() throws SQLException;
    CupcakePart getCupcakePartById(CupcakePartEnum partType, int id) throws SQLException;
}
