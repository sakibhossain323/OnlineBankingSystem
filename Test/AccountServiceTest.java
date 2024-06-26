import Model.Account;
import Model.Customer;
import Repository.AccountRepository;
import Repository.CustomerRepository;
import Repository.DbContext;
import Service.AccountService;
import Service.CustomerService;
import Service.IAccountService;
import Service.ICustomerService;
import Utility.AccountType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountServiceTest {


    @Test
    public void createCustomerTest() throws ClassNotFoundException {
        ICustomerService service = new CustomerService(new CustomerRepository(new DbContext()));
        service.createCustomer("Alice","095","a@b.c","abc");
        service.createCustomer("Bob", "097", "b@c.d", "def");
        Customer customer = service.getCustomerById(service.getNewId());
        assertEquals("Bob", customer.getName());
    }


    @Test
    public void createAccountTest() throws ClassNotFoundException {
        ICustomerService service = new CustomerService(new CustomerRepository(new DbContext()));
        IAccountService accountService = new AccountService(new AccountRepository(new DbContext()));
        Customer customer = service.createCustomer("Alice","095","a@b.c","abc");
        accountService.createAccount("SavingAccount", 1000, 1,customer);
        List<Account> accounts = accountService.getAccounts(customer);
        assertEquals(1000, accounts.get(0).getBalance());
    }


    @Test
    public void getAccountTest() throws ClassNotFoundException {
        ICustomerService service = new CustomerService(new CustomerRepository(new DbContext()));
        IAccountService accountService = new AccountService(new AccountRepository(new DbContext()));
        Customer customer = service.createCustomer("Alice","095","a@b.c","abc");


        customer.setId(service.getNewId());

        accountService.createAccount("SavingAccount", 1000, 1,customer);
        Account account = accountService.getAccount(customer, 1);
        assertEquals(1000, account.getBalance());
    }



    @Test
    public void getAccountsTest() throws ClassNotFoundException {
        ICustomerService service = new CustomerService(new CustomerRepository(new DbContext()));
        IAccountService accountService = new AccountService(new AccountRepository(new DbContext()));
        Customer customer = service.createCustomer("Alice","095","a@b.c","abc");
        customer.setId(service.getNewId());
        accountService.createAccount("SavingAccount", 1000, 1,customer);
        accountService.createAccount("SavingAccount", 2000, 1,customer);
        List<Account> accounts = accountService.getAccounts(customer);
        assertEquals(2, accounts.size());
    }

    @Test
    void createAccountThrowsExceptionWhenBalanceLessThanZeroTest() throws ClassNotFoundException {
        ICustomerService service = new CustomerService(new CustomerRepository(new DbContext()));
        IAccountService accountService = new AccountService(new AccountRepository(new DbContext()));
        Customer customer = service.createCustomer("Alice","095","a@b.c","abc");
        Assertions.assertThrows(RuntimeException.class, () -> {
            accountService.createAccount("SavingAccount", -1000, 1, customer);
        });
    }

    @Test
    void getAccountReturnsNullWhenAccountDoesNotExistTest() throws ClassNotFoundException {
        ICustomerService service = new CustomerService(new CustomerRepository(new DbContext()));
        IAccountService accountService = new AccountService(new AccountRepository(new DbContext()));
        Customer customer = service.createCustomer("Alice","095","a@b.c","abc");
        Account account = accountService.getAccount(customer, 9999);
        assertNull(account);
    }

    @Test
    void getAccountsReturnsEmptyListWhenCustomerHasNoAccountsTest() throws ClassNotFoundException {
        ICustomerService service = new CustomerService(new CustomerRepository(new DbContext()));
        IAccountService accountService = new AccountService(new AccountRepository(new DbContext()));
        Customer customer = service.createCustomer("Alice","095","a@b.c","abc");
        List<Account> accounts = accountService.getAccounts(customer);
        assertTrue(accounts.isEmpty());
    }



}