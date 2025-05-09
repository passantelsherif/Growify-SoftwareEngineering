import java.io.Serializable;
import java.util.List;

public class Asset extends MainMenu implements Serializable {
    private String type;
    private String name;
    private String quantity;
    private String date;
    private String price;
    private String UserEmail;

    public Asset(String type, String name, String quantity, String date, String price, String UserEmail) {
        this.type = type;
        this.name = name;
        this.quantity = quantity;
        this.date = date;
        this.price = price;
        this.UserEmail = UserEmail;
    }

    // Abstract method implementations
    @Override
    public void openPage() {
        System.out.println("Opening Asset Page...");
        System.out.println("Asset Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Quantity: " + quantity);
        System.out.println("Date: " + date);
        System.out.println("Price: " + price + " EGP");
    }

    @Override
    public void closePage() {
        System.out.println("Closing Asset Page...");
    }

    public void setType(String type) { this.type = type; }
    public void setName(String name) { this.name = name; }
    public void setQuantity(String quantity) { this.quantity = quantity; }
    public void setDate(String date) { this.date = date; }
    public void setPrice(String price) { this.price = price; }

    public String getType() { return type; }
    public String getName() { return name; }
    public String getQuantity() { return quantity; }
    public String getDate() { return date; }
    public String getPrice() { return price; }
    public String getUserEmail() { return UserEmail; }

    public static boolean editAsset(String originalName, String newType, String newName, String newQuantity, String newDate, String newPrice) {

        List<Asset> assets = Database.loadAssets();
        for (Asset asset : assets) {
            if (asset.getName().equalsIgnoreCase(originalName)) {
                asset.setType(newType);
                asset.setName(newName);
                if (Database.NameExists(newName)){
                    return false;
                }
                asset.setQuantity(newQuantity);
                asset.setDate(newDate);
                asset.setPrice(newPrice);
                return Database.saveAssets(assets);
            }
        }
        return false;
    }

    public static boolean removeAsset(String nameToRemove) {
        List<Asset> assets = Database.loadAssets();
        boolean removed = assets.removeIf(asset -> asset.getName().equals(nameToRemove));
        return removed && Database.saveAssets(assets);
    }

    public static Asset findAssetByName(String originalName) {
        List<Asset> assets = Database.loadAssets();
        for (Asset asset : assets) {
            if (asset.getName().equalsIgnoreCase(originalName)) {
                return asset;
            }
        }
        return null;
    }
}

