package Service;

import Model.Account;
import Model.Customer;
import Utility.AccountType;

import java.util.List;

public interface IAccountService {
    void createAccount(String accountType, double balance, int branchId,  Customer accountHolder);
    Account getAccount(int accountNo);
    List<Account> getAccounts(Customer customer);
}

