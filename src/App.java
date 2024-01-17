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

    public void launch()
    {

    }
}
