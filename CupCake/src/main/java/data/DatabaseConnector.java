package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 *
 * @author Martin Frederiksen
 */
public class DatabaseConnector {

    private DataSource dataSource;
    private Connection connection;
    private Statement statement;
    private ResultSet rs;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void open() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = dataSource.getConnection();
        }
    }

    public void close() throws SQLException {
        if (rs != null) {
            rs.close();
        }

        if (statement != null) {
            statement.close();
        }

        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null;
        }
    }
    
    public PreparedStatement prepareStatement(String sql) throws SQLException{
        return connection.prepareStatement(sql);
    }
    
    public PreparedStatement prepareStatement(String sql, int indicator) throws SQLException{
        return connection.prepareStatement(sql, indicator);
    }
    
    public void rollback() throws SQLException{
        connection.rollback();
    }
    
    public void setAutoCommit(boolean flag) throws SQLException{
        connection.setAutoCommit(flag);
    }
    
    public void commit() throws SQLException{
        connection.commit();
    }
    
    public Statement createStatement() throws SQLException{
        return connection.createStatement();
    }
}
