/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import presentation.model.User;
import data.UserMapper;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author willi & Martin Frederiksen
 */
public class UserController {

    public List<User> getUsers() {
        try {
            return new UserMapper().getUsers();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public User getUser(String username) {
        try {
            return new UserMapper().getUser(username);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void addUser(String username, String email, String password) {
        try {
            new UserMapper().addUser(username, email, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        UserController uc = new UserController();
        List<User> users = uc.getUsers();
        for(User u : users){
            System.out.println(u.getUsername());
        }
        /*User user = uc.getUser("Asger");
        System.out.println(user.getBalance());
        uc.addUser("William", "ErDuFærdig?@gmail.com", "1234");*/
        System.out.println(uc.getUser("William").getEmail());
    }
    
    
}
