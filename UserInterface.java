import javax.swing.*;
import java.awt.*;

public class UserInterface {
    public static void DisplayApplication() {
        new Start();
    }

    public static void showSuccessfulMsg(String message) {
        JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void DisplaySignup() {
        new SignUpField();
    }

    public static void DisplaySignin() {
        new SignInField();
    }

    public static void DisplayResetPassword() {
        new ResetField();
    }

    public static void DisplayDashboardPage() {
        new DisplayDashboard();
    }

    public static void DisplayInvestmentDashboardPage() {
        new DisplayInvestmentDashboard();
    }

    public static void AddAssetPage() {
        new ShowAddAssetForm();
    }

    public static void EditAssetPage(String originalName) {
        new EditAssetForm(originalName);
    }

    public static void RemoveAssetPage() {
        new RemoveAssetForm();
    }

    public static void DisplayZakatPage() {
        new ZakatCalculationForm();
    }

    public static void ReportPage() {
        new ReportForm() ;
    }


    //Start page
    static class Start extends JFrame {
        private JButton SignIn, SignUp, ForgetPassword, exitButton;

        public Start() {
            setTitle("Growify");
            setSize(300, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(8, 2));

            SignIn = new JButton("Sign In");
            SignUp = new JButton("Sign Up");
            ForgetPassword = new JButton("Forget Password");
            exitButton = new JButton("Exit");

            add(new JLabel("Welcome to Growify Application"));
            add(SignIn);
            add(SignUp);
            add(ForgetPassword);
            add(exitButton);

            SignUp.addActionListener(e -> {
                UserInterface.DisplaySignup();
            });

            SignIn.addActionListener(e -> {
                UserInterface.DisplaySignin();
            });

            ForgetPassword.addActionListener(e -> {
                UserInterface.DisplayResetPassword();
            });

            exitButton.addActionListener(e -> System.exit(0));

            setVisible(true);
        }
    }


    // Sign-Up Page UI
    static class SignUpField extends JFrame {
        private JTextField nameField, emailField, phoneField;
        private JPasswordField passwordField;
        private JButton registerButton, exitButton;

        public SignUpField() {
            setTitle("Growify - Sign Up");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(6, 1));

            nameField = new JTextField();
            emailField = new JTextField();
            phoneField = new JTextField();
            passwordField = new JPasswordField();
            registerButton = new JButton("Register");
            exitButton = new JButton("Exit");

            add(new JLabel("UserName:"));
            add(nameField);
            add(new JLabel("Email:"));
            add(emailField);
            add(new JLabel("Phone:"));
            add(phoneField);
            add(new JLabel("Password:"));
            add(passwordField);
            add(registerButton);
            add(exitButton);

            registerButton.addActionListener(e -> {
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String phone = phoneField.getText().trim();
                String password = new String(passwordField.getPassword());

                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                    showError("Please fill all fields.");
                } else {
                    User user = new User(name, email, password, phone);

                    if (Authentication.validateCredentialsSignUp(user)) {
                        showSuccessfulMsg("You Registered successfully!");
                        UserInterface.DisplayApplication();
                    } else {
                        showError("Email already registered or an error occurred during registration.");
                    }
                }
            });

            exitButton.addActionListener(e -> System.exit(0));

            setVisible(true);
        }
    }

    // Sign-In Page UI
    static class SignInField extends JFrame {
        private JTextField emailField;
        private JPasswordField passwordField;
        private JButton ConfirmButton;

        public SignInField() {
            setTitle("Growify - Sign In");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(6, 1));

            emailField = new JTextField();
            passwordField = new JPasswordField();
            ConfirmButton = new JButton("Confirm");

            add(new JLabel("Email:"));
            add(emailField);
            add(new JLabel("Password:"));
            add(passwordField);
            add(ConfirmButton);

            ConfirmButton.addActionListener(e -> {
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword());

                if (email.isEmpty() || password.isEmpty()) {
                    showError("Please fill all fields.");
                } else {

                    if (Authentication.validateCredentialsSignIn(email, password)) {
                        Zakat.currentUserEmail = email;
                        showSuccessfulMsg("You Signed In successfully!..Welcome to Growify!");
                        UserInterface.DisplayDashboardPage();
                    } else {
                        showError("Password or Email is incorrect or empty !");
                    }
                }
            });

            setVisible(true);
        }
    }

    //Reset page GUI
    static class ResetField extends JFrame {
        private JTextField emailField;
        private JButton ConfirmButton;

        public ResetField() {
            setTitle("Growify - Reset Password");
            setSize(400, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(6, 1));

            emailField = new JTextField();
            ConfirmButton = new JButton("Confirm");


            add(new JLabel("Email:"));
            add(emailField);
            add(ConfirmButton);

            ConfirmButton.addActionListener(e -> {
                String email = emailField.getText().trim();

                if (email.isEmpty()) {
                    showError("Please fill all fields.");
                } else {
                    String newPassword = promptForPassword();
                    if (newPassword == null) return; // User canceled or input was invalid

                    boolean result = Authentication.resetPassword(email, newPassword);
                    if (result) {
                        UserInterface.showSuccessfulMsg("Password has been reset successfully!");
                        dispose();
                    } else {
                        UserInterface.showError("Email not found or error updating password.");
                    }

                }
            });


            setVisible(true);
        }

        private String promptForPassword() {
            JPasswordField passwordField = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(
                    this,
                    passwordField,
                    "Enter New Password",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (option == JOptionPane.OK_OPTION) {
                String newPassword = new String(passwordField.getPassword());
                if (newPassword.isEmpty()) {
                    UserInterface.showError("Password cannot be empty.");
                    return null;
                }
                return newPassword;
            }
            return null;
        }
    }


    // Dashboard Page UI
    static class DisplayDashboard extends JFrame {
        private JButton InvestmentDashboard, Integration, RiskAllocation, FinancialGoals, ReportInsight, Zakat_compliance;

        public DisplayDashboard() {
            setTitle("Growify - Dashboard");
            setSize(300, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(8, 1));

            InvestmentDashboard = new JButton("Investment Dashboard");
            Integration = new JButton("Integration");
            RiskAllocation = new JButton("Risk Allocation");
            FinancialGoals = new JButton("Financial Goals");
            ReportInsight = new JButton("Report & Insight");
            Zakat_compliance = new JButton("Zakat & compliance");

            add(new JLabel("                               Main Menu"));
            add(InvestmentDashboard);
            add(Integration);
            add(RiskAllocation);
            add(FinancialGoals);
            add(ReportInsight);
            add(Zakat_compliance);

            InvestmentDashboard.addActionListener(e -> UserInterface.DisplayInvestmentDashboardPage());
            Zakat_compliance.addActionListener(e -> UserInterface.DisplayZakatPage());
            ReportInsight.addActionListener(e -> UserInterface.ReportPage());

            setVisible(true);
        }
    }

    // InvestmentDashboard Page UI
    static class DisplayInvestmentDashboard extends JFrame {
        private JButton AddAsset, RemoveAsset, EditAsset;

        public DisplayInvestmentDashboard() {
            setTitle("Growify - Investment Dashboard");
            setSize(300, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(4, 1));

            AddAsset = new JButton("Add Asset");
            RemoveAsset = new JButton("Remove Asset");
            EditAsset = new JButton("Edit Asset");

            add(new JLabel("Welcome To Investment Dashboard ! "));
            add(AddAsset);
            add(RemoveAsset);
            add(EditAsset);

            AddAsset.addActionListener(e -> UserInterface.AddAssetPage());

            // Edit Asset action
            EditAsset.addActionListener(e -> {
                String originalName = JOptionPane.showInputDialog(this, "Enter the name of the asset to edit:");
                if (originalName != null && !originalName.trim().isEmpty()) {
                    UserInterface.EditAssetPage(originalName);  // Pass the original name to edit
                } else {
                    JOptionPane.showMessageDialog(this, "Asset name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            RemoveAsset.addActionListener(e -> UserInterface.RemoveAssetPage());

            setVisible(true);
        }
    }

    // User Interface of Add Asset Page
    public static class ShowAddAssetForm extends JFrame {
        private JTextField typeField, nameField, quantityField, dateField, priceField;
        private JButton submitButton;

        public ShowAddAssetForm() {
            setTitle("Growify - Add Asset");
            setSize(400, 400);
            setLayout(new GridLayout(6, 2));
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // So it doesn't exit app

            // Initialize fields
            typeField = new JTextField();
            nameField = new JTextField();
            quantityField = new JTextField();
            dateField = new JTextField();
            priceField = new JTextField();
            submitButton = new JButton("Submit");

            // Add components to frame
            add(new JLabel("Asset Type:"));
            add(typeField);

            add(new JLabel("Asset Name:"));
            add(nameField);

            add(new JLabel("Quantity:"));
            add(quantityField);

            add(new JLabel("Date (DD/MM/YYYY):"));
            add(dateField);

            add(new JLabel("Price:"));
            add(priceField);

            add(submitButton);

            // Submit action
            submitButton.addActionListener(e -> {
                String type = typeField.getText().trim();
                String name = nameField.getText().trim();
                String quantity = quantityField.getText().trim();
                String date = dateField.getText().trim();
                String price = priceField.getText().trim();

                if (type.isEmpty() || name.isEmpty() || quantity.isEmpty() || date.isEmpty() || price.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean success = Database.insertAsset(type, name, quantity, date, price, Zakat.currentUserEmail);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Asset added successfully.");
                    dispose();
                    UserInterface.DisplayDashboardPage();

                } else {
                    JOptionPane.showMessageDialog(this, "Name already exists please try another name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            setVisible(true);
        }
    }


    // EditAsset Page GUI
    public static class EditAssetForm extends JFrame {
        private JTextField typeField, nameField, quantityField, dateField, priceField;
        private JButton updateButton;

        public EditAssetForm(String originalName) {
            setTitle("Growify - Edit Asset");
            setSize(400, 400);
            setLayout(new GridLayout(6, 2));
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Load existing asset by name
            Asset existingAsset = Asset.findAssetByName(originalName);
            if (existingAsset == null) {
                JOptionPane.showMessageDialog(this, "Asset not found!", "Error", JOptionPane.ERROR_MESSAGE);
                dispose();
                return;
            }

            // Initialize fields with existing values
            typeField = new JTextField(existingAsset.getType());
            nameField = new JTextField(existingAsset.getName());
            quantityField = new JTextField(existingAsset.getQuantity());
            dateField = new JTextField(existingAsset.getDate());
            priceField = new JTextField(existingAsset.getPrice());
            updateButton = new JButton("Update");

            // Add components
            add(new JLabel("Asset Type:"));
            add(typeField);

            add(new JLabel("Asset Name:"));
            add(nameField);

            add(new JLabel("Quantity:"));
            add(quantityField);

            add(new JLabel("Date (YYYY-MM-DD):"));
            add(dateField);

            add(new JLabel("Price:"));
            add(priceField);

            add(updateButton);

            // Update logic
            updateButton.addActionListener(e -> {
                String newType = typeField.getText().trim();
                String newName = nameField.getText().trim();
                String newQuantity = quantityField.getText().trim();
                String newDate = dateField.getText().trim();
                String newPrice = priceField.getText().trim();

                if (newType.isEmpty() || newName.isEmpty() || newQuantity.isEmpty() || newDate.isEmpty() || newPrice.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean updated = Asset.editAsset(originalName, newType, newName, newQuantity, newDate, newPrice);
                if (updated) {
                    JOptionPane.showMessageDialog(this, "Asset updated successfully.");
                    dispose();
                    UserInterface.DisplayDashboardPage();
                } else {
                    JOptionPane.showMessageDialog(this, "Name already exists please try another name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            setVisible(true);
        }
    }

    // Remove Asset Form UI
    public static class RemoveAssetForm extends JFrame {
        private JTextField nameField;
        private JButton removeButton;

        public RemoveAssetForm() {
            setTitle("Growify - Remove Asset");
            setSize(400, 200);
            setLayout(new GridLayout(3, 2));
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Initialize fields
            nameField = new JTextField();
            removeButton = new JButton("Remove");

            // Add components to frame
            add(new JLabel("Enter Asset Name to Remove:"));
            add(nameField);
            add(removeButton);

            // Remove asset logic
            removeButton.addActionListener(e -> {
                String assetName = nameField.getText().trim();

                if (assetName.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter an asset name.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean removed = Asset.removeAsset(assetName); // Remove asset by name
                if (removed) {
                    JOptionPane.showMessageDialog(this, "Asset removed successfully.");
                    dispose();
                    UserInterface.DisplayDashboardPage();
                } else {
                    JOptionPane.showMessageDialog(this, "Asset not found or failed to remove.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            setVisible(true);
        }
    }

    //Calculate Zakat GUI
    public static class ZakatCalculationForm extends JFrame {

        private JButton calculateButton;

        public ZakatCalculationForm() {
            setTitle("Growify - Zakat Calculator");
            setSize(400, 200);
            setLayout(new GridLayout(3, 1));
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JLabel infoLabel = new JLabel("Click to calculate your Zakat (2.5% of your assets)", SwingConstants.CENTER);
            calculateButton = new JButton("Calculate Zakat");

            // Add components to frame
            add(infoLabel);
            add(calculateButton);

            // Calculation logic
            calculateButton.addActionListener(e -> {
                if (Zakat.currentUserEmail == null) {
                    JOptionPane.showMessageDialog(this, "Please log in first.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double zakatAmount = Zakat.calculateZakat(Zakat.currentUserEmail);
                JOptionPane.showMessageDialog(this, "Your calculated Zakat is: " + zakatAmount + " EGP", "Zakat Result", JOptionPane.INFORMATION_MESSAGE);
                UserInterface.DisplayDashboardPage();
            });

            setVisible(true);
        }
    }

    //Report GUI
    public static class ReportForm extends JFrame {

        private JTextArea reportArea;
        private JButton refreshButton;

        public ReportForm() {
            setTitle("Growify - Asset Report");
            setSize(500, 400);
            setLayout(new BorderLayout());
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Instructions
            JLabel titleLabel = new JLabel("Your Asset Report", SwingConstants.CENTER);

            // Text area to show report
            reportArea = new JTextArea();
            reportArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(reportArea);
            add(scrollPane, BorderLayout.CENTER);

            // Refresh button
            refreshButton = new JButton("Refresh Report");
            add(refreshButton, BorderLayout.SOUTH);

            refreshButton.addActionListener(e -> {
                String reportText = Report.UserAssetsReport(); // Get the report
                reportArea.setText(reportText);               // Show in GUI
            });
            setVisible(true);
        }
    }

    
}
