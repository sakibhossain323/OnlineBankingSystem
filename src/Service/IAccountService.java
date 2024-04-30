package Service;

import Model.Customer;
import Model.IAccount;
import Utility.AccountType;

import java.util.List;

public interface IAccountService {
    void createAccount(AccountType type, Customer accountHolder, double balance, boolean disabled);
    IAccount getAccount(int accountNo);
    List<IAccount> getAccounts(Customer customer);
}

