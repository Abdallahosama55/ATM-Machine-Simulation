import java.util.Scanner;

public class ATMMachine {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        final int CORRECT_PIN = 1234;
        int enteredPin;
        int attempts = 0;
        boolean loggedIn = false;

        double balance = 2500.75;
        int transactionCount = 0;

        // 1. PIN Authentication (While Loop)
        while (attempts < 3) {

            System.out.print("Enter your 4-digit PIN: ");
            enteredPin = input.nextInt();

            if (enteredPin == CORRECT_PIN) {
                loggedIn = true;
                System.out.println("Login successful!");
                break;
            } else {
                attempts++;
                System.out.println("Incorrect PIN. Attempts left: "
                        + (3 - attempts));
            }
        }

        if (!loggedIn) {
            System.out.println("Your account has been locked.");
            input.close();
            return;
        }

        // 2. ATM Menu (Do-While Loop)
        int choice;

        do {

            System.out.println("\\n========= ATM =========");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Show Account Status");
            System.out.println("5. Exit");
            System.out.println("=======================");
            System.out.print("Choose an option: ");

            choice = input.nextInt();

            switch (choice) {

                case 1:
                    // Check Balance
                    System.out.printf("Current Balance: %.2f%n", balance);
                    break;

                case 2:
                    // Deposit
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = input.nextDouble();

                    if (depositAmount > 0) {
                        balance += depositAmount;
                        transactionCount++;

                        System.out.printf("Deposit successful!%n");
                        System.out.printf("Updated Balance: %.2f%n", balance);

                    } else {
                        System.out.println("Invalid amount.");
                    }
                    break;

                case 3:
                    // Withdraw
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = input.nextDouble();

                    if (withdrawAmount == 0) {
                        System.out.println("Transaction cancelled.");

                    } else if (withdrawAmount < 0) {
                        System.out.println("Invalid amount.");

                    } else if (withdrawAmount > balance) {
                        System.out.println("Insufficient balance.");

                    } else {
                        balance -= withdrawAmount;
                        transactionCount++;

                        System.out.println("Withdrawal successful!");
                        System.out.printf("Updated Balance: %.2f%n", balance);

                        if (balance == 0) {
                            System.out.println(
                                    "Warning: Your account is empty.");
                        }
                    }
                    break;

                case 4:
                    // Show Account Status
                    if (balance >= 5000) {
                        System.out.println("VIP Customer");

                    } else if (balance >= 1000) {
                        System.out.println("Regular Customer");

                    } else {
                        System.out.println("Low Balance");
                    }
                    break;

                case 5:
                    // Exit
                    System.out.println("Thank you for using our ATM.");
                    System.out.println("Total successful transactions: "
                            + transactionCount);
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 5);

        input.close();
    }
}
