package main;
import java.io.Serializable;

public class Staff implements Serializable {
    private static final long serialVersionUID = 1L;
    private String staffId;
    private String name;
    private String email;
    private String password;
    private String status;

    public Staff(String staffId, String name, String email, String password, String status) {
        this.staffId = staffId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public String getStaffId() { return staffId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getStatus() { return status; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Staff ID: " + staffId + ", Name: " + name + ", Email: " + email + ", Status: " + status;
    }
}
