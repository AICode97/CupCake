package data;

import java.sql.SQLException;
import java.util.List;
import presentation.model.*;

/**
 *
 * @author Andreas Vikke
 */
public interface ICupcakeMapper {
    void addCupcakePart(CupcakePartEnum partType, String name, double price) throws SQLException;
    List<CupcakePart> getCupcakeParts() throws SQLException;
    CupcakePart getCupcakePartById(CupcakePartEnum partType, int id) throws SQLException;
}
