import java.util.Scanner;

public class MainApp {

    static int selectedOption;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("*** Mobile Banking Application System ***");

        BankImpl bank = new BankImpl();
        if (!bank.isloggedin()){
            bank.login();
        }
        do {
            if (!bank.isloggedin()){
                bank.login();
            }

            displayMenu();

                switch (selectedOption) {
                    case 1:
                        try{
                            System.out.println("Enter the amount you want to deposit: ");
                            int depositAmount = sc.nextInt();
                            bank.depositMoney(depositAmount);
                            System.out.println(bank.session.users.name+" Your balance is "+bank.checkBalance());
                        } catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        try {
                            System.out.println("Enter the amount you would like to withdraw: ");
                            int withdrawAmount = sc.nextInt();
                            int withdraw = bank.withdraw(withdrawAmount);
                            System.out.println(bank.session.users.name+" You have successfully withdrawn "+withdraw);
                            System.out.println("Your balance is "+bank.checkBalance());
                        } catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("Enter the amount of airtime you would like to buy: ");
                        int airtimeAmount = sc.nextInt();
                        int airtime = bank.buyAirtime(airtimeAmount);
                        System.out.println(bank.session.users.name+" You have successfully purchased airtime worth: "+airtime);
                        System.out.println("Your account balance is "+bank.checkBalance());
                        break;
                    case 4:
                        System.out.println(bank.session.users.name+" Your account balance is"+bank.checkBalance());
                        displayMenu();
                        break;
                    case 5:
                        System.out.println(bank.session.users.name);
                        bank.logout();
                        System.out.println("Your session has ended.");
                        System.out.println("Thank you for banking with us");
                        break;
                    default:
                        System.out.println("Transaction has Ended");
                        System.out.println("Thank you for banking with us");
                        System.exit(0);
                        break;
                }
        } while (true);
    }

    private static void displayMenu() {
        System.out.println("Please select an option below");
        System.out.println("Press 1 to Deposit Money");
        System.out.println("Press 2 to Withdraw Money");
        System.out.println("Press 3 to buy Airtime");
        System.out.println("Press 4 to Check Balance");
        System.out.println("Press 5 to logout");
        System.out.println("Press any key: ");
        selectedOption = sc.nextInt();
    }
}
