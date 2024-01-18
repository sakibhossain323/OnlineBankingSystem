import java.util.List;
import java.util.Scanner;

public class CustomerView implements ICustomerView {
    Customer customer;
    IAccountService accountService;
    ITransactionService transactionService;
    Scanner scanner;

    public CustomerView(Customer customer, IAccountService accountService, ITransactionService transactionService) {
        this.customer = customer;
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.scanner = new Scanner(System.in);
    }

    private void ShowNavigationOption()
    {
        System.out.println("1 -> Add Account");
        System.out.println("2 -> List Accounts");
        System.out.println("3 -> Fund Transfer");
        System.out.println("0 -> Exit");
        System.out.println("Choose Option: ");
    }

    private AccountType selectAccountTypes()
    {
        for (AccountType type: AccountType.values()) {
            System.out.println(type.ordinal()+1 + " -> " + type.toString());
        }
        System.out.println("Choose Option: ");
        int choice = scanner.nextInt();
        for (AccountType type: AccountType.values()) {
            if(choice-1 == type.ordinal()) return type;
        }
        throw new RuntimeException("Invalid choice!");
    }

    @Override
    public void startSession() {
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
                    try{
                        addAccount();
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2-> {
                    try{
                        ListAccounts();
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3-> {
                    try{
                        transfer();
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                default -> System.out.println("Invalid Choice");
            }
        }
    }

    @Override
    public void addAccount() {

        AccountType type = selectAccountTypes();
        double balance = Reader.readFieldDouble("initial deposit");
        accountService.createAccount(type, customer, balance, false);
    }

    @Override
    public void ListAccounts() {
        List<IAccount> accounts = accountService.getAccounts(customer);
        for(int i = 0; i < accounts.size(); i++) {
            System.out.println((i+1)+". "+accounts.get(i).getAccountNo());
        }
    }

    @Override
    public void transfer() {
        int fromNo = Reader.readFieldInt("A/C No(from)");
        int toNo = Reader.readFieldInt("A/C No(to)");
        double amount = Reader.readFieldDouble("amount");
        IAccount from = accountService.getAccount(fromNo);
        IAccount to = accountService.getAccount(toNo);
        transactionService.transfer(from, to, amount);
    }
}

