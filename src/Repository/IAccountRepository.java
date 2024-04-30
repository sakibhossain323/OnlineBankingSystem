package Repository;

import Model.Customer;
import Model.IAccount;

import java.util.List;

public interface IAccountRepository {
    void createAccount(IAccount account);

    IAccount getAccount(int accountNo);

    List<IAccount> getAccounts(Customer customer);
}

