import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.OptionalDouble;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {
    @Test
    void createAccountTest()
    {
        IAccountService service = new AccountService(new AccountRepository());
        ICustomer customer = new Customer(1, "Bob", "096", "a@b.c");
        service.createAccount(AccountType.SavingAccount,customer, 100, false);
        service.createAccount(AccountType.SavingAccount,customer, 500, false);
        IAccount account = service.getAccount(2);
        assertEquals(500, account.getBalance());
    }

    @Test
    void getAccountsTest()
    {
        IAccountService service = new AccountService(new AccountRepository());
        ICustomer customer = new Customer(1, "Bob", "096", "a@b.c");
        service.createAccount(AccountType.SavingAccount,customer, 100, false);
        service.createAccount(AccountType.SavingAccount,customer, 500, false);
        List<IAccount> accounts = service.getAccounts(customer);
        double sum = accounts.stream().mapToDouble(IAccount::getBalance).sum();
        assertEquals(600, sum);
    }
}