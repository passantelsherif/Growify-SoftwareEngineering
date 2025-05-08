public abstract class MainMenu {

    public abstract void openPage();
    public abstract void closePage();

    public void displayMenu() {
        System.out.println("Displaying the menu...");
        openPage();
    }
}