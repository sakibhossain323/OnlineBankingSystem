import java.util.List;

public class AccountService implements IAccountService {
    IAccountRepository accountRepository;

    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void createAccount(AccountType type, Customer accountHolder, double balance, boolean disabled) {
        AccountFactory factory = new AccountFactory();
        IAccount account = factory.getAccount(type, accountHolder, balance, disabled);
        accountRepository.createAccount(account);
    }

    @Override
    public IAccount getAccount(int accountNo) {
        return accountRepository.getAccount(accountNo);
    }

    @Override
    public List<IAccount> getAccounts(Customer customer) {
        return accountRepository.getAccounts(customer);
    }
}
