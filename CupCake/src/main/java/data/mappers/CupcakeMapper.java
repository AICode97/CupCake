package data.mappers;

import data.DBConnector;
import data.interfaces.ICupcakeMapper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logic.model.CupcakePart;
import logic.model.enums.CupcakePartEnum;

/**
 *
 * @author Andreas Vikke
 */
public class CupcakeMapper implements ICupcakeMapper {
    
    private DBConnector connector;

    public CupcakeMapper() {
        connector = new DBConnector();
    }


    @Override
    public void addCupcakePart(CupcakePartEnum partType, String name, int price) throws SQLException {
        String quary = "";
        switch(partType) {
            case TOP:
                quary = "INSERT INTO cupcakeTops(name, price) VALUES(?,?);";
                break;
            case BOTTOM:
                quary = "INSERT INTO cupcakeBottoms(name, price) VALUES(?,?);";
                break;
        }
        PreparedStatement ps = connector.getConnection().prepareCall(quary);
        try {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            connector.getConnection().rollback();
            if (connector.getConnection() != null) {
                connector.getConnection().rollback();
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public List<CupcakePart> getCupcakeParts() throws SQLException {
        List<CupcakePart> cupcakes = new ArrayList();
        String topQuary = "SELECT * FROM cupcakeTops;";
        String bottomQuary = "SELECT * FROM cupcakeBottoms;";
        Statement stmt = connector.getConnection().createStatement();
        
        ResultSet rs = stmt.executeQuery(topQuary);
        while (rs.next()) {
            cupcakes.add(new CupcakePart(rs.getInt("id"), CupcakePartEnum.TOP, rs.getString("name"), rs.getInt("price")));
        }
        
        rs = stmt.executeQuery(bottomQuary);
        while (rs.next()) {
            cupcakes.add(new CupcakePart(rs.getInt("id"), CupcakePartEnum.BOTTOM, rs.getString("name"), rs.getInt("price")));
        }
        
        return cupcakes;
    }

    @Override
    public CupcakePart getCupcakePartById(CupcakePartEnum partType, int id) throws SQLException {
        String quary = "";
        PreparedStatement ps = null;
        switch(partType) {
            case TOP:
                quary = "SELECT * FROM cupcakeTops WHERE id = ?;";
                ps = connector.getConnection().prepareStatement(quary);
                break;
            case BOTTOM:
                quary = "SELECT * FROM cupcakeBottoms WHERE id = ?;";
                ps = connector.getConnection().prepareStatement(quary);
                break;
        }
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        CupcakePart cupcake = null;
        while (rs.next()) {
            cupcake = new CupcakePart(rs.getInt("id"), partType, rs.getString("name"), rs.getInt("price"));
        }
        return cupcake;
    }
    
    public static void main(String[] args) {
        CupcakeMapper ccm = new CupcakeMapper();
        try {
            List<CupcakePart> cupcakes = ccm.getCupcakeParts();
            for (CupcakePart ccp : cupcakes) {
                System.out.println(ccp);
            }
            System.out.println(ccm.getCupcakePartById(CupcakePartEnum.TOP, 5));
            System.out.println(ccm.getCupcakePartById(CupcakePartEnum.BOTTOM, 5));
            System.out.println(ccm.getCupcakePartById(CupcakePartEnum.TOP, 1));
            System.out.println(ccm.getCupcakePartById(CupcakePartEnum.BOTTOM, 1));
//            CupcakePart cupcake = ccm.getCupcakePartById(CupcakePartEnum.TOP, 7);
//            CupcakePart cupcake2 = ccm.getCupcakePartById(CupcakePartEnum.BOTTOM, 5);
//            System.out.println(cupcake);
//            System.out.println(cupcake2);

//            ccm.addCupcakePart(CupcakePartEnum.BOTTOM, "Test", 5.5);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
