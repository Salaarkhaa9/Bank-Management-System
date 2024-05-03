import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class bank {
    public static void main(String[] args) {
        System.out.println(".........................................");
        System.out.println("********** WELCOME TO THE BANK MANAGEMENT SYSTEM **********");
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("********** DO YOU WANT TO ENTER BANK OR USE AN ATM? **********");
            System.out.println(
                    "********** \"PRESS 1 TO ENTER BANK\" **********\n********** \"PRESS 2 TO USE ATM SERVICES\" **********");
            try {
                int userChoice = sc.nextInt();
                switch (userChoice) {
                    case 1:
                        try {
                            System.out.println(".........................................");
                            System.out.println("********** ENTERING THE BANK **********");
                            for (int i = 3; i > 0; i--) {
                                System.out.println("PLEASE WAIT..." + i);
                                Thread.sleep(1000);
                            }
                            System.out.println(".........................................");
                            bank();
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                        break;
                    case 2:
                        try {
                            System.out.println("********** ENTERING THE ATM **********");
                            for (int i = 3; i > 0; i--) {
                                System.out.println("PLEASE WAIT..." + i);
                                Thread.sleep(1000);
                            }
                            atm();
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                        break;
                    default:
                        System.out.println("INVALID CHOICE");
                        break;
                }
                System.out.println(
                        "********** DO YOU WANT TO USE THE SYSTEM AGAIN?(PRESS 1 TO EXIT OR ANY KEY TO CONTINUE) **********");
                int continueChoice = sc.nextInt();
                if (continueChoice == 1) {
                    System.out.println("********** THANK YOU FOR USING THE BANK MANAGEMENT SYSTEM **********");
                    System.out.println(".........................................");
                    break;
                }
            } catch (InputMismatchException e) {
                
            }
        }
    }

    public static void bank() 
    {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(
                        "IF YOU ARE AN ADMIN;PLEASE PRESS 1,IF YOU ARE NEW USER;PLEASE PRESS 2 AND PROCEED TO ACCOUNT MAKING PROCESS");
                if (!sc.hasNextInt()) {
                    System.out.println("********** INVALID CHOICE. PLEASE ENTER 1 OR 2 **********");
                    sc.nextLine(); 
                    continue; 
                }
                int userChoice = sc.nextInt();
                if (userChoice == 1) {
                    adminBank();
                } else if (userChoice == 2) {
                    accountMaking();
                } else {
                    
                }
                System.out.println(
                        "********** DO YOU NEED ANY OTHER INFORMATION OR WANT TO CREATE AN ACCOUNT? (ENTER \"EXIT\" TO EXIT OR PRESS ANY OTHER KEY TO CONTINUE) **********");
                String continueChoice = sc.nextLine();
                if ((continueChoice.equalsIgnoreCase("exit"))) {
                    System.out.println("********** THANK YOU FOR USING OUR SERVICES! **********");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("********** PLEASE ENTER VALID CHOICES(1 OR 2) **********");
                break;
            }
        }
    }

public static void atm() {
    boolean exit = false;
    
    while (!exit) {
        try {
            Scanner input = new Scanner(System.in);
            File file = new File("AllAccountsDetails.txt");
            boolean accountFound = false;
            int maxPinAttempts = 3;
            int pinAttempts = 0;

            System.out.println("********** PLEASE INSERT YOUR CREDIT CARD (ENTER CC NUMBER) **********");
            
            if (input.hasNextInt()) {
                int enteredCreditCardNumber = input.nextInt();
                input.nextLine();
                Scanner reader = new Scanner(file);

                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    
                    if (line.contains("CREDIT CARD NUMBER: ")) {
                        String creditNum = line.substring(20).trim();
                        
                        if (!creditNum.isEmpty()) {
                            int cardNum = Integer.parseInt(creditNum);

                            if (enteredCreditCardNumber == cardNum) {
                                accountFound = true;
                                int storedPin = 0;

                                while (reader.hasNextLine()) {
                                    String pinLine = reader.nextLine();

                                    if (pinLine.startsWith("PIN: ")) {
                                        storedPin = Integer.parseInt(pinLine.substring(5).trim());
                                        break;
                                    }
                                }

                                while (pinAttempts < maxPinAttempts) 
                                {
                                    System.out.println("********** PLEASE ENTER YOUR PIN **********");
                                    int enteredPin = input.nextInt();
                                    input.nextLine();

                                    if (enteredPin == storedPin) {
                                        System.out.println("********** LOGIN SUCCESSFUL! **********");
                                        do {
                                            System.out.println("********** PRESS 1 TO CHECK BALANCE **********");
                                            System.out.println("********** PRESS 2 TO WITHDRAW CASH **********");
                                            System.out.println("********** PRESS 3 TO TRANSFER MONEY **********");
                                            System.out.println("********** PRESS 4 TO EXIT **********");
                                            int choice = input.nextInt();

                                            switch (choice) {
                                                case 1:
                                                    checkBalance(cardNum, enteredCreditCardNumber);
                                                    break;
                                                case 2:
                                                    withdrawCash(cardNum, enteredCreditCardNumber);
                                                    break;
                                                case 3:
                                                    transferMoney();
                                                    break;
                                                case 4:
                                                    exit = true;
                                                    System.out.println("********** EXITING ATM **********");
                                                    break;
                                                default:
                                                    System.out.println("********** INVALID CHOICE **********");
                                            }
                                        } while (!exit);

                                        break;
                                    } else {
                                        System.out.println("********** INCORRECT PIN. PLEASE TRY AGAIN ********** ");
                                        pinAttempts++;
                                    }
                                }

                                if (pinAttempts == maxPinAttempts) {
                                    System.out.println("********** INCORRECT PIN ENTERED 3 TIMES. YOU CANNOT USE THE ATM NOW. PLEASE TRY AGAIN LATER **********");
                                    try {
                                        System.out.println(".........................................");
                                        System.out.println("TRY AGAIN IN...");
                                        for (int i = 10; i > 0; i--) {
                                            System.out.println(" " + i + " sec");
                                            
                                            Thread.sleep(1000);
                                        }
                                        System.out.println("\n.........................................");
                                    } catch (InterruptedException e) {
                                        System.out.println(e);
                                    }
                                    pinAttempts = 0; // Reset pinAttempts after the delay
                                }
                            }
                        }
                    }
                }

                if (!accountFound) {
                    System.out.println("********** PLEASE ENTER A VALID CC NUMBER **********");
                }
            } else {
                System.out.println("********** INVALID INPUT **********");
                input.nextLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

    public static void checkBalance(int cardNum, int enteredCreditCardNumber) {
        String fileString = "AllAccountsDetails.txt";
        ArrayList<String> al = new ArrayList<>();
        ArrayList<String> al1 = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(fileString))) {
            while (reader.hasNextLine()) {
                al.add(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found! " + e.getMessage());
            return;
        }
        for (int i = 0; i < al.size(); i++) {
            String line = al.get(i);
            if (line.startsWith("CREDIT CARD NUMBER: ")) {
                String numericPart = line.substring(20).trim();
                cardNum = Integer.parseInt(numericPart);
                if (cardNum == enteredCreditCardNumber) {
                    String balanceLine = al.get(i - 1);
                    String balance = balanceLine.substring(9).trim();
                    al1.clear();
                    al1.add(balance);
                }

            }
        }
        for (String st : al1) {
            System.out.println("********** THE BALANCE IS $" + st + " **********");
        }

    }

    public static void withdrawCash(int cardNum, int enteredCreditCardNumber) {
        String fileString = "AllAccountsDetails.txt";
        Scanner sc = new Scanner(System.in);
        ArrayList<String> al = new ArrayList<>();
        ArrayList<String> al1 = new ArrayList<>();

        try (Scanner reader = new Scanner(new File(fileString))) {
            while (reader.hasNextLine()) {
                al.add(reader.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        System.out.println("********** ENTER AMOUNT TO WITHDRAW **********");
        int withdrawAmt = sc.nextInt();

        for (int i = 0; i < al.size(); i++) {
            String line = al.get(i);
            if (line.startsWith("CREDIT CARD NUMBER: ")) {
                String numericPart = line.substring(20).trim();
                cardNum = Integer.parseInt(numericPart);
                if (cardNum == enteredCreditCardNumber) {
                    String balanceLine = al.get(i - 1);
                    String balanceStr = balanceLine.substring(9).trim();
                    int balance = Integer.parseInt(balanceStr);

                    while (true) {
                        if (withdrawAmt <= balance) {
                            balance -= withdrawAmt;
                            String newBalance = Integer.toString(balance);
                            al1.clear();
                            al1.add(newBalance);
                            al.set(i - 1, "BALANCE: " + newBalance); // Update balance in the ArrayList
                            break;
                        } else {
                            System.out.println(
                                    "********** INSUFFICIENT BALANCE. PLEASE ENTER WITHDRAWAL AMOUNT AGAIN **********");
                            withdrawAmt = sc.nextInt();
                        }
                    }
                }
            }
        }

        try (PrintWriter printWriter = new PrintWriter(new FileWriter("AllAccountsDetails.txt"))) {
            for (String st : al) {
                printWriter.println(st);
            }
            System.out.println("********** THE NEW BALANCE IS $" + al1.get(0) + " **********");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
    }

    public static void transferMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.println("********** ENTER SENDER ACCOUNT NUMBER **********");
        int senderAccNumber = sc.nextInt();
        System.out.println("********** ENTER RECEIVER ACCOUNT NUMBER **********");
        int receiverAccNumber = sc.nextInt();

        String fileString = "AllAccountsDetails.txt";
        ArrayList<String> al = new ArrayList<>();
        ArrayList<String> al1 = new ArrayList<>();
        boolean senderFound = false;
        boolean receiverFound = false;

        try (Scanner reader = new Scanner(new File(fileString))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                al.add(line);

                if (line.contains("ACCOUNT NUMBER: ")) {
                    String numericPart = line.substring(16).trim();
                    if (!numericPart.isEmpty()) {
                        int accountNumber = Integer.parseInt(numericPart);
                        if (senderAccNumber == accountNumber) {
                            senderFound = true;
                        } else if (receiverAccNumber == accountNumber) {
                            receiverFound = true;
                        }
                    }
                }
            }
            if (!senderFound || !receiverFound) {
                System.out.println("********** INVALID ACCOUNT NUMBER(S) ENTERED **********");
                return;
            }
            System.out.println("********** ENTER AMOUNT TO TRANSFER **********");
            int transferAmt = sc.nextInt();
            int senderBalance = getBalance(senderAccNumber, al);
            if (transferAmt <= senderBalance) {
                int receiverBalance = getBalance(receiverAccNumber, al);
                updateBalance(senderAccNumber, al, senderBalance - transferAmt);
                updateBalance(receiverAccNumber, al, receiverBalance + transferAmt);
                saveToFile(fileString, al);
                System.out.println("********** TRANSFER SUCCESSFUL **********");
            } else {
                System.out.println("********** INSUFFICIENT BALANCE FOR TRANSFER **********");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getBalance(int accountNumber, ArrayList<String> al) {
        for (int i = 0; i < al.size(); i++) {
            String line = al.get(i);
            if (line.contains("ACCOUNT NUMBER: " + accountNumber)) {
                String balanceLine = al.get(i + 6);
                String balanceStr = balanceLine.substring(9).trim();
                return Integer.parseInt(balanceStr);
            }
        }
        return 0;
    }

    public static void updateBalance(int accountNumber, ArrayList<String> al, int newBalance) {
        for (int i = 0; i < al.size(); i++) {
            String line = al.get(i);
            if (line.contains("ACCOUNT NUMBER: " + accountNumber)) {
                al.set(i + 6, "BALANCE: " + newBalance);
                break;
            }
        }
    }

    public static void saveToFile(String fileString, ArrayList<String> al) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(fileString))) {
            for (String st : al) {
                printWriter.println(st);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void adminBank() {
        Scanner sc = new Scanner(System.in);
        File file = new File("AllAccountsDetails.txt");
        while (true) {
            System.out.println(".........................................");
            System.out.println("********** PRESS 1 TO GET ACCOUNT DETAILS **********");
            System.out.println("********** PRESS 2 TO SEE TOTAL CASH IN BANK **********");
            System.out.println("********** PRESS 3 TO SEE THE DETAILS OF USER/S WITH MAXIMUM BALANCE ********** ");
            System.out.println("********** PRESS 4 TO SEE THE DETAILS OF USER/S WITH MINIMUM BALANCE ********** ");
            System.out.println(
                    "********** PRESS 5 TO SEARCH FOR SPECIFIC ACCOUNT DETAILS(BY ACCOUNT NUMBER) ********** ");
            System.out.println("********** PRESS 6 TO CHANGE THE PIN ********** ");

            System.out.println(".........................................");
            switch (sc.nextInt()) {
                case 1:
                    try {
                        Scanner input = new Scanner(file);
                        while (input.hasNextLine()) {
                            String accountNumber = input.nextLine();
                            String firstName = input.nextLine();
                            String lastName = input.nextLine();
                            String emailAddress = input.nextLine();
                            String phoneNumber = input.nextLine();
                            String accountType = input.nextLine();
                            String balance = input.nextLine();
                            String creditCardNumber = input.nextLine();
                            String pin = input.nextLine();
                            System.out.println(accountNumber);
                            System.out.println(firstName);
                            System.out.println(lastName);
                            System.out.println(emailAddress);
                            System.out.println(phoneNumber);
                            System.out.println(accountType);
                            System.out.println(balance);
                            System.out.println(creditCardNumber);
                            System.out.println(pin);
                            System.out.println(".........................................");
                            if (input.nextLine() == null) {
                                break;
                            }
                        }
                        input.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    String fileName = "AllAccountsDetails.txt";
                    int totalBalance = 0;
                    try {
                        Scanner Reader = new Scanner(new File(fileName));
                        while (Reader.hasNextLine()) {
                            String line = Reader.nextLine();
                            if (line.contains("BALANCE:")) {
                                String numericPart = (line.replaceAll("[^0-9]", ""));
                                if (!numericPart.isEmpty()) {
                                    int balance = Integer.parseInt(numericPart);
                                    totalBalance += balance;
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("File not found: " + e.getMessage());
                    }
                    System.out.println("********** TOTAL BALANCE IN THE BANK **********\n" + totalBalance);
                    break;
                case 3:
                    String filename = "AllAccountsDetails.txt";
                    ArrayList<String> al = new ArrayList<>();
                    ArrayList<String> al1 = new ArrayList<>();
                    try (Scanner reader = new Scanner(new File(filename))) {
                        while (reader.hasNextLine()) {
                            al.add(reader.nextLine());
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found! " + e.getMessage());
                        return;
                    }
                    int maxBalance = Integer.MIN_VALUE;
                    for (int i = 0; i < al.size(); i++) {
                        String line = al.get(i);
                        if (line.startsWith("BALANCE: ")) {
                            String numericPart = line.substring(9).trim();
                            int balance = Integer.parseInt(numericPart);
                            if (balance > maxBalance) {
                                maxBalance = balance;
                                al1.clear();
                                al1.add(al.get(i - 6));
                                al1.add(al.get(i - 5));
                                al1.add(al.get(i - 4));
                                al1.add(al.get(i - 3));
                                al1.add(al.get(i - 2));
                                al1.add(al.get(i - 1));
                                al1.add(al.get(i));
                                al1.add(al.get(i + 1));
                                al1.add(al.get(i + 2));
                            } else if (balance == maxBalance) {
                                al1.clear();
                                al1.add(al.get(i - 6));
                                al1.add(al.get(i - 5));
                                al1.add(al.get(i - 4));
                                al1.add(al.get(i - 3));
                                al1.add(al.get(i - 2));
                                al1.add(al.get(i - 1));
                                al1.add(al.get(i));
                                al1.add(al.get(i + 1));
                                al1.add(al.get(i + 2));
                            }
                        }
                    }
                    System.out
                            .println("********** THE DETAILS OF THE ACCOUNT/s WITH THE MAXIMUM BALANCE ARE **********");
                    for (String st : al1) {
                        System.out.println(st);
                    }
                    break;
                case 4:
                    String fileName1 = "AllAccountsDetails.txt";
                    al = new ArrayList<>();
                    al1 = new ArrayList<>();
                    try (Scanner reader = new Scanner(new File(fileName1))) {
                        while (reader.hasNextLine()) {
                            al.add(reader.nextLine());
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found! " + e.getMessage());
                        return;
                    }
                    int minBalance = Integer.MAX_VALUE;
                    for (int i = 0; i < al.size(); i++) {
                        String line = al.get(i);
                        if (line.startsWith("BALANCE: ")) {
                            String numericPart = line.substring(9).trim();
                            int balance = Integer.parseInt(numericPart);
                            if (balance < minBalance) {
                                minBalance = balance;
                                al1.clear();
                                al1.add(al.get(i - 6));
                                al1.add(al.get(i - 5));
                                al1.add(al.get(i - 4));
                                al1.add(al.get(i - 3));
                                al1.add(al.get(i - 2));
                                al1.add(al.get(i - 1));
                                al1.add(al.get(i));
                                al1.add(al.get(i + 1));
                                al1.add(al.get(i + 2));
                            } else if (balance == minBalance) {
                                al1.clear();
                                al1.add(al.get(i - 6));
                                al1.add(al.get(i - 5));
                                al1.add(al.get(i - 4));
                                al1.add(al.get(i - 3));
                                al1.add(al.get(i - 2));
                                al1.add(al.get(i - 1));
                                al1.add(al.get(i));
                                al1.add(al.get(i + 1));
                                al1.add(al.get(i + 2));
                            }
                        }
                    }
                    System.out
                            .println("********** THE DETAILS OF THE ACCOUNT/s WITH THE MINIMUM BALANCE ARE **********");
                    for (String st : al1) {
                        System.out.println(st);
                    }
                    break;
                case 5:
                    System.out.println("********** ENTER THE ACCOUNT NUMBER TO CHECK DETAILS **********");
                    int accNumber = sc.nextInt();
                    String fil = "AllAccountsDetails.txt";
                    boolean accountFound = false;
                    try {
                        Scanner reader = new Scanner(new File(fil));
                        while (reader.hasNextLine()) {
                            String line = reader.nextLine();
                            if (line.contains("ACCOUNT NUMBER:")) {
                                String numericPart = (line.replaceAll("[^0-9]", ""));
                                if (!numericPart.isEmpty()) {
                                    int accountNumber = Integer.parseInt(numericPart);
                                    if (accNumber == accountNumber) {
                                        System.out.println("********** ACCOUNT DETAILS **********");
                                        System.out.println("ACCOUNT NUMBER: " + accountNumber);
                                        System.out.println(reader.nextLine());
                                        System.out.println(reader.nextLine());
                                        System.out.println(reader.nextLine());
                                        System.out.println(reader.nextLine());
                                        System.out.println(reader.nextLine());
                                        System.out.println(reader.nextLine());
                                        System.out.println(reader.nextLine());
                                        System.out.println(reader.nextLine());
                                        System.out.println(".........................................");
                                        accountFound = true;
                                        break;
                                    }
                                }
                            }
                        }
                        if (!accountFound) {
                            System.out.println("********** THIS ACCOUNT DOES NOT EXIST **********");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    System.out.println("********** ENTER THE ACCOUNT NUMBER TO UPDATE PIN **********");
                    int accountNumberToUpdatePin = sc.nextInt();
                    while (!isAccountExists(accountNumberToUpdatePin)) {
                        System.out.println(
                                "********** ACCOUNT DOES NOT EXIST!PLEASE ENTER VALID ACCOUNT NUMBER **********");
                        accountNumberToUpdatePin = sc.nextInt();
                    }
                    System.out.println("********** ENTER NEW 4-DIGIT PIN **********");
                    int newPin = sc.nextInt();
                    while (newPin < 1000 || newPin > 9999) {
                        System.out.println("********** PIN MUST BE A 4-DIGIT NUMBER **********");
                        newPin = sc.nextInt();
                    }
                    System.out.println("********** CONFIRM NEW PIN **********");
                    int confirmPin = sc.nextInt();
                    while (newPin != confirmPin) {
                        System.out.println("********** PIN AND CONFIRMATION DO NOT MATCH **********");
                        confirmPin = sc.nextInt();
                    }
                    updatePinInFile(accountNumberToUpdatePin, newPin);
                    System.out.println("********** PIN UPDATED SUCCESSFULLY! **********");
                    System.out.println(".........................................");
                    break;
                default:

            }
            System.out.println(
                    "********** DO YOU WANT TO SEE MORE DETAILS?(PRESS 1 TO EXIT OR ANY KEY TO CONTINUE) **********");
            int continueChoice = sc.nextInt();
            if (continueChoice == 1) {
                System.out.println(".........................................");
                break;
            }
            sc.close();
        }
    }

    public static boolean isAccountExists(int accountNumberToCheck) {
        String fileString = "AllAccountsDetails.txt";
        try (Scanner reader = new Scanner(new File(fileString))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.startsWith("ACCOUNT NUMBER: ")) {
                    String numericPart = line.substring(16).trim();
                    int currentAccountNumber = Integer.parseInt(numericPart);
                    if (currentAccountNumber == accountNumberToCheck) {
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("********** FILE NOT FOUND! **********");
        }
        return false;
    }

    public static void updatePinInFile(int accountNumber, int newPin) {
        String fileString = "AllAccountsDetails.txt";
        ArrayList<String> al = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(fileString))) {
            while (reader.hasNextLine()) {
                al.add(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("********** FILE NOT FOUND! **********");
            return;
        }
        for (int i = 0; i < al.size(); i++) {
            String line = al.get(i);
            if (line.startsWith("ACCOUNT NUMBER: ")) {
                String numericPart = line.substring(16).trim();
                int currentAccountNumber = Integer.parseInt(numericPart);
                if (currentAccountNumber == accountNumber) {
                    while (i < al.size() && !al.get(i).startsWith("PIN: ")) {
                        i++;
                    }
                    al.set(i, "PIN: " + newPin);
                    break;
                }
            }
        }
        try (PrintWriter writer = new PrintWriter(fileString)) {
            for (String st : al) {
                writer.println(st);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error updating PIN in the file: " + ex.getMessage());
        }
    }

    public static void accountMaking() {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        int balance = 0;
        String fileName = "AllAccountsDetails.txt";
        System.out.println("********** PLEASE HAVE A SEAT AND ENTER THE FOLLOWING FIELDS **********");
        try {
            FileOutputStream fos = new FileOutputStream(fileName, true);
            PrintStream ps = new PrintStream(fos);
            System.out.print("FIRST NAME: ");
            String firstName = sc.nextLine();
            System.out.print("LAST NAME: ");
            String lastName = sc.nextLine();
            System.out.print("EMAIL ADDRESS: ");
            String email = sc.nextLine();
            System.out.print("PHONE NUMBER: ");
            int phoneNumber = sc.nextInt();
            int accountNumber = 0;
            int creditCardNumber = 0;
            int pin = 0;
            String accountType = "";
            System.out.println(
                    "********** DO YOU WANT TO OPEN A SAVINGS OR A CURRENT ACCOUNT? (ENTER \"SAVINGS\" FOR SAVINGS OR \"CURRENT\" FOR CURRENT )**********");
            System.out.println(
                    "********** FOLLOWING IS THE DETAILS OF THESE TWO TYPES OF ACCOUNTS **********\n SAVINGS ACCOUNT:- YOU WILL RECEIVE 4% INTEREST ON THE PRESENT BALANCE BUT YOU CANNOT GET THE OVERDRAFT FACILITY \n CURRENT ACCOUNT:- YOU WILL NOT GET ANY INTEREST BUT YOU CAN AVAIL THE OVERDRAFT FACILITY");
            accountType = sc.nextLine();
            while (true) {
                if (accountType.equalsIgnoreCase("savings")) {
                    System.out.print("BALANCE: ");
                    balance = sc.nextInt();
                    accountNumber = rd.nextInt(100000, 999999);
                    creditCardNumber = 2 * accountNumber;
                    while (accountNumberExists(accountNumber, fileName)) {
                        accountNumber = rd.nextInt(100000, 999999);
                    }
                    break;
                } else if (accountType.equalsIgnoreCase("current")) {
                    System.out.print("BALANCE:");
                    balance = sc.nextInt();
                    accountNumber = rd.nextInt(100000, 999999);
                    creditCardNumber = 2 * accountNumber;
                    while (accountNumberExists(accountNumber, fileName)) {
                        accountNumber = rd.nextInt(100000, 999999);
                    }
                    break;
                } else {
                    System.out
                            .println("********** PLEASE ENTER A VALID OPTION (\"SAVINGS\" OR \"CURRENT\") **********");
                    accountType = sc.nextLine();
                }
                System.out.println("********** PLEASE ENTER A 4-DIGIT INTEGER NUMBER PIN **********");
                while (true) {
                    if (sc.hasNextInt()) {
                        pin = sc.nextInt();
                        sc.nextLine();
                        if (pin >= 1000 && pin <= 9999) {
                            break;
                        } else {
                            System.out.println("INVALID PIN. PLEASE ENTER A 4-DIGIT NUMBER");
                        }
                    } else {
                        System.out.println("INVALID INPUT");
                        sc.nextLine();
                    }
                }
            }
            System.out.println("********** YOUR ACCOUNT IS SUCCESSFULLY CREATED! **********");
            System.out.println(".........................................");
            System.out.println("ACCOUNT NUMBER: " + accountNumber);
            System.out.println("CREDIT CARD NUMBER: " + creditCardNumber);
            System.out.println("PIN: " + pin);
            ps.println("ACCOUNT NUMBER: " + accountNumber);
            ps.println("FIRST NAME: " + firstName);
            ps.println("LAST NAME: " + lastName);
            ps.println("EMAIL ADDRESS: " + email);
            ps.println("PHONE NUMBER: " + phoneNumber);
            ps.println("ACCOUNT TYPE: " + accountType);
            ps.println("BALANCE: " + balance);
            ps.println("CREDIT CARD NUMBER: " + creditCardNumber);
            ps.println("PIN: " + pin);
            ps.println();

        } catch (IOException e) {
            System.out.println("FILE NOT FOUND");
        }

    }

    public static boolean accountNumberExists(int targetAccountNumber, String fileName) {
        try (Scanner reader = new Scanner(fileName)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                int number = reader.nextInt();
                if (line.contains("ACCOUNT NUMBER: " + targetAccountNumber)) {
                    return true;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("THERE IS OTHER THAN INTEGER TYPE DATA IN THE FILE");
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
}
