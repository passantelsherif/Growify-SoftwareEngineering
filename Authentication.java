 import java.util.List;

public class Authentication {

    // Method for validating user credentials during sign-up
    public static boolean validateCredentialsSignUp(User user) {
        try {
            if (Database.emailExists(user.getEmail())) {
                return false; // Email already exists
            } else {
                Database.saveUser(user); // Save the user if email doesn't exist
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method for validating credentials during sign-in (email and password)
    public static boolean validateCredentialsSignIn(String email, String password) {
        List<User> users = Database.loadUsers(); // Load users from the serialized file
        if (users.isEmpty()) {
            System.out.println("No users found. The user list is empty.");
            return false;
        }
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true; // Credentials are valid
            }
        }
        return false; // Invalid credentials
    }

    public static boolean resetPassword(String email, String newPassword) {
        List<User> users = Database.loadUsers();
        boolean updated = false;

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                user.setPassword(newPassword);
                updated = true;
                break;
            }
        }

        if (updated) {
            Database.saveUsers(users);  // Save updated list back to file
            return true;
        } else {
            return false; // Email not found
        }
    }


}