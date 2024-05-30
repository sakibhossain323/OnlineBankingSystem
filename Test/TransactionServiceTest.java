import Model.Account;
import Model.Customer;
import Model.Transaction;
import Repository.AccountRepository;
import Repository.DbContext;
import Repository.TransactionRepository;
import Service.AccountService;
import Service.IAccountService;
import Service.ITransactionService;
import Service.TransactionService;
import Utility.AccountType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {
//    @Test
//    void transferTest() throws ClassNotFoundException {
//        Customer c1 = new Customer(1, "Alice", "0967", "a@b.c");
//        Customer c2 = new Customer(2, "Bob", "0968", "b@b.c");
//        IAccountService service = new AccountService(new AccountRepository(new DbContext()));
//        service.createAccount(AccountType.SavingAccount, c1, 1000, false);
//        service.createAccount(AccountType.SavingAccount, c2, 500, false);
//        Account ac1 = service.getAccount(1);
//        Account ac2 = service.getAccount(2);
//        ITransactionService trx = new TransactionService(new TransactionRepository(new DbContext()));
//        trx.transfer(ac1, ac2, 100);
//        List<Account> alice = service.getAccounts(c1);
//        List<Account> bob = service.getAccounts(c2);
//        assertEquals(600, bob.get(0).getBalance());
//        assertEquals(900, alice.get(0).getBalance());
//    }
//
//    @Test
//    void getTransactionTest() throws ClassNotFoundException {
//        Customer c1 = new Customer(1, "Alice", "0967", "a@b.c");
//        Customer c2 = new Customer(2, "Bob", "0968", "b@b.c");
//        IAccountService service = new AccountService(new AccountRepository(new DbContext()));
//        service.createAccount(AccountType.SavingAccount, c1, 1000, false);
//        service.createAccount(AccountType.SavingAccount, c2, 500, false);
//        Account ac1 = service.getAccount(1);
//        Account ac2 = service.getAccount(2);
//        ITransactionService trxService = new TransactionService(new TransactionRepository(new DbContext()));
//        trxService.transfer(ac1, ac2, 100);
//        Transaction trx = trxService.getTransaction(1);
//        assertEquals(100, trx.getAmount());
//    }


//    @Test
//    void transferTest() throws ClassNotFoundException {
//        Customer c1 = new Customer(1, "Alice", "0967", "a@b.c");
//        Customer c2 = new Customer(2, "Bob", "0968", "b@b.c");
//        IAccountService service = new AccountService(new AccountRepository(new DbContext()));
//        service.createAccount(AccountType.SavingAccount, c1, 1000, false);
//        service.createAccount(AccountType.SavingAccount, c2, 500, false);
//        Account ac1 = service.getAccount(1);
//        Account ac2 = service.getAccount(2);
//        ITransactionService trx = new TransactionService(new TransactionRepository(new DbContext()));
//        trx.transfer(ac1, ac2, 100);
//        List<Account> alice = service.getAccounts(c1);
//        List<Account> bob = service.getAccounts(c2);
//        assertEquals(600, bob.get(0).getBalance());
//        assertEquals(900, alice.get(0).getBalance());
//    }

    @Test
    void getTransactionTest() throws ClassNotFoundException {
        Customer c1 = new Customer("1", "Alice", "0967", "a@b.c");
        Customer c2 = new Customer("2", "Bob", "0968", "b@b.c");
        IAccountService service = new AccountService(new AccountRepository(new DbContext()));
        service.createAccount("SavingAccount", 1000, 1, c1);
        service.createAccount("SavingAccount", 500, 1, c2);
        Account ac1 = service.getAccount(c1,10001);
        Account ac2 = service.getAccount(c2,10002);
        ITransactionService trxService = new TransactionService(new TransactionRepository(new DbContext()));
        trxService.transfer(ac1, ac2, 100);
        Transaction trx = trxService.getTransaction(1);
        assertEquals(100, trx.getAmount());
    }


    @Test
    void transferTest() throws ClassNotFoundException {
        Customer c1 = new Customer("1", "Alice", "0967", "a@b.c");
        Customer c2 = new Customer("2", "Bob", "0968", "b@b.c");
        IAccountService service = new AccountService(new AccountRepository(new DbContext()));
        service.createAccount("SavingAccount", 1000, 1, c1);
        service.createAccount("SavingAccount", 500, 1, c2);
        Account ac1 = service.getAccount(c1, 10001);
        Account ac2 = service.getAccount(c2, 10002);
        ITransactionService trx = new TransactionService(new TransactionRepository(new DbContext()));
        trx.transfer(ac1, ac2, 100);
        List<Account> alice = service.getAccounts(c1);
        List<Account> bob = service.getAccounts(c2);
        assertEquals(600, bob.get(0).getBalance());
        assertEquals(900, alice.get(0).getBalance());

    }

    @Test

   void getDateTest() throws ClassNotFoundException {
        Customer c1 = new Customer("1", "Alice", "0967", "a@b.c");
        Customer c2 = new Customer("2", "Bob", "0968", "b@b.c");

        IAccountService service = new AccountService(new AccountRepository(new DbContext()));

        service.createAccount("SavingAccount", 1000, 1, c1);
        service.createAccount("SavingAccount", 500, 1, c2);

        Account ac1 = service.getAccount(c1, 10001);
        Account ac2 = service.getAccount(c2, 10002);

        ITransactionService trx = new TransactionService(new TransactionRepository(new DbContext()));

        trx.transfer(ac1, ac2, 100);

        List<Transaction> transactions = trx.getTransactions(ac1);

        assertEquals("2021-07-01", transactions.get(0).getDate());

    }


    @Test
    void transferDecreasesSenderBalanceTest() throws ClassNotFoundException {
        Customer c1 = new Customer("1", "Alice", "0967", "a@b.c");
        Customer c2 = new Customer("2", "Bob", "0968", "b@b.c");
        IAccountService service = new AccountService(new AccountRepository(new DbContext()));
        service.createAccount("SavingAccount", 1000, 1, c1);
        service.createAccount("SavingAccount", 500, 1, c2);
        Account ac1 = service.getAccount(c1, 10001);
        Account ac2 = service.getAccount(c2, 10002);
        ITransactionService trx = new TransactionService(new TransactionRepository(new DbContext()));
        double initialBalance = ac1.getBalance();
        trx.transfer(ac1, ac2, 100);
        assertEquals(initialBalance - 100, ac1.getBalance());
    }

    @Test
    void transferIncreasesReceiverBalanceTest() throws ClassNotFoundException {
        Customer c1 = new Customer("1", "Alice", "0967", "a@b.c");
        Customer c2 = new Customer("2", "Bob", "0968", "b@b.c");
        IAccountService service = new AccountService(new AccountRepository(new DbContext()));
        service.createAccount("SavingAccount", 1000, 1, c1);
        service.createAccount("SavingAccount", 500, 1, c2);
        Account ac1 = service.getAccount(c1, 10001);
        Account ac2 = service.getAccount(c2, 10002);
        ITransactionService trx = new TransactionService(new TransactionRepository(new DbContext()));
        double initialBalance = ac2.getBalance();
        trx.transfer(ac1, ac2, 100);
        assertEquals(initialBalance + 100, ac2.getBalance());
    }



    @Test
    void TransferTest() throws ClassNotFoundException {
        Customer c1 = new Customer("1", "Alice", "0967", "a@b.c");
        Customer c2 = new Customer("2", "Bob", "0968", "b@b.c");
        IAccountService service = new AccountService(new AccountRepository(new DbContext()));
        service.createAccount("SavingAccount", 1000, 1, c1);
        service.createAccount("SavingAccount", 500, 1, c2);
        Account ac1 = service.getAccount(c1, 10001);
        Account ac2 = service.getAccount(c2, 10002);
        ITransactionService trx = new TransactionService(new TransactionRepository(new DbContext()));
        trx.transfer(ac1, ac2, 100);
        List<Account> alice = service.getAccounts(c1);
        List<Account> bob = service.getAccounts(c2);
        assertEquals(600, bob.get(0).getBalance());
        assertEquals(900, alice.get(0).getBalance());
    }








}