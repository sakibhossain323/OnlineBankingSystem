package View;

import Model.Account;
import Model.Customer;
import Model.Transaction;
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

    public CustomerView(Customer customer, IAccountService accountService, ITransactionService transactionService) {
        this.customer = customer;
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.scanner = new Scanner(System.in);
    }

    private void ShowNavigationOption()
    {
        System.out.println(CYAN + customer.getName() + "'s Dashboard" + RESET);
        System.out.println(YELLOW + "-----------------" + RESET);
        System.out.println(GREEN + "1 -> Add Account" + RESET);
        System.out.println(GREEN + "2 -> List Accounts" + RESET);
        System.out.println(GREEN + "3 -> Balance Inquiry" + RESET);
        System.out.println(GREEN + "4 -> Fund Transfer" + RESET);
        System.out.println(GREEN + "5 -> Transaction History" + RESET);
        System.out.println(GREEN + "6 -> Take Loan" + RESET);
        System.out.println(GREEN + "7 -> Loan Status" + RESET);
        System.out.println(RED + "0 -> Exit" + RESET);
    }

    private AccountType selectAccountTypes()
    {
        System.out.println(YELLOW + "Select Account Type" + RESET);
        for (AccountType type: AccountType.values()) {
            System.out.println(GREEN + (type.ordinal() + 1) + " -> " + type.toString() + RESET);
        }
        int choice = Reader.selectOption();
        for (AccountType type: AccountType.values()) {
            if(choice - 1 == type.ordinal()) return type;
        }
        throw new RuntimeException(RED + "Invalid choice!...\n" + RESET);
    }

    private Account selectAccount()
    {
        System.out.println(YELLOW + "Choose Account: " + RESET);

        List<Account> accounts = accountService.getAccounts(customer);
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(GREEN + (i + 1) + ") A/C No: " + accounts.get(i).getAccountNo() + RESET);
        }
        int choice = Reader.selectOption() - 1;

        if (0 <= choice && choice < accounts.size()) {
            return accounts.get(choice);
        }
        throw new RuntimeException(RED + "Invalid choice!...\n" + RESET);
    }

    @Override
    public void startSession() {
        System.out.println(GREEN + "Welcome " + customer.getName() + "\n" + RESET);
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
                    case 1 -> createAccount();
                    case 2 -> ListAccounts();
                    case 3 -> checkBalance();
                    case 4 -> transfer();
                    case 5 -> viewTransactions();
                    case 6 -> takeLoan();
                    case 7 -> checkLoanStatus();

                    default -> System.out.println(RED + "Invalid Choice...\n" + RESET);
                }
            } catch (Exception e) {
                System.out.println(RED + e.getMessage() + RESET);
            }
        }
    }

    @Override
    public void createAccount() {
        System.out.println(YELLOW + "Create Account" + RESET);
        String type = String.valueOf(selectAccountTypes());
        double balance = Reader.readDouble("Initial Deposit Amount: ");
        int branchId = Reader.readInt("Branch ID: ");
        accountService.createAccount(type, balance, branchId, customer);
        System.out.println(GREEN + "Account created successfully!" + RESET);
    }

    @Override
    public void ListAccounts() {
        System.out.println(YELLOW + "List of Accounts" + RESET);
        List<Account> accounts = accountService.getAccounts(customer);
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(GREEN + (i + 1) + ". A/C No: " + accounts.get(i).getAccountNo() + RESET);
        }
    }

    @Override
    public void checkBalance() {
        System.out.println(YELLOW + "Balance Inquiry" + RESET);
        Account account = selectAccount();
        System.out.println(GREEN + "Balance: " + account.getBalance() + RESET);
    }

    @Override
    public void transfer() {
        System.out.println(YELLOW + "Fund Transfer" + RESET);
        Account from = selectAccount();
        int toNo = Reader.readInt("A/C No (to): ");
        double amount = Reader.readDouble("Amount: ");
        Account to = accountService.getAccount(customer, toNo);
        transactionService.transfer(from, to, amount);
        System.out.println(GREEN + "Transfer successful!" + RESET);
    }

    @Override
    public void takeLoan() {
        System.out.println(YELLOW + "Take a Loan" + RESET);
        Account account = selectAccount();
        double amount = Reader.readDouble("Amount: ");
        int duration = Reader.readInt("Duration (months): ");
        transactionService.takeLoan(account, amount, duration);
        System.out.println(GREEN + "Loan processed successfully!" + RESET);
    }


    public void checkLoanStatus() {


    }

    public void viewTransactions() {
        System.out.println(YELLOW + "Transactions" + RESET);
        Account account = selectAccount();
        List<Transaction> transactions = transactionService.getTransactions(account);
        int i = 1;
        for (Transaction transaction: transactions) {
            System.out.print(YELLOW + "1) " + i++ + RESET);
            System.out.print(YELLOW +" Date: " + transaction.getDate() + RESET);
            if(account.getAccountNo() == transaction.getFrom().getAccountNo())
            {
                System.out.print(RED + " Debit: " + transaction.getAmount() + RESET);
            }
            else
            {
                System.out.print(GREEN + " Credit: " + transaction.getAmount() + RESET);
            }
            System.out.println(YELLOW + " TrxId: " + transaction.getId() + RESET);
        }
    }

}

