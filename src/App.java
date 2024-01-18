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

    private void ShowNavigationOption()
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
            ShowNavigationOption();
            choice = scanner.nextInt();
            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1-> {
                    IAuthenticationView authView = new AuthenticationView();
                    try {
                        Customer  customer = authView.login(customerService, authenticationService);
                        ICustomerView customerView = new CustomerView(customer, accountService, transactionService);
                        customerView.startSession();
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                }
                case 2-> {
                    IAuthenticationView authView = new AuthenticationView();
                    authView.register(customerService, authenticationService);
                }
                default -> System.out.println("Invalid Choice");
            }
        }
    }
}
