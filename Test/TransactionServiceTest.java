import Model.Account;
import Model.Customer;
import Model.Transaction;
import Repository.AccountRepository;
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
    @Test
    void transferTest()
    {
        Customer c1 = new Customer(1, "Alice", "0967", "a@b.c");
        Customer c2 = new Customer(2, "Bob", "0968", "b@b.c");
        IAccountService service = new AccountService(new AccountRepository());
        service.createAccount(AccountType.SavingAccount, c1, 1000, false);
        service.createAccount(AccountType.SavingAccount, c2, 500, false);
        Account ac1 = service.getAccount(1);
        Account ac2 = service.getAccount(2);
        ITransactionService trx = new TransactionService(new TransactionRepository());
        trx.transfer(ac1, ac2, 100);
        List<Account> alice = service.getAccounts(c1);
        List<Account> bob = service.getAccounts(c2);
        assertEquals(600, bob.get(0).getBalance());
        assertEquals(900, alice.get(0).getBalance());
    }

    @Test
    void getTransactionTest()
    {
        Customer c1 = new Customer(1, "Alice", "0967", "a@b.c");
        Customer c2 = new Customer(2, "Bob", "0968", "b@b.c");
        IAccountService service = new AccountService(new AccountRepository());
        service.createAccount(AccountType.SavingAccount, c1, 1000, false);
        service.createAccount(AccountType.SavingAccount, c2, 500, false);
        Account ac1 = service.getAccount(1);
        Account ac2 = service.getAccount(2);
        ITransactionService trxService = new TransactionService(new TransactionRepository());
        trxService.transfer(ac1, ac2, 100);
        Transaction trx = trxService.getTransaction(1);
        assertEquals(100, trx.getAmount());
    }
}