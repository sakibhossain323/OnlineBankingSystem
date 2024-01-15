import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class AccountRepository implements IAccountRepository {
    List<IAccount> accounts;

    public AccountRepository() {
        this.accounts = new ArrayList<>();
    }

    public AccountRepository(List<IAccount> accounts) {
        this.accounts = accounts;
    }

    public void createAccount(IAccount account)
    {
        OptionalInt accountNo = accounts.stream().mapToInt(IAccount::getAccountNo).max();
        account.setAccountNo(accountNo.orElse(0)+1);
        this.accounts.add(account);
    }

    public IAccount getAccount(int accountNo)
    {
        Optional<IAccount> result = accounts.stream()
            .filter((a)-> a.getAccountNo() == accountNo).findFirst();
        return  result.orElse(null);
    }
}
