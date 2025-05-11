
Growify - Investment Management Software


**Description:**
Growify is a desktop application designed to help users manage their investments, calculate Zakat (charitable donation), and generate detailed reports. The program provides a user-friendly interface for adding, editing, and removing assets, as well as viewing investment dashboards and calculating Zakat.

---

**Project Structure and Files:**

1. **Main Classes:**
   - **Main.java**
     - Entry point of the application.
     - Initializes the user interface and manages navigation between pages.

   - **UserInterface.java**
     - Core user interface manager.
     - Contains methods for displaying various pages like the dashboard, add/edit asset forms, and reports.

2. **UI Components:**
   - **DisplayInvestmentDashboard.java**
     - Implements the main investment dashboard GUI.
     - Provides buttons to add, remove, and edit assets.

   - **ShowAddAssetForm.java**
     - GUI for adding a new asset.
     - Collects user input for asset details like type, name, quantity, date, and price.

   - **EditAssetForm.java**
     - GUI for editing existing assets.
     - Prefills asset information for easy modification.

   - **RemoveAssetForm.java**
     - GUI for removing an asset by name.
     - Ensures the asset exists before attempting deletion.

   - **ZakatCalculationForm.java**
     - GUI for calculating Zakat.
     - Calculates 2.5% of the total asset value for the current logged-in user.

   - **ReportForm.java**
     - Displays an asset report in a scrollable text area.
     - Allows users to refresh, export, print, or copy the report.

3. **Core Logic:**
   - **Authentication.java**
     - Handles user sign-up, sign-in, and password reset functionality.
     - Interacts with the database for user validation.

   - **Zakat.java**
     - Calculates Zakat based on the user's assets.
     - Includes logic to display Zakat-related information in the UI.

   - **Asset.java**
     - Represents individual asset data.
     - Provides methods for managing assets (e.g., find, edit, and remove).

   - **Database.java**
     - Manages data storage and retrieval for users and assets.
     - Uses file-based persistence for simplicity.

   - **Report.java**
     - Generates detailed asset reports.
     - Includes functionality to export reports to text files.

4. **Shared Resources:**
   - **MainMenu.java**
     - Base class for various pages, ensuring consistent navigation and UI behavior.

---

**Development Environment:**
- **Programming Language:** Java (JDK 17 or later recommended)
- **Integrated Development Environment:** Visual Studio Code and IntelliJ
  - Required extensions: "Java Extension Pack" for Java support
  - JavaDoc plugin for documentation generation

---

**Execution Instructions:**
1. Open the project in Visual Studio Code.
2. Compile the project using the `javac` command or through the IDE's build tools.
3. Run the `Main.java` file to launch the application.
4. Use the GUI to navigate and manage your investments.

---

**Additional Information:**
- **Dependencies:** No external libraries were used beyond the standard Java Development Kit (JDK).
- **Storage:** User and asset data are stored using serialized files.

---

**Contact Information:**
For any questions or support regarding this project, please reach out to the developer at `bassantelsherif80@gmail.com`, `saniiiemaad55@gmail.com`, or `omarbassam813@gmail.com` .
