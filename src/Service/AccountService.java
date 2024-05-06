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
    public void createAccount(AccountType type, Customer accountHolder, double balance, boolean disabled) {
        Account account = new Account(accountHolder, balance, disabled);
        accountRepository.createAccount(account);
    }

    @Override
    public Account getAccount(int accountNo) {
        return accountRepository.getAccount(accountNo);
    }

    @Override
    public List<Account> getAccounts(Customer customer) {
        return accountRepository.getAccounts(customer);
    }
}
