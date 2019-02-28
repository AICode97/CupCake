package presentation.model;
/**
 *
 * @author Martin Frederiksen && Andreas Vikke
 */
public class User {
    private String username;
    private String email;
    private String password;
    private double balance;

    public User(String username, String email, String password, double balance) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double newBal) {
        balance = newBal;
    }
}
