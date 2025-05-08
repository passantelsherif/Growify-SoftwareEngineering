import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Database {


    // File paths where users and assets are stored
    private static final String USER_FILE = "users_list.ser";
    private static final String ASSET_FILE = "assets_list.ser";

    // --- Method to Save Users --- //
    public static void saveUser(User user) {
        List<User> users = loadUsers();
        users.add(user);
        saveUsers(users);
    }

    // --- Method to Save List of Users to File --- //
    public static void saveUsers(List<User> users) {
        try (FileOutputStream fos = new FileOutputStream(USER_FILE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- Method to Insert Asset --- //
    public static boolean insertAsset(String type, String name, String quantity, String date, String price ,  String userEmail ) {
        List<Asset> assets = loadAssets();
        Asset newAsset = new Asset(type, name, quantity, date, price , userEmail);
        assets.add(newAsset);
        return saveAssets(assets);
    }

    // --- Method to Load Users from File --- //
    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(USER_FILE);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            users = (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            // No users file, it's okay
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    // --- Method to Check if Email Exists --- //
    public static boolean emailExists(String email) {
        List<User> users = loadUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }


    // --- Method to Save Assets --- //
    public static boolean saveAssets(List<Asset> assets) {
        try (FileOutputStream fos = new FileOutputStream(ASSET_FILE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(assets);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // --- Method to Load Assets --- //
    public static List<Asset> loadAssets() {
        List<Asset> assets = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(ASSET_FILE);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            assets = (List<Asset>) ois.readObject();
        } catch (FileNotFoundException e) {
            // No assets file, it's okay
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return assets;
    }


    public static void printAllUsers() {
        List<User> users = loadUsers();

        if (users.isEmpty()) {
            System.out.println("No users found in the file.");
        } else {
            System.out.println("----- All Registered Users -----");
            for (User user : users) {
                System.out.println("Name   : " + user.getName());
                System.out.println("Email  : " + user.getEmail());
                System.out.println("Pass   : " + user.getPassword());
                System.out.println("Phone   : " + user.getPhone());
                System.out.println("-------------------------------");
            }
        }
    }

    public static void printAllAssets() {
        List<Asset> assets = loadAssets();

        if (assets.isEmpty()) {
            System.out.println("No assets found in the file.");
        } else {
            System.out.println("----- All Registered assets -----");
            for (Asset asset : assets) {
                System.out.println("type   : " + asset.getType());
                System.out.println("name  : " + asset.getName());
                System.out.println("quantity   : " + asset.getQuantity());
                System.out.println("date   : " + asset.getDate());
                System.out.println("price   : " + asset.getPrice());
                System.out.println("email   : " + asset.getUserEmail());

                System.out.println("-------------------------------");
            }
        }
    }

}


