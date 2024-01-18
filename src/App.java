import java.util.Scanner;

public class App {
    ICustomerService customerService;
    IAccountService accountService;
    ITransactionService transactionService;
    IAuthenticationService authenticationService;

    public App() {
        this.customerService = new CustomerService(new CustomerRepository());
        this.accountService = new AccountService(new AccountRepository());
        this.transactionService = new TransactionService(new TransactionRepository());
        this.authenticationService = new AuthenticationService(new AuthenticationRepository());
    }

    private void printNavigationOption()
    {
        System.out.println("1 -> Login");
        System.out.println("2 -> Register");
        System.out.println("0 -> Exit");
        System.out.println("Choose Option: ");
    }
    public void launch()
    {
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true)
        {
            printNavigationOption();
            choice = scanner.nextInt();
            switch (choice) {
                case 0 -> {
                return;
            }
            case 1-> {
                System.out.println("logging in");
            }
                case 2-> {
                    System.out.println("registering...");
                }
                default -> System.out.println("Invalid Choice");
            }
        }
    }
}
