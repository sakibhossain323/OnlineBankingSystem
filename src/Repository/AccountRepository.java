package Repository;

import Model.Account;
import Model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class AccountRepository implements IAccountRepository {
    DbContext db;
    List<Account> accounts;

    public AccountRepository(DbContext db) {
        this.db = db;
        this.accounts = new ArrayList<>();
    }

    public AccountRepository(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public void createAccount(Account account)
    {
        OptionalInt accountNo = accounts.stream().mapToInt(Account::getAccountNo).max();
        account.setAccountNo(accountNo.orElse(0)+1);
        this.accounts.add(account);
    }

    @Override
    public Account getAccount(int accountNo)
    {
        Optional<Account> result = accounts.stream()
            .filter((a)-> a.getAccountNo() == accountNo).findFirst();
        return  result.orElse(null);
    }
    @Override
    public List<Account> getAccounts(Customer customer) {
        return accounts.stream()
                .filter((a)->a.getAccountHolder() == customer)
                .collect(Collectors.toList());
    }
}
