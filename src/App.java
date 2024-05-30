import Model.*;
import Repository.*;
import Service.*;
import View.*;
import View.Reader;

import java.io.IOException;

public class App {
    DbContext db;
    ICustomerService customerService;
    IAccountService accountService;
    ITransactionService transactionService;
    IAuthenticationService authService;
    IAuthenticationView authView;

    // ANSI escape codes for colors
    private static final String RESET = "\u001B[0m";
    private static final String BLACK = "\u001B[30m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";

    public App(DbContext db) {
        this.db = db;
        this.customerService = new CustomerService(new CustomerRepository(db));
        this.accountService = new AccountService(new AccountRepository(db));
        this.transactionService = new TransactionService(new TransactionRepository(db));
        this.authService = new AuthenticationService(new AuthenticationRepository(db));
        this.authView = new AuthenticationView(customerService, authService);
    }

    private void ShowNavigationOption() {
        System.out.println(CYAN + "Main Menu" + RESET);
        System.out.println(YELLOW + "------------------------" + RESET);
        System.out.println(GREEN + "1 -> Login" + RESET);
        System.out.println(GREEN + "2 -> Register" + RESET);
        System.out.println(RED + "0 -> Exit" + RESET);
    }

    public void launch() {
        int choice;
        while (true) {
            ShowNavigationOption();
            try {
                choice = Reader.selectOption();
                switch (choice) {
                    case 0 -> {
                        System.out.println(RED + "Exiting... Goodbye!" + RESET);
                        return;
                    }
                    case 1 -> {
                        Customer customer = authView.login();
                        ICustomerView customerView = new CustomerView(customer, accountService, transactionService);
                        customerView.startSession();
                    }
                    case 2 -> authView.register();
                    default -> System.out.println(RED + "Invalid Choice...\n" + RESET);
                }
            } catch (Exception e) {
                System.out.println(RED + e.getMessage() + RESET);
            }
        }
    }
}