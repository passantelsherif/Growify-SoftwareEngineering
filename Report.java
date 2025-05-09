import java.util.List;

public class Report extends MainMenu {

    @Override
    public void openPage() {
        UserAssetsReport();
    }

    @Override
    public void closePage() {
        System.out.println("Closing the report page...");
    }

    // Print all asset details for the currently logged-in user
    public static String UserAssetsReport() {
        String email = Zakat.currentUserEmail;

        if (email == null) {
            System.out.println("No user is currently logged in.");
            return "No user is currently logged in.";
        }

        List<Asset> allAssets = Database.loadAssets();
        StringBuilder report = new StringBuilder();
        boolean found = false;

        report.append("=== Asset Report for: ").append(Zakat.currentUserEmail).append(" ===\n\n");

        for (Asset asset : allAssets) {
            if (asset.getUserEmail().equals(Zakat.currentUserEmail)) {
                found = true;
                report.append("Asset Name: ").append(asset.getName()).append("\n");
                report.append("Price: ").append(asset.getPrice()).append(" EGP\n");
                report.append("Date: ").append(asset.getDate()).append("\n");
                report.append("Quantity: ").append(asset.getQuantity()).append("\n");

                report.append("-----------------------------\n");
            }
        }

        report.append("Zakat: ").append(Zakat.calculateZakat(email)).append("\n");

        if (!found) {
            report.append("No assets found for this user.");
        }

        return report.toString();
    }
}
