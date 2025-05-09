import java.io.*;
import java.util.*;
class User {
    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}

class FileUserRepository {
    private final File file = new File("users.txt");

    public boolean userExists(String username) {
        return getUser(username) != null;
    }

    public void saveUser(User user) {
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
}

class UserService {
    private final FileUserRepository repository = new FileUserRepository();
    private final Scanner scanner = new Scanner(System.in);

    public boolean isStrongPassword(String password) {
        if (password.length() < 8) return false;
        boolean hasUpper = false, hasNumber = false, hasSpecial = false;
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isDigit(ch)) hasNumber = true;
            else if (!Character.isLetterOrDigit(ch)) hasSpecial = true;
        }
        return hasUpper && hasNumber && hasSpecial;
    }

    public void register() {
        while (true) {
            System.out.print("Enter username (email): ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (!username.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                System.out.println("Invalid email.");
                continue;
            }
            if (repository.userExists(username)) {
                System.out.println("User already exists.");
                continue;
            }
            if (!isStrongPassword(password)) {
                System.out.println("Weak password.");
                continue;
            }
            repository.saveUser(new User(username, password));
            System.out.println("Signup successful.");
            break;
        }
    }

    public void login() {
        while (true) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            User user = repository.getUser(username);
            if (user != null && user.getPassword().equals(password)) {
                String name = username.split("@")[0];
                System.out.println("Welcome, " + name);
                break;
            }
            System.out.println("Invalid credentials. Try again.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService service = new UserService();

        while (true) {
            System.out.println("1. Sign Up\n2. Login\n3. Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 : service.register();
                case 2 : service.login();
                case 3 :
                    System.out.println("Goodbye!");
                    return;

                default : System.out.println("Invalid choice.");
            }
        }
    }
}
