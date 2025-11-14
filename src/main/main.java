package main;

import Config.config;
import java.util.Scanner;

public class main {

    public static void viewUsers() {
        config db = new config();
        String votersQuery = "SELECT * FROM tbl_users";
        String[] headers = {"Customer_ID", "Name", "Email", "Contact_numb", "Role", "Address", "Status"};
        String[] columns = {"Customer_ID", "Name", "Email", "Contact_numb", "Role", "Address", "Status"};

        config conf = new config();
        conf.viewRecords(query, headers, columns);
    }
    

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            config database = new config();
            config.connectDB();
            char cont;
            int choice;

            do {
                System.out.println("\n=== GRABFOOD ORDERING SYSTEM ===");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. View Users");
                System.out.println("4. Update User");
                System.out.println("5. Delete User");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1: 
                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();
                        System.out.print("Enter Password: ");
                        String pass = sc.nextLine();

                        String qry = "SELECT * FROM tbl_users WHERE Email = ? AND Password = ?";
                        java.util.List<java.util.Map<String, Object>> result = database.fetchRecords(qry, email, pass);

                        if (result.isEmpty()) {
                            System.out.println("INVALID CREDENTIALS!");
                        } else {
                            java.util.Map<String, Object> user = result.get(0);
                            String status = user.get("Status").toString();
                            String role = user.get("Role").toString();

                            if (status.equalsIgnoreCase("Pending")) {
                                System.out.println("Your account is still pending approval. Please contact the Admin.");
                            } else {
                                System.out.println("LOGIN SUCCESS!");

                                if (role.equalsIgnoreCase("Admin")) {
                                    boolean exitAdmin = false;
                                    while (!exitAdmin) {
                                        System.out.println("\n=== ADMIN DASHBOARD ===");
                                        System.out.println("1. Approve Account");
                                        System.out.println("2. View All Users");
                                        System.out.println("3. Logout");
                                        System.out.print("Enter choice: ");
                                        int adminChoice = sc.nextInt();
                                        sc.nextLine();

                                        switch (adminChoice) {
                                            case 1:
                                                
                                                String pendingQuery = "SELECT * FROM tbl_users WHERE Status = 'Pending'";
                                                String[] headers = {"Customer_ID", "Name", "Email", "Role", "Status"};
                                                String[] columns = {"Customer_ID", "Name", "Email", "Role", "Status"};
                                                database.viewRecords(pendingQuery, headers, columns);
                                            
                                            case 2:
                                                
                                                System.out.print("Enter Customer_ID to Approve: ");
                                                int approveId = sc.nextInt();
                                                sc.nextLine();

                                                String updateSQL = "UPDATE tbl_users SET Status = ? WHERE Customer_ID = ?";
                                                database.updateRecord(updateSQL, "Approved", approveId);

                                                System.out.println("✅ Account with ID " + approveId + " has been approved!");
                                            
                                            case 3:
                                                
                                                viewUsers();
                                                break;
                                            case 4: 
                                                
                                                System.out.println("Logging out...");
                                                exitAdmin = true;
                                            
                                            default:
                                              System.out.println("Invalid choice. Try again.");
                                        }
                                    }
                                }

                                else if (role.equalsIgnoreCase("Customer")) {
                                    System.out.println("Welcome, " + user.get("Name") + "! Enjoy using GrabFood!");
                                }
                            }
                        }
                    

                    case 2:
                        config con = new config();

                        System.out.print("Enter Your Name: ");
                        String name = sc.nextLine();

                        String regEmail;
                       while (true) {
                        System.out.print("Enter User Email: ");
                        regEmail = sc.nextLine();

                         qry = "SELECT * FROM users WHERE Email = ?";
                         java.util.List<java.util.Map<String, Object>> intresult =
                         con.fetchRecords(qry, regEmail);


                            if (!intresult.isEmpty()) {
                                System.out.println("Email already exists, please enter another.");
                            } else {
                                break;
                            }
                        }

                        System.out.print("Enter Contact Number: ");
                        String cnumber = sc.nextLine();

                        String role = "";
                    OUTER:
                    while (true) {
                        System.out.print("Enter Role (1 - Admin / 2 - Customer): ");
                        String roleInput = sc.nextLine();
                        switch (roleInput) {
                            case "1" :
                                role = "Admin";
                                break OUTER;
                                
                            case "2" :
                                role = "Customer";
                                break OUTER;
                                
                            default:
                            System.out.println("Invalid input! Please enter 1 or 2.");
                        }
                    }

                        System.out.print("Enter Address: ");
                        String address = sc.nextLine();

                        System.out.print("Enter Password: ");
                        String password = sc.nextLine();
                        String hashedPassword = con.hashPassword(password);

                        String status = "Pending";

                      String insertSQL = "INSERT INTO tbl_users (Name, Email, Password, Contact_numb, Role, Address, Status)"

                           + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                            

                        con.addRecord(insertSQL, name, regEmail, hashedPassword, cnumber, role, address, status);
                        System.out.println("Registration successful! Please wait for Admin approval.");
                    

                    case 3: viewUsers();

                    case 4:
                        System.out.print("Enter Customer_ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter New Name: ");
                        String uname = sc.nextLine();
                        System.out.print("Enter New Email: ");
                        String uemail = sc.nextLine();
                        System.out.print("Enter New Contact Number: ");
                        String unumber = sc.nextLine();
                        System.out.print("Enter New Role (Admin/Customer): ");
                        String urole = sc.nextLine();
                        System.out.print("Enter New Password: ");
                        String upass = sc.nextLine();

                        String sqlUpdate = "UPDATE tbl_users SET Name = ?, Email = ?, Contact_numb = ?, Role = ?, Password = ? WHERE Customer_ID = ?";
                        database.updateRecord(sqlUpdate, uname, uemail, unumber, urole, upass, id);

                        System.out.println("User updated successfully!");
                    

                    // ------------------ DELETE USER ------------------
                    case 5: 
                        System.out.print("Enter Customer_ID to DELETE: ");
                        int deleteId = sc.nextInt();

                        String sqlDelete = "DELETE FROM tbl_users WHERE Customer_ID = ?";
                        database.deleteRecord(sqlDelete, deleteId);

                        System.out.println("User deleted successfully!");
                    

                    case 6:
                        System.out.println("Exiting program...");
                        System.exit(0);
                    

                    default:
                       System.out.println("Invalid choice. Please try again.");
                }

                if (choice != 6) {
                    System.out.print("\nDo you want to continue? (Y/N): ");
                    cont = sc.next().charAt(0);
                } else {
                    cont = 'N';
                }

            } while (cont == 'Y' || cont == 'y');
        }
    }
}
