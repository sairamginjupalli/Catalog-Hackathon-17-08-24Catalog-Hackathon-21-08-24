import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {

    // Sample User Database
    private static final HashMap<String, User> usersDb = new HashMap<>();

    // User class to store password and PIN
    static class User {
        private final String password;
        private final String pin;

        User(String password, String pin) {
            this.password = password;
            this.pin = pin;
        }

        public String getPassword() {
            return password;
        }

        public String getPin() {
            return pin;
        }
    }

    // Function to generate a CAPTCHA
    private static String generateCaptcha() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            captcha.append(characters.charAt(random.nextInt(characters.length())));
        }
        return captcha.toString();
    }

    // Function to validate password
    private static boolean validatePassword(String username, String password) {
        return usersDb.containsKey(username) && usersDb.get(username).getPassword().equals(password);
    }

    // Function to validate PIN
    private static boolean validatePin(String username, String pin) {
        return usersDb.containsKey(username) && usersDb.get(username).getPin().equals(pin);
    }

    // Function to validate CAPTCHA
    private static boolean validateCaptcha(String userCaptcha, String actualCaptcha) {
        return userCaptcha.equals(actualCaptcha);
    }

    // Main function to authenticate user
    private static boolean authenticateUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Three-Level Authentication System");

        System.out.print("Enter your username: ");
        String username = scanner.nextLine().trim();

        if (!usersDb.containsKey(username)) {
            System.out.println("Invalid username.");
            return false;
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine().trim();
        if (!validatePassword(username, password)) {
            System.out.println("Incorrect password.");
            return false;
        }

        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine().trim();
        if (!validatePin(username, pin)) {
            System.out.println("Incorrect PIN.");
            return false;
        }

        String captcha = generateCaptcha();
        System.out.println("CAPTCHA: " + captcha);
        System.out.print("Enter the CAPTCHA: ");
        String userCaptcha = scanner.nextLine().trim();
        if (!validateCaptcha(userCaptcha, captcha)) {
            System.out.println("Incorrect CAPTCHA.");
            return false;
        }

        System.out.println("Authentication successful!");
        return true;
    }

    // Function to seed the user database
    private static void seedUserDatabase() {
        // Hashing passwords and PINs would be ideal for security
        usersDb.put("user1", new User("Password123!", "1234"));
        // Add more users as required
    }

    // Main function to run the system
    public static void main(String[] args) {
        seedUserDatabase();

        if (authenticateUser()) {
            System.out.println("Access Granted");
        } else {
            System.out.println("Access Denied");
        }
    }
}
