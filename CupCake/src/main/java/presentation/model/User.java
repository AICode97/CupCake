package presentation.model;
/**
 *
 * @author Martin Frederiksen && Andreas Vikke
 */
public class User {
    private String username;
    private String email;
    private double balance;

    public User(String username, String email, double balance) {
        this.username = username;
        this.email = email;
        this.balance = balance;
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
    
    public void setBalance(double newBal) {
        balance = newBal;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", email=" + email + ", balance=" + balance + '}';
    }
}
