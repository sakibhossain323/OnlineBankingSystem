import java.util.List;

public interface IAccountService {
    void createAccount(AccountType type,ICustomer accountHolder, double balance, boolean disabled);
    IAccount getAccount(int accountNo);
    List<IAccount> getAccounts(ICustomer customer);
}

