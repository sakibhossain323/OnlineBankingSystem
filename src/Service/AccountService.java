package Service;

import Model.Account;
import Model.Customer;
import Repository.IAccountRepository;
import Utility.AccountType;

import java.util.List;

public class AccountService implements IAccountService {
    IAccountRepository accountRepository;

    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void createAccount(String accountType, double balance, int branchId,  Customer accountHolder)
    {
        Account account = new Account(accountType, balance, branchId, accountHolder);
        accountRepository.createAccount(account);
    }

    @Override
    public Account getAccount(Customer customer, int accountNo)
    {
        return accountRepository.getAccount(customer,accountNo);
    }

    @Override
    public List<Account> getAccounts(Customer customer)
    {
        return accountRepository.getAccounts(customer);
    }
}
