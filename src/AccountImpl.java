public class AccountImpl implements Account {

    int balance;

    public AccountImpl(int balance) {
        this.balance = balance;
    }

    @Override
    public int deposit(int amount) {
        balance = amount + balance;
        return balance;
    }

    @Override
    public int withdraw(int amount) {
        if (amount > balance){
            throw new IllegalStateException("Insufficient funds!");
        }
        balance = balance - amount;
        return amount;
    }

    @Override
    public int checkBalance() {
        return balance;
    }
}
