package logic.model;

import logic.model.enums.RoleEnum;

/**
 *
 * @author Martin Frederiksen - Andreas Vikke
 */
public class User {
    private String username;
    private String password;
    private String email;
    private int balance;
    private RoleEnum role;

    public User(String username, String password, String email, int balance, RoleEnum role) {
        this.username = username;
        this.email = email;
        this.balance = balance;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }
    
     public String getPassword() {
        return password;
    }
    
    public String getEmail() {
        return email;
    }

    public int getBalance() {
        return balance;
    }

    public RoleEnum getRole() {
        return role;
    }
    
    public void setBalance(int newBal) {
        balance = newBal;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", email=" + email + ", balance=" + balance + '}';
    }
}
