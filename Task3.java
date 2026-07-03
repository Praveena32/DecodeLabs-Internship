import java.util.Scanner;

// ==========================================
// 1. BANK ACCOUNT CLASS (Business Logic)
// ==========================================
class BankAccount {
    private final String accountNumber;
    private double balance;
 
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = Math.max(initialBalance, 0.0);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited: $%.2f%n", amount);
        } else {
            System.out.println("Invalid deposit amount. Amount must be positive.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount. Amount must be positive.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Transaction Declined: Insufficient funds.");
            return false;
        }
        
        balance -= amount;
        System.out.printf("Successfully withdrew: $%.2f%n", amount);
        return true;
    }
}

// ==========================================
// 2. ATM CLASS (User Interface/Controller)
// ==========================================
class ATM {
    private final BankAccount account;
    private final Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        System.out.println("=== Welcome to the Java OOP Bank ATM ===");
        System.out.println("Account Verified: " + account.getAccountNumber());

        while (running) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    handleWithdrawal();
                    break;
                case 4:
                    System.out.println("\nThank you for using our ATM. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select options 1-4.");
                    break;
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n----------------------------");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
        System.out.print("Please choose an option: ");
    }

    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); 
            System.out.print("Please choose an option: ");
        }
        return scanner.nextInt();
    }

    private double getAmountInput(String action) {
        System.out.print("Enter amount to " + action + ": $");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid amount format. Please enter a valid number.");
            scanner.next(); 
            System.out.print("Enter amount to " + action + ": $");
        }
        return scanner.nextDouble();
    }

    private void checkBalance() {
        System.out.printf("%nYour current balance is: $%.2f%n", account.getBalance());
    }

    private void handleDeposit() {
        double amount = getAmountInput("deposit");
        account.deposit(amount);
    }

    private void handleWithdrawal() {
        double amount = getAmountInput("withdraw");
        account.withdraw(amount);
    }
}

// ==========================================
// 3. TASK4 CLASS (Application Entry Point)
// ==========================================
public class Task3 {
    public static void main(String[] args) {
        // Create the account with a $500 starting balance
        BankAccount userAccount = new BankAccount("NL-987654321", 500.00);

        // Pass the account to the ATM
        ATM atmMachine = new ATM(userAccount);

        // Start the ATM interface
        atmMachine.start();
    }
}
