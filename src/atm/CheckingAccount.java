package atm;

public class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, String ownerName, double openingBalance, double overdraftLimit) {
        super(accountNumber, ownerName, openingBalance);
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative");
        }
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public String getAccountType() {
        return "CHECKING";
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        if ((getBalance() - amount) < -overdraftLimit) {
            double shortfall = amount - (getBalance() + overdraftLimit);
            throw new InsufficientFundsException(shortfall);
        }
        applyWithdrawal(amount);
    }
}
