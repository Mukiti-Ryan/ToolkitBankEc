public interface Bank {

    int depositMoney(int amount);
    int withdraw(int amount);
    int buyAirtime(int amount);
    boolean login();
    boolean register();
    boolean logout();
    boolean isloggedin();
    int checkBalance();
}
