////-----------NOTE !!Should put it in database class!------------------
//
//
//
//public class MehodsForDebugFile {
//
//    public static void printAllUsers() {
//        List<User> users = loadUsers();
//
//        if (users.isEmpty()) {
//            System.out.println("No users found in the file.");
//        } else {
//            System.out.println("----- All Registered Users -----");
//            for (User user : users) {
//                System.out.println("Name   : " + user.getName());
//                System.out.println("Email  : " + user.getEmail());
//                System.out.println("Pass   : " + user.getPassword());
//                System.out.println("Phone   : " + user.getPhone());
//                System.out.println("-------------------------------");
//            }
//        }
//    }
//
//    public static void printAllAssets() {
//        List<Asset> assets = loadAssets();
//
//        if (assets.isEmpty()) {
//            System.out.println("No assets found in the file.");
//        } else {
//            System.out.println("----- All Registered assets -----");
//            for (Asset asset : assets) {
//                System.out.println("type   : " + asset.getType());
//                System.out.println("name  : " + asset.getName());
//                System.out.println("quantity   : " + asset.getQuantity());
//                System.out.println("date   : " + asset.getDate());
//                System.out.println("price   : " + asset.getPrice());
//                System.out.println("email   : " + asset.getUserEmail());
//
//                System.out.println("-------------------------------");
//            }
//        }
//    }
//}
