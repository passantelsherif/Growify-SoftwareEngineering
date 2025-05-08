import java.util.List;

public class Zakat {
    public static String currentUserEmail = null;

    private static final double ZAKAT_PERCENTAGE= 0.025; // 2.5%

    // Calculate zakat for a user based on their assets
    public static double calculateZakat(String email) {
        List<Asset> allAssets = Database.loadAssets();
        double totalZakatableValue = 0.0;

        for (Asset asset : allAssets) {
            if (currentUserEmail.equals(email)) { // assuming this field exists
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
}
