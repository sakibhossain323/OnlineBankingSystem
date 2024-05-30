package View;

import Model.Account;
import Model.Customer;
import Service.IAccountService;
import Service.ITransactionService;
import Utility.AccountType;

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
        System.out.println(customer.getName()+"'s Dashboard\n-----------------");
        System.out.println("1 -> Add Account");
        System.out.println("2 -> List Accounts");
        System.out.println("3 -> Balance Inquiry");
        System.out.println("4 -> Fund Transfer");
        System.out.println("5 -> Take Loan");
        System.out.println("0 -> Exit");
    }

    private AccountType selectAccountTypes()
    {
        for (AccountType type: AccountType.values()) {
            System.out.println(type.ordinal()+1 + " -> " + type.toString());
        }
        int choice = Reader.selectOption();
        for (AccountType type: AccountType.values()) {
            if(choice-1 == type.ordinal()) return type;
        }
        throw new RuntimeException("Invalid choice!...\n");
    }

    private Account selectAccount()
    {
        System.out.println("Choose Account: ");

        List<Account> accounts = accountService.getAccounts(customer);
        for(int i = 0; i < accounts.size();i++)
        {
            System.out.println(i+1 + ") A/C No: " + accounts.get(i).getAccountNo());
        }
        int choice = Reader.selectOption() - 1;

        if(0 <= choice && choice < accounts.size())
        {
            return accounts.get(choice);
        }
        throw new RuntimeException("Invalid choice!...\n");
    }

    @Override
    public void startSession() {
        System.out.println("Welcome "+customer.getName()+"\n");
        int choice;
        while (true) {
            ShowNavigationOption();
            try {
                choice = Reader.selectOption();
                switch (choice) {
                    case 0 -> {
                        return;
                    }
                    case 1-> createAccount();
                    case 2-> ListAccounts();
                    case 3-> checkBalance();
                    case 4-> transfer();
                    case 5-> takeLoan();
                    default-> System.out.println("Invalid Choice...\n");
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }

    @Override
    public void createAccount()
    {

        String type = String.valueOf(selectAccountTypes());

        double balance = Reader.readDouble("Initial Deposit Amount:");
        int branchId = Reader.readInt("Branch ID");

        accountService.createAccount(type, balance, branchId, customer);
    }

    @Override
    public void ListAccounts()
    {
        List<Account> accounts = accountService.getAccounts(customer);
        for(int i = 0; i < accounts.size(); i++) {
            System.out.println((i+1)+". A/C No: "+accounts.get(i).getAccountNo());
        }
    }

    @Override
    public void checkBalance()
    {
        Account account = selectAccount();
        System.out.println("Balance: "+account.getBalance());

        //int accountNo = Reader.readInt("A/C No");
        //Account account = accountService.getAccount(accountNo);
        //System.out.println("Balance: "+account.getBalance());
    }

   @Override
   public void transfer()
   {
       Account from = selectAccount();

       int toNo = Reader.readInt("A/C No(to)");
       double amount = Reader.readDouble("amount");
       Account to = accountService.getAccount(customer,toNo);

       transactionService.transfer(from, to, amount);
   }

    @Override
    public void takeLoan()
    {
        Account account = selectAccount();
        double amount = Reader.readDouble("Amount");
        int duration = Reader.readInt("Duration");

        transactionService.takeLoan(account, amount, duration);
    }
}

