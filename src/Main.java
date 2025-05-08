import java.util.Scanner;
import java.io.*;
 class Main {
    public static void main(String[] args) {
        while(true) {
            System.out.println("--------welcome--------");
            System.out.println("1-sign up");
            System.out.println("2-login");
            System.out.println("3-exit");
            System.out.println("Enter your choice:");
            Scanner choice = new Scanner(System.in);
            int choiceInt = choice.nextInt();
            choice.nextLine();
            switch (choiceInt) {
                case 1:
                    signUp(choice);
                    break;
                case 2:
                    login(choice);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");


            }

        }


    }
     public static void signUp(Scanner choice)  {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your name:");
            String name = input.nextLine();
            System.out.print("Enter username: ");
            String username = input.nextLine();
            System.out.print("Enter password: ");
            String password = input.nextLine();
            if (!username.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                System.out.println("Please enter a valid email address.");
                continue;
            }
            if(userExist(username)){
                System.out.println("Username already exists");
                continue;

            }
            if (!isStrongPassword(password)) {
                System.out.println("Password must be at least 8 characters, include one uppercase letter, one number, and one special character.");
                continue;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
                writer.write(username + "," + password);
                writer.newLine();
                System.out.println("Signed up successfully! Redirecting to login...");
                break;
            } catch (IOException e) {
                System.out.println("Error writing to file.");
            }


        }
     }
     public static void login(Scanner choice) {
         Scanner input = new Scanner(System.in);


             System.out.print("Enter username: ");
             String username = input.nextLine();
             System.out.print("Enter password: ");
             String password = input.nextLine();
             try(BufferedReader file=new BufferedReader(new FileReader("users.txt"))){
                 String line;
                 while ((line= file.readLine())!=null){
                     String[] parts = line.split(",");
                     String[] parts2 = parts[0].split("@");
                     if(username.equals(parts[0])&&password.equals(parts[1])){
                         System.out.println("Logged in successfully,Welcome "+parts2[0]);
                         return;
                     }

                 }
                 System.out.println("Invalid username or password");
             }catch (IOException e) {
                 System.out.println("Error writing to file.");
             }



     }
     public static  boolean userExist(String username) {
        try(BufferedReader file = new BufferedReader(new FileReader("users.txt"))){
            String line;
            while((line= file.readLine())!=null){
                String[] parts = line.split(",");
                if(parts[0].equals(username)){
                    return true;
                }
                else{
                return false;
                }
            }



        }catch (IOException e) {
            System.out.println("Error writing to file.");
        }
        return false;

     }
     public static boolean isStrongPassword(String password) {
         if (password.length() < 8) return false;

         boolean hasUpper = false;
         boolean hasNumber = false;
         boolean hasSpecial = false;

         for (char ch : password.toCharArray()) {
             if (Character.isUpperCase(ch)) hasUpper = true;
             else if (Character.isDigit(ch)) hasNumber = true;
             else if (!Character.isLetterOrDigit(ch)) hasSpecial = true;
         }

         return hasUpper && hasNumber && hasSpecial;
     }
}



// edit from Zeinab 20230165

public class Calculation {

    // Method for addition
    public double add(double a, double b) {
        return a + b;
    }

    // Method for subtraction
    public double subtract(double a, double b) {
        return a - b;
    }

    // Method for multiplication
    public double multiply(double a, double b) {
        return a * b;
    }

    // Method for division
    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero.");
        }
        return a / b;
    }

    public static void main(String[] args) {
        Calculation calc = new Calculation();

        System.out.println("Addition: " + calc.add(10, 5));
        System.out.println("Subtraction: " + calc.subtract(10, 5));
        System.out.println("Multiplication: " + calc.multiply(10, 5));
        System.out.println("Division: " + calc.divide(10, 5));
    }
}
