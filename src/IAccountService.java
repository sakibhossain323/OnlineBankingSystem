import java.util.List;

public interface IAccountService {
    void createAccount(AccountType type, Customer accountHolder, double balance, boolean disabled);
    IAccount getAccount(int accountNo);
    List<IAccount> getAccounts(Customer customer);
}

