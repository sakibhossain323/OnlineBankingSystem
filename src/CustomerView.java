import java.util.List;
import java.util.Scanner;

public class CustomerView implements ICustomerView {
    Customer customer;
    IAccountService accountService;
    ITransactionService transactionService;

    public CustomerView(Customer customer, IAccountService accountService, ITransactionService transactionService) {
        this.customer = customer;
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    private void ShowNavigationOption()
    {
        System.out.println("1 -> Add Account");
        System.out.println("2 -> List Accounts");
        System.out.println("0 -> Exit");
        System.out.println("Choose Option: ");
    }

    @Override
    public void startSession() {
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

                    System.out.println("gg");
                }
                case 2-> {
                    System.out.println("gg wp");
                }
                default -> System.out.println("Invalid Choice");
            }
        }
    }

    @Override
    public void addAccount() {

    }

    @Override
    public List<IAccount> getAccounts() {
        return null;
    }

    @Override
    public void transfer() {

    }
}
