package logic.model;
/**
 *
 * @author Martin Frederiksen && Andreas Vikke
 */
public class User {
    private String username;
    private String email;
    private double balance;
    private RoleEnum role;

    public User(String username, String email, double balance, RoleEnum role) {
        this.username = username;
        this.email = email;
        this.balance = balance;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public RoleEnum getRole() {
        return role;
    }
    
    public void setBalance(double newBal) {
        balance = newBal;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", email=" + email + ", balance=" + balance + '}';
    }
}
