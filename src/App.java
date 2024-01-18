public class App {
    ICustomerService customerService;
    IAccountService accountService;
    ITransactionService transactionService;
    IAuthenticationService authService;
    IAuthenticationView authView;

    public App() {
        this.customerService = new CustomerService(new CustomerRepository());
        this.accountService = new AccountService(new AccountRepository());
        this.transactionService = new TransactionService(new TransactionRepository());
        this.authService = new AuthenticationService(new AuthenticationRepository());
        this.authView = new AuthenticationView(customerService, authService);
    }

    private void ShowNavigationOption() {
        System.out.println("Main Menu\n------------------------");
        System.out.println("1 -> Login");
        System.out.println("2 -> Register");
        System.out.println("0 -> Exit");
    }
    public void launch() {
        int choice;
        while (true) {
            ShowNavigationOption();
            try {
                choice = Reader.selectOption();
                switch (choice) {
                    case 0 -> {
                        return;
                    }
                    case 1-> {
                        Customer customer = authView.login();
                        ICustomerView customerView = new CustomerView(customer, accountService, transactionService);
                        customerView.startSession();
                    }
                    case 2-> authView.register();
                    default-> System.out.println("Invalid Choice...\n");
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

}
