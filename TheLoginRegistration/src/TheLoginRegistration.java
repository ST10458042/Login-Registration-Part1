import java.util.Scanner;

public class TheLoginRegistration {

    static String savedUsername = "";
    static String savedPassword = "";
    static String savedCell = "";

    // Validation methods
    public static boolean isValidUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public static boolean isValidPassword(String password) {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[0-9].*") &&
               password.matches(".*[!@#$%^&*()_+=<>?/{}\\[\\]-].*");
    }

    public static boolean isValidCell(String cell) {
        return cell.matches("^\\+27[0-9]{9}$");
    }

    // Register method
    public static String registerUser(String username, String password, String cell) {
        String message = "";

        if (isValidUsername(username)) {
            savedUsername = username;
            message += "✅ Username successfully recorded.\n";
        } else {
            message += "❌ Username must contain an underscore (_) and be no more than 5 characters.\n";
        }

        if (isValidPassword(password)) {
            savedPassword = password;
            message += "✅ Password successfully recorded.\n";
        } else {
            message += "❌ Password must be at least 8 characters and contain a capital letter, a number, and a special character.\n";
        }

        if (isValidCell(cell)) {
            savedCell = cell;
            message += "✅ Cell phone number successfully recorded.\n";
        } else {
            message += "❌ Cell number is incorrectly formatted. It must start with +27 and be followed by 9 digits.\n";
        }

        return message;
    }

    // Login verification
    public static boolean login(String username, String password) {
        return username.equals(savedUsername) && password.equals(savedPassword);
    }

    // Login status message
    public static String getLoginStatusMessage(String username, String password) {
        if (login(username, password)) {
            return "✅ Welcome " + username + "! You’ve successfully logged in.";
        } else {
            return "❌ Login failed. Username or password incorrect.";
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Registration loop
        boolean registered = false;
        while (!registered) {
            System.out.println("=== Registration ===");
            System.out.print("Enter a username: ");
            String username = input.nextLine();

            System.out.print("Enter a password: ");
            String password = input.nextLine();

            System.out.print("Enter your cell phone number (+27...): ");
            String cell = input.nextLine();

            String registrationResult = registerUser(username, password, cell);
            System.out.println("\n" + registrationResult);

            // Checking if fields are valid.
            if (isValidUsername(username) && isValidPassword(password) && isValidCell(cell)) {
                registered = true;
            } else {
                System.out.println("❗ Please try registering again.\n");
            }
        }

        // Login retry
        System.out.println("\n=== Login ===");

        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Enter your username: ");
            String loginUsername = input.nextLine();

            System.out.print("Enter your password: ");
            String loginPassword = input.nextLine();

            String loginMessage = getLoginStatusMessage(loginUsername, loginPassword);
            System.out.println(loginMessage);

            loggedIn = login(loginUsername, loginPassword);
        }

        input.close();
    }
}
