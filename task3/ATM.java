import java.util.Scanner;

public class ATM {

    private static final String BANK_NAME = "[YOUR BANK NAME]";
    private static Scanner scanner = new Scanner(System.in);
    private static String cardNumber = ""; // Placeholder for card number
    private static String pin = ""; // Placeholder for PIN
    private static double balance = 10000.00; // Initial balance for demonstration

    public static void main(String[] args) 
       {
        displayWelcomeScreen();
        insertCard();
        enterPin();
        if (authenticateUser()) 
          {
            mainMenu();
          } else 
            {
            System.out.println("Incorrect PIN. Card blocked. Please contact your bank.");
            }
        displayGoodbyeScreen();
          }

    private static void displayWelcomeScreen() {
        System.out.println("\n-------------------------------------");
        System.out.println("     WELCOME TO " + BANK_NAME);
        System.out.println("     Please Insert Your Card");
        System.out.println("-------------------------------------");
        // Simulate a delay for card insertion
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void insertCard() {
        System.out.print("Simulating card insertion... (Press Enter to continue)");
        scanner.nextLine(); // Consume the newline after initial prompt
        System.out.print("Enter Card Number: ");
        cardNumber = scanner.nextLine();
        System.out.println("Card inserted successfully!");
    }

    private static void enterPin() {
        System.out.println("\n-------------------------------------");
        System.out.println("     WELCOME [CARDHOLDER NAME]"); // In a real system, this would be fetched
        System.out.print("     Please Enter Your 4-Digit PIN: ");
        pin = scanner.nextLine();
        System.out.println("-------------------------------------");
    }

    private static boolean authenticateUser() {
        // In a real system, this would involve checking against a database
        // For this demo, let's assume the correct PIN is "1234"
        return pin.equals("1234");
    }

    private static void mainMenu() {
        int choice;
        do {
            System.out.println("\n-------------------------------------");
            System.out.println("             MAIN MENU");
            System.out.println("     Please Select a Transaction:");
            System.out.println("     1. Withdrawal");
            System.out.println("     2. Fast Cash");
            System.out.println("     3. Balance Inquiry");
            System.out.println("     4. Deposit");
            System.out.println("     5. Fund Transfer");
            System.out.println("     6. Mini Statement");
            System.out.println("     7. PIN Change");
            System.out.println("     8. Other Services");
            System.out.println("     9. Exit/Cancel"); // Added an exit option
            System.out.println("-------------------------------------");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = 0; // Set to an invalid choice to re-display menu
                continue;
            }

            switch (choice) {
                case 1:
                    withdrawal();
                    break;
                case 2:
                    fastCash();
                    break;
                case 3:
                    balanceInquiry();
                    break;
                case 4:
                    deposit();
                    break;
                case 5:
                    fundTransfer();
                    break;
                case 6:
                    miniStatement();
                    break;
                case 7:
                    pinChange();
                    break;
                case 8:
                    otherServices();
                    break;
                case 9:
                    System.out.println("Exiting ATM. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 9);
    }

    private static void withdrawal() {
        int amountChoice;
        double withdrawAmount = 0;

        System.out.println("\n-------------------------------------");
        System.out.println("             WITHDRAWAL");
        System.out.println("     Please Select Amount:");
        System.out.println("     1. ₹500");
        System.out.println("     2. ₹1,000");
        System.out.println("     3. ₹2,000");
        System.out.println("     4. ₹5,000");
        System.out.println("     5. ₹10,000");
        System.out.println("     6. Other Amount");
        System.out.println("     7. Back to Main Menu");
        System.out.println("-------------------------------------");
        System.out.print("Enter your choice: ");

        try {
            amountChoice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        switch (amountChoice) {
            case 1: withdrawAmount = 500; break;
            case 2: withdrawAmount = 1000; break;
            case 3: withdrawAmount = 2000; break;
            case 4: withdrawAmount = 5000; break;
            case 5: withdrawAmount = 10000; break;
            case 6:
                System.out.println("\n-------------------------------------");
                System.out.println("     WITHDRAWAL - OTHER AMOUNT");
                System.out.print("     Enter Amount (Multiples of ₹100): ₹");
                try {
                    withdrawAmount = Double.parseDouble(scanner.nextLine());
                    if (withdrawAmount % 100 != 0 || withdrawAmount <= 0) {
                        System.out.println("Invalid amount. Please enter a positive multiple of ₹100.");
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    return;
                }
                System.out.println("-------------------------------------");
                break;
            case 7: return; // Go back to main menu
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }

        if (balance >= withdrawAmount) {
            balance -= withdrawAmount;
            System.out.printf("\nTransaction Successful! Please take your cash: ₹%.2f\n", withdrawAmount);
            System.out.printf("Your new balance is: ₹%.2f\n", balance);
            askForReceipt();
        } else {
            System.out.println("Insufficient balance. Your current balance is: ₹" + balance);
        }
    }

    private static void fastCash() {
        System.out.println("\nFast Cash is not implemented in this demo.");
        // In a real system, this would offer fixed, common withdrawal amounts
    }

    private static void balanceInquiry() {
        System.out.println("\n-------------------------------------");
        System.out.println("         BALANCE INQUIRY");
        System.out.printf("     Your current balance is: ₹%.2f\n", balance);
        System.out.println("-------------------------------------");
        askForReceipt();
    }

    private static void deposit() {
        System.out.println("\n-------------------------------------");
        System.out.println("             DEPOSIT");
        System.out.print("     Enter amount to deposit: ₹");
        try {
            double depositAmount = Double.parseDouble(scanner.nextLine());
            if (depositAmount > 0) {
                balance += depositAmount;
                System.out.printf("Deposit successful! Your new balance is: ₹%.2f\n", balance);
                askForReceipt();
            } else {
                System.out.println("Deposit amount must be positive.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
        System.out.println("-------------------------------------");
    }

    private static void fundTransfer() {
        System.out.println("\nFund Transfer is not implemented in this demo.");
        // This would involve entering recipient account details and amount
    }

    private static void miniStatement() {
        System.out.println("\nMini Statement is not implemented in this demo.");
        // This would show recent transactions
    }

    private static void pinChange() {
        System.out.println("\nPIN Change is not implemented in this demo.");
        // This would involve verifying old PIN and setting a new one
    }

    private static void otherServices() {
        System.out.println("\nOther Services are not implemented in this demo.");
        // This could lead to options like bill payments, mobile recharge, etc.
    }

    private static void askForReceipt() {
        System.out.print("\nWould you like a receipt? (yes/no): ");
        String receiptChoice = scanner.nextLine().trim().toLowerCase();
        if (receiptChoice.equals("yes")) {
            System.out.println("Printing receipt...");
        } else {
            System.out.println("No receipt requested.");
        }
    }

    private static void displayGoodbyeScreen() {
        System.out.println("\n-------------------------------------");
        System.out.println("     THANK YOU FOR BANKING WITH US!");
        System.out.println("     Please Take Your Card.");
        System.out.println("-------------------------------------");
        scanner.close();
    }
}