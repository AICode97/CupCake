/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cupcake.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Martin Frederiksen
 */
public class DBConnector {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    //Vi skal bruge en droplet ip og et database navn
    private static final String URL = "jdbc:mysql://localhost:3306/???";
    //Vi skal have en bruger og et bruger navn evt transformer fra droplet?
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                //Skal vi skrive dette til logfil?
                ex.printStackTrace();
            }
        }
        return connection;
    }
}
