import java.io.File;
import java.io.FileWriter; 
import java.io.IOException; 
import java.util.Scanner;

class app {
    public static void main(String[] args) {
        
        Scanner sc = null;
        FileWriter validWriter = null;
        FileWriter invalidWriter = null;

        try {
            File inputFile = new File("data/emails.txt");
            
            File outputDir = new File("output");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }
            
            File validFile = new File("output/valid_emails.csv");
            File invalidFile = new File("output/invalid_emails.csv");
            sc = new Scanner(inputFile);
            
            validWriter = new FileWriter(validFile);
            invalidWriter = new FileWriter(invalidFile);

            System.out.println("Processing started...");

            while (sc.hasNextLine()) {
                String email = sc.nextLine();

                if (isValidEmail(email)) {
                    validWriter.write(email + "\n");
                } else {
                    invalidWriter.write(email + "\n");
                }
            }
            
            validWriter.close();
            invalidWriter.close();
            
            System.out.println("Processing complete! Check your folder.");

        } catch (IOException e) {
            System.out.println("An error occurred during File I/O: " + e.getMessage());
        } finally {
            try {
                if (sc != null) sc.close();
                if (validWriter != null) validWriter.close();
                if (invalidWriter != null) invalidWriter.close();
            } catch (IOException e) {
                System.out.println("Error closing files: " + e.getMessage());
            }
        }
    }
    
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
