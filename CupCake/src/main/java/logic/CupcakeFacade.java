package logic;

import data.DataSourceMySql;
import data.exceptions.CupcakeException;
import data.mappers.CupcakeMapper;
import java.sql.SQLException;
import java.util.List;
import data.models.CupcakePart;
import data.models.enums.CupcakePartEnum;

/**
 *
 * @author Andreas Vikke
 */
public class CupcakeFacade {
    private static final CupcakeMapper ccm = new CupcakeMapper(new DataSourceMySql().getDataSource());
    
    public static List<CupcakePart> getCupcakeParts() throws SQLException, CupcakeException {
        return ccm.getAll();
    }

    public static CupcakePart getCupcakePart(int id) throws SQLException, CupcakeException {
        return ccm.get(id);
    }

    public static void addCupcakePart(CupcakePartEnum partType, String name, int price) throws SQLException, CupcakeException {
        ccm.add(new CupcakePart(0, partType, name, price));
    }
}
