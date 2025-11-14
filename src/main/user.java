package main;
import java.io.Serializable;

public class user implements Serializable {
    private static final long serialVersionUID = 1L;

    
    private String id;          
    private String name;
    private String email;
    private String password;
    private String status;

   
    private String contactNumber;
    private String address;
    private String role;        
    


    public user(String staffId, String name, String email, String password, String status) {
        this.id = staffId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;

        this.role = "Staff";       
    }

    
    public user(int customerId, String name, String email, String password,
                String contactNumber, String address, String status, String role) {

        this.id = String.valueOf(customerId);
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;
        this.contactNumber = contactNumber;
        this.address = address;
        this.role = role;
    }

    

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getStatus() { return status; }
    public String getContactNumber() { return contactNumber; }
    public String getAddress() { return address; }
    public String getRole() { return role; }

    

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setStatus(String status) { this.status = status; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public void setAddress(String address) { this.address = address; }
    public void setRole(String role) { this.role = role; }

    

    @Override
    public String toString() {
        return "\nUser Information:" +
               "\nID: " + id +
               "\nName: " + name +
               "\nEmail: " + email +
               "\nRole: " + role +
               "\nContact: " + (contactNumber != null ? contactNumber : "N/A") +
               "\nAddress: " + (address != null ? address : "N/A") +
               "\nStatus: " + status;
    }
}
