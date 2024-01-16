public class App {
    ICustomerService customerService;
    IAccountService accountService;
    ITransactionService transactionService;

    public App() {
        this.customerService = new CustomerService(new CustomerRepository());
        this.accountService = new AccountService(new AccountRepository());
//        this.transactionService = new TransactionService(new TransactionRepository());
    }
}
