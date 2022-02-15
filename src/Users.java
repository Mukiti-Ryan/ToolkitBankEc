public class Users {
    String name;
    String password;
    AccountImpl accountImpl;

    public Users(String name, String password) {
        this.name = name;
        this.password = password;
        accountImpl = new AccountImpl(0);
    }

    public String getName() {
        return name;
    }

    public AccountImpl getAccount() {
        return accountImpl;
    }
}
