public class AccountService implements IAccountService {
    IAccountRepository accountRepository = null;

    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void createAccount(ICustomer accountHolder, double balance, boolean disabled) {

    }
}
