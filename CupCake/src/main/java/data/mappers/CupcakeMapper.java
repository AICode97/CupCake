package data.mappers;

import data.DatabaseConnector;
import data.interfaces.ICupcakeMapper;
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
public class CupcakeMapper implements ICupcakeMapper {
    private DatabaseConnector connector = new DatabaseConnector();

    public CupcakeMapper(DataSource ds) {
        connector.setDataSource(ds);
    }
    
    /**
     * Adds a new Cupcake Part to the Database
     * @param partType Cupcake Part (TOP, BOTTOM)
     * @param name Name of Cupcake Part
     * @param price Price of Cupcake Part
     * @throws SQLException 
     */
    @Override
    public void addCupcakePart(CupcakePartEnum partType, String name, int price) throws SQLException {
        connector.open();
        String quary = "";
        switch(partType) {
            case TOP:
                quary = "INSERT INTO cupcakeTops(name, price) VALUES(?,?);";
                break;
            case BOTTOM:
                quary = "INSERT INTO cupcakeBottoms(name, price) VALUES(?,?);";
                break;
        }
        PreparedStatement ps = connector.prepareStatement(quary);
        try {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.executeUpdate();
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
     * @throws SQLException 
     */
    @Override
    public List<CupcakePart> getCupcakeParts() throws SQLException {
        connector.open();
        List<CupcakePart> cupcakes = new ArrayList();
        String topQuary = "SELECT * FROM cupcakeTops;";
        String bottomQuary = "SELECT * FROM cupcakeBottoms;";
        Statement stmt = connector.createStatement();
        
        ResultSet rs = stmt.executeQuery(topQuary);
        while (rs.next()) {
            cupcakes.add(new CupcakePart(rs.getInt("id"), CupcakePartEnum.TOP, rs.getString("name"), rs.getInt("price")));
        }
        
        rs = stmt.executeQuery(bottomQuary);
        while (rs.next()) {
            cupcakes.add(new CupcakePart(rs.getInt("id"), CupcakePartEnum.BOTTOM, rs.getString("name"), rs.getInt("price")));
        }
        connector.close();
        return cupcakes;
    }

    /**
     * Returns a specific Cupcake Part from the Database
     * @param partType Cupcake Part (TOP, BOTTOM)
     * @param id Specific Cupcake Id
     * @return Specific Cupcake Part
     * @throws SQLException 
     */
    @Override
    public CupcakePart getCupcakePartById(CupcakePartEnum partType, int id) throws SQLException {
        connector.open();
        String quary = "";
        PreparedStatement ps = null;
        switch(partType) {
            case TOP:
                quary = "SELECT * FROM cupcakeTops WHERE id = ?;";
                ps = connector.prepareStatement(quary);
                break;
            case BOTTOM:
                quary = "SELECT * FROM cupcakeBottoms WHERE id = ?;";
                ps = connector.prepareStatement(quary);
                break;
        }
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        CupcakePart cupcake = null;
        while (rs.next()) {
            cupcake = new CupcakePart(rs.getInt("id"), partType, rs.getString("name"), rs.getInt("price"));
        }
        connector.close();
        return cupcake;
    }
}
