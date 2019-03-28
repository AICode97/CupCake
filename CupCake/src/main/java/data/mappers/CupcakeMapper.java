package data.mappers;

import data.DatabaseConnector;
import data.interfaces.DataMapperInterface;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import logic.model.CupcakePart;
import logic.model.enums.CupcakePartEnum;

/**
 *
 * @author Andreas Vikke
 */
public class CupcakeMapper implements DataMapperInterface<CupcakePart, Integer> {
    private DatabaseConnector connector = new DatabaseConnector();

    public CupcakeMapper(DataSource ds) {
        connector.setDataSource(ds);
    }
    
    /**
     * Adds a new Cupcake Part to the Database
     * @param cupcake Cupcake Part
     * @throws SQLException SQLException
     */
    @Override
    public void add(CupcakePart cupcake) throws SQLException {
        connector.open();
        String quary = "INSERT INTO cupcakeBottoms(name, price, partType) VALUES(?,?,?);";
        PreparedStatement ps = connector.prepareStatement(quary);
        try {
            ps.setString(1, cupcake.getName());
            ps.setDouble(2, cupcake.getPrice());
            ps.setString(3, cupcake.getPart().toString());
            connector.setAutoCommit(false);
            ps.executeUpdate();
            connector.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            connector.rollback();
            if (connector != null) {
                connector.rollback();
            }
        } finally {
            connector.close();
        }
    }

    /**
     * Returns a list of all CupcakeParts in the Database
     * @return List of Cupcake Parts
     * @throws SQLException SQLException
     */
    @Override
    public List<CupcakePart> getAll() throws SQLException {
        connector.open();
        List<CupcakePart> cupcakes = new ArrayList();
        String quary = "SELECT * FROM cupcakeParts;";
        Statement stmt = connector.createStatement();
        
        ResultSet rs = stmt.executeQuery(quary);
        while (rs.next()) {
            cupcakes.add(new CupcakePart(rs.getInt("id"), CupcakePartEnum.valueOf(rs.getString("partType")), rs.getString("name"), rs.getInt("price")));
        }
        
        connector.close();
        return cupcakes;
    }

    /**
     * Returns a specific Cupcake Part from the Database
     * @param id Specific Cupcake Id
     * @return Specific Cupcake Part
     * @throws SQLException SQLException
     */
    @Override
    public CupcakePart get(Integer id) throws SQLException {
        connector.open();
        String quary = "SELECT * FROM cupcakeParts WHERE id = ?;";
        PreparedStatement ps = connector.prepareStatement(quary);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        CupcakePart cupcake = null;
        while (rs.next()) {
            cupcake = new CupcakePart(rs.getInt("id"), CupcakePartEnum.valueOf(rs.getString("partType")), rs.getString("name"), rs.getInt("price"));
        }
        connector.close();
        return cupcake;
    }
}
