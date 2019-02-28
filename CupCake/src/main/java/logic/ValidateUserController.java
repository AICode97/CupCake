/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author willi
 */
public class ValidateUserController {

    public static boolean checkUser(String username, String password) {
        boolean st = false;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://andreasvikke.dk:3306/cupcake", "transformer", "f7qGtArm");
            PreparedStatement ps = con.prepareStatement("select * from users where username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            st = rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }

}
