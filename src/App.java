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

    public App(DbContext db) {
        this.db = db;
        this.customerService = new CustomerService(new CustomerRepository(db));
        this.accountService = new AccountService(new AccountRepository(db));
        this.transactionService = new TransactionService(new TransactionRepository(db));
        this.authService = new AuthenticationService(new AuthenticationRepository(db));
        this.authView = new AuthenticationView(customerService, authService);
    }

    private void ShowNavigationOption()  {
        System.out.println("Main Menu\n------------------------");
        System.out.println("1 -> Login");
        System.out.println("2 -> Register");
        System.out.println("0 -> Exit");
        }

//    private void ShowNavigationOption() throws IOException {
//
//        String message = "Main Menu\n------------------------";
//        String options[] = {"Login", "Register", "Exit"};
//        IMenuView menuView = new MenuView(options);
//
//        int choice = menuView.Run(message);
//
//        switch(choice)
//        {
//            case 0:
//                Customer customer = authView.login();
//                ICustomerView customerView = new CustomerView(customer, accountService, transactionService);
//                customerView.startSession();
//                break;
//            case 1:
//                authView.register();
//                break;
//            case 2:
//                return;
//            default:
//                System.out.println("Invalid Choice...\n");
//        }
//    }
    public void launch() {
//        try {
//            while (true) {
//                ShowNavigationOption();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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
