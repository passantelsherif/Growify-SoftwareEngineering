import java.util.List;

public class Zakat extends MainMenu {
    public static String currentUserEmail = null;

    private static final double ZAKAT_PERCENTAGE = 0.025; // 2.5%

    // Calculate zakat for a user based on their assets
    public static double calculateZakat(String email) {
        List<Asset> allAssets = Database.loadAssets();
        double totalZakatableValue = 0.0;

        for (Asset asset : allAssets) {
            if (asset.getUserEmail().equals(currentUserEmail)) {
                try {
                    double value = Double.parseDouble(asset.getPrice());
                    totalZakatableValue += value;
                } catch (NumberFormatException e) {
                    e.printStackTrace(); // Handle price conversion issues
                }
            }
        }

        return totalZakatableValue * ZAKAT_PERCENTAGE;
    }

    // Implement abstract methods from MainMenu
    @Override
    public void openPage() {
        System.out.println("Zakat calculation page opened.");
        if (currentUserEmail == null) {
            System.out.println("Please log in first.");
        } else {
            double zakatAmount = calculateZakat(currentUserEmail);
            System.out.println("Your calculated Zakat is: " + zakatAmount + " EGP");
        }
    }

    @Override
    public void closePage() {
        System.out.println("Zakat calculation page closed.");
    }
}
