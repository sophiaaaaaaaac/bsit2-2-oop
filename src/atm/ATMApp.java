package atm;

import java.util.Scanner;

public class ATMApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ATMService atm = new ATMService();

        Account account = new SavingsAccount("SA-1001", "Juan Dela Cruz", 5000.0, 0.02);
        boolean running = true;

        while (running) {
            printHeader(account);
            printMenu();
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        System.out.printf("Balance: PHP %.2f%n", account.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = Double.parseDouble(input.nextLine());
                        atm.deposit(account, depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = Double.parseDouble(input.nextLine());
                        account.withdraw(withdrawAmount);
                        System.out.printf("Success. New balance: PHP %.2f%n", account.getBalance());
                        break;
                    case 4:
                        double totalDeposited = atm.depositAll(account, 100.0, 250.5, 300.0);
                        System.out.printf("Deposited multiple amounts successfully. Total added: PHP %.2f. New balance: PHP %.2f%n",
                                totalDeposited, account.getBalance());
                        break;
                    case 5:
                        System.out.printf("Balance before demo: PHP %.2f%n", account.getBalance());
                        atm.tryToReplace(account);
                        System.out.printf("Balance after tryToReplace (unchanged): PHP %.2f%n", account.getBalance());
                        atm.addBonus(account, 50.0);
                        System.out.printf("Balance after addBonus (mutated): PHP %.2f%n", account.getBalance());
                        break;
                    case 0:
                        running = false;
                        System.out.println("Thank you for using Liceo ATM!");
                        break;
                    default:
                        System.out.println("Invalid option. Choose 0 to 5.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please type a number, not letters.");
            } catch (InsufficientFundsException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.printf("You need PHP %.2f more.%n", e.getShortfall());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("---------------------------------");
            }
        }
        input.close();
    }

    private static void printHeader(Account account) {
        System.out.println("=================================");
        System.out.println("LICEO ATM MACHINE");
        System.out.println("=================================");
        System.out.printf("Account : %s%n", account.getAccountNumber() + " (" + account.getOwnerName() + ")");
        System.out.printf("Type    : %s%n", account.getAccountType());
        System.out.printf("Balance : PHP %.2f%n", account.getBalance());
        System.out.println("---------------------------------");
    }

    private static void printMenu() {
        System.out.println("[1] Check Balance");
        System.out.println("[2] Deposit");
        System.out.println("[3] Withdraw");
        System.out.println("[4] Deposit Multiple Amounts");
        System.out.println("[5] Pass-by-Value Demo");
        System.out.println("[0] Exit");
    }
}