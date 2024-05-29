package Repository;

import Model.Account;
import Model.Customer;

import java.util.List;

public interface IAccountRepository {
    void createAccount(Account account);

    Account getAccount(Customer customer,int accountNo);

    List<Account> getAccounts(Customer customer);
}

