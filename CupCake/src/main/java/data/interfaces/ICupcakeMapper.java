package data.interfaces;

import logic.model.enums.CupcakePartEnum;
import logic.model.CupcakePart;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Andreas Vikke
 */
public interface ICupcakeMapper {
    void addCupcakePart(CupcakePartEnum partType, String name, int price) throws SQLException;
    List<CupcakePart> getCupcakeParts() throws SQLException;
    CupcakePart getCupcakePartById(int id) throws SQLException;
}
