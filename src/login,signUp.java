import java.io.*;
import java.util.*;

class User {
    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username; 
    }
    public String getPassword() {
        return password; 
    }
}

class UserService {
    private final File file = new File("users.txt");

    public boolean userExists(String username) {
        return getUser(username) != null;
    }

    public void createUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(user.getUsername() + "," + user.getPassword());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving user.");
        }
    }

    public User getUser(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    return new User(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        return null;
    }

    public String validateCredentials(String username, String password) {
        if (!isValidEmail(username)) {
            return "Invalid email.";
        }
        if (userExists(username)) {
            return "User already exists.";
        }
        if (!validatePassword(password)) {
            return "Weak password. It must contain at least 8 characters, one uppercase letter, one digit, and one special character.";
        }
        return null; 
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    private boolean validatePassword(String password) {
        if (password.length() < 8) return false;
        boolean hasUpper = false, hasNumber = false, hasSpecial = false;
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isDigit(ch)) hasNumber = true;
            else if (!Character.isLetterOrDigit(ch)) hasSpecial = true;
        }
        return hasUpper && hasNumber && hasSpecial;
    }
}

// <<control>>
class AuthenticationController {
    private final UserService service;

    public AuthenticationController(UserService service) {
        this.service = service;
    }

    public String register(String username, String password) {
        String validation = service.validateCredentials(username, password);
        if (validation != null) return validation;

        service.createUser(new User(username, password));
        return "Signup successful.";
    }

    public String login(String username, String password) {
        User user = service.getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            String name = username.split("@")[0];
            return "Welcome, " + name;
        }
        return "Invalid credentials.";
    }
}

class AuthenticationGUI {
    private final Scanner scanner = new Scanner(System.in);
    private final AuthenticationController controller;

    public AuthenticationGUI(AuthenticationController controller) {
        this.controller = controller;
    }

    public void startPage() {
        while (true) {
            System.out.println("1. Sign Up\n2. Login\n3. Exit");
            System.out.print("Your choice: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> signupFlow();
                case "2" -> loginFlow();
                case "3" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void signupFlow() {
        System.out.print("Enter username (email): ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        String result = controller.register(username, password);
        System.out.println(result);
    }

    private void loginFlow() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        String result = controller.login(username, password);
        System.out.println(result);
    }
}

public class Main {
    public static void main(String[] args) {
        UserService service = new UserService();
        AuthenticationController controller = new AuthenticationController(service);
        AuthenticationGUI gui = new AuthenticationGUI(controller);
        gui.startPage();
    }
}
