public class AccountService implements IAccountService {
    IAccountRepository accountRepository = null;

    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void createAccount(ICustomer accountHolder, double balance, boolean disabled) {
        IAccount account = new SavingAccount(accountHolder, balance, disabled);
        accountRepository.createAccount(account);
    }

    @Override
    public IAccount getAccount(int accountNo) {
        return accountRepository.getAccount(accountNo);
    }
}
