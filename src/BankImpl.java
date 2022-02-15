import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BankImpl implements Bank{

    HashMap<String,Users> users;
    Set<AccountImpl> accountImpl;
    HashMap<String, AccountImpl> accountHashMap;
    Session session;

    public void addUser(String name, String password){
        Users user = new Users(name, password);
        AccountImpl accountImpl = user.getAccount();
        users.put(name,user);
        accountHashMap.put(user.getName(), accountImpl);
    }

    Scanner sc = new Scanner(System.in);
    public BankImpl() {
        this.users = new HashMap<>();
        this.accountImpl = new HashSet<>();
        this.accountHashMap = new HashMap<>();
        populateUsers();
    }

    private void populateUsers() {
        addUser("Max","Mac");
        addUser("Alex","desk");
        addUser("Andrew", "chair");
    }

    @Override
    public int depositMoney(int amount) {
        if (!isloggedin()) {
            throw new IllegalStateException("No user logged in.");
        }
        var account = session.users.getAccount();
        account.deposit(amount);
        return account.checkBalance();
    }

    @Override
    public int withdraw(int amount) {
        if (!isloggedin()){
            throw new IllegalStateException("No user logged in.");
        }
        var account = session.users.getAccount();
        account.withdraw(amount);
        return account.checkBalance();
    }

    @Override
    public int buyAirtime(int amount) {
        if (!isloggedin()) {
            throw new IllegalStateException("No user logged in.");
        }
        var account = session.users.getAccount();
        return account.withdraw(amount);
    }

    @Override
    public boolean login() {
        if (isloggedin()){
            System.out.println(session.users.name+" is currently logged in.");
            return false;
        }
        System.out.println("Please enter your name and password to login.");
        System.out.println("Enter your name:");
        var name = sc.next();
        int counter = 0;
        while (counter < 4){
            if (counter == 3) throw new IllegalStateException("Maximum tries exceeded.");
            System.out.println("Enter your password:");
            var password = sc.next();
            var user = users.getOrDefault(name, null);
            if (user == null){
                Users newuser = new Users(name, password);
                AccountImpl accountImpl = newuser.getAccount();
                users.put(name,newuser);
                accountHashMap.put(newuser.getName(), accountImpl);
                session = new Session(newuser);
                System.out.println("You have successfully been registered.");
                break;
            } else {
                if (!password.equals(user.password)){
                    counter++;
                    System.out.println("Wrong password!");
                } else {
                    session = new Session(user);
                    break;
                }

            }
        }
        return true;
    }

    @Override
    public boolean register() {
        System.out.println("Please enter your name and password to register yourself.");
        System.out.println("Enter your name:");
        var name = sc.next();
        System.out.println("Enter your password:");
        var password = sc.next();
        Users user = new Users(name, password);
        AccountImpl accountImpl = user.getAccount();
        users.put(name,user);
        accountHashMap.put(user.getName(), accountImpl);
        return true;
    }

    @Override
    public boolean logout() {
        session = null;
        return session == null;
    }

    @Override
    public boolean isloggedin() {
        return session != null;
    }

    @Override
    public int checkBalance() {
        Users user = session.users;
        AccountImpl accountImpl = user.getAccount();
        return accountImpl.checkBalance();
    }
}
