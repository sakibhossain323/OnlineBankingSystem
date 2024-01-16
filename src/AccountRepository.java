import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class AccountRepository implements IAccountRepository {
    List<IAccount> accounts;

    public AccountRepository() {
        this.accounts = new ArrayList<>();
    }

    public AccountRepository(List<IAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public void createAccount(IAccount account)
    {
        OptionalInt accountNo = accounts.stream().mapToInt(IAccount::getAccountNo).max();
        account.setAccountNo(accountNo.orElse(0)+1);
        this.accounts.add(account);
    }

    @Override
    public IAccount getAccount(int accountNo)
    {
        Optional<IAccount> result = accounts.stream()
            .filter((a)-> a.getAccountNo() == accountNo).findFirst();
        return  result.orElse(null);
    }
    @Override
    public List<IAccount> getAccounts(Customer customer) {
        return accounts.stream()
                .filter((a)->a.getAccountHolder() == customer)
                .collect(Collectors.toList());
    }
}
