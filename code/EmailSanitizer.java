import java.io.File;
import java.io.FileWriter; // The Pen
import java.io.IOException; // The Error for writing
import java.util.Scanner;

class EmailSanitizer2 {
    public static void main(String[] args) {
        
        // We declare these OUTSIDE the try block so we can close them in 'finally'
        Scanner sc = null;
        FileWriter validWriter = null;
        FileWriter invalidWriter = null;

        try {
            File myFile = new File("emails.txt");
            sc = new Scanner(myFile);
            
            // 1. Initialize the Writers
            // "valid_emails.csv" is the filename.
            validWriter = new FileWriter("valid_emails.csv");
            invalidWriter = new FileWriter("invalid_emails.csv");

            System.out.println("Processing started...");

            while (sc.hasNextLine()) {
                String email = sc.nextLine();

                if (isValidEmail(email)) {
                    // 2. Write to valid file
                    // We add "\n" because FileWriter doesn't add new lines automatically
                    validWriter.write(email + "\n");
                } else {
                    // 3. Write to invalid file
                    invalidWriter.write(email + "\n");
                }
            }
            
            System.out.println("Processing complete! Check your folder.");

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // 4. THE CLEANUP PHASE
            // We must close resources manually to prevent memory leaks
            try {
                if (sc != null) sc.close();
                if (validWriter != null) validWriter.close();
                if (invalidWriter != null) invalidWriter.close();
            } catch (IOException e) {
                System.out.println("Error closing files: " + e.getMessage());
            }
        }
    }

    // ... [Keep your helper methods: isValidEmail, isAllowedChar, checkDomain here] ...
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        
        int atIndex = email.indexOf('@');
        
        if (atIndex < 1 || atIndex == email.length() - 1) {
            return false;
        }

        if (email.lastIndexOf('@') != atIndex) {
            return false;
        }

        if (email.startsWith(".") || email.endsWith(".")) {
            return false;
        }
        
        if (email.contains("..")) {
            return false;
        }
        
        if (!isAllowedChar(email))
        {
            return false;
        }
        
        if (!checkDomain(email))
        {
            return false;
        }

        return true;
    }
    
    public static boolean isAllowedChar(String email)
    {
        for (int i = 0; i < email.length(); i++) {
            char c = email.charAt(i);

            boolean isSpecial = (c == '.' || c == '_' || c == '-');
            boolean isAt = (c == '@');
            
            if ( Character.isWhitespace(c) ) {
                return false;
            }
            else if ( !Character.isLetterOrDigit(c) && !isSpecial && !isAt) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean checkDomain(String email)
    {
        String domainPart = email.substring(email.indexOf("@") + 1);
        if (domainPart.startsWith(".") || domainPart.endsWith(".")) {
            return false;
        }
        if (!domainPart.contains(".")) {
            return false;
        }
        return true;
    }
}