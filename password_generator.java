
import java.security.SecureRandom; // For generating cryptographically secure random numbers
import java.util.InputMismatchException; 
import java.util.Scanner; 

public class Main { 
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in); // prompt for user to choose which password generator they'd like
        System.out.println("Choose a password type: Press 1 for Numeric, and 2 for Alphabetic"); 
        PasswordGenerator passwordGenerator = null;
        
        try {
            int choice = scanner.nextInt(); 

            if (choice == 1) { 
                passwordGenerator = new IntegerPasswordGenerator();
            } else if (choice == 2) { 
                passwordGenerator = new AlphabeticPasswordGenerator(); 
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
                return; 
            }

            System.out.println("Enter the length of your password:"); 
            int length = scanner.nextInt(); 
            String password = passwordGenerator.generatePassword(length); 
            System.out.println("Generated password: " + password); 

        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter an integer."); 
        } finally {
            scanner.close(); 
        }
    }
}

abstract class PasswordGenerator {  //blueprint for both password generators 
    protected SecureRandom random; 

    public PasswordGenerator() { 
        this.random = new SecureRandom(); 
    }

    public abstract String generatePassword(int length); 

    public String generatePassword() { 
        return generatePassword(8); 
    }
}

class IntegerPasswordGenerator extends PasswordGenerator { 
 
    public String generatePassword(int length) { 
        StringBuilder password = new StringBuilder(length); 
        for (int i = 0; i < length; i++) { 
            password.append(random.nextInt(10)); 
        }
        return password.toString();
    }
}

class AlphabeticPasswordGenerator extends PasswordGenerator { 
  
    public String generatePassword(int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        StringBuilder password = new StringBuilder(length); 
        for (int i = 0; i < length; i++) { 
            int index = random.nextInt(alphabet.length()); 
            password.append(alphabet.charAt(index)); 
        }
        return password.toString(); 
    }
}
