package Service;

import Model.Account;
import Model.Customer;
import Utility.AccountType;

import java.util.List;

public interface IAccountService {
    void createAccount(AccountType type, Customer accountHolder, double balance, boolean disabled);
    Account getAccount(int accountNo);
    List<Account> getAccounts(Customer customer);
}

