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
        String sql = "INSERT INTO account VALUES (?,?,?,?,?)";

        try (var conn = db.getConnection();
             var ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, 0);
            ps.setString(2, account.getAccountType());
            ps.setDouble(3, account.getBalance());
            ps.setInt(4, account.getBranchId());
            ps.setInt(5, account.getAccountHolder().getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        OptionalInt accountNo = accounts.stream().mapToInt(Account::getAccountNo).max();
//        account.setAccountNo(accountNo.orElse(0)+1);
//        this.accounts.add(account);
    }

    @Override
    public Account getAccount(int accountNo)
    {
        Optional<Account> result = accounts.stream()
            .filter((a)-> a.getAccountNo() == accountNo).findFirst();
        return  result.orElse(null);
    }
    @Override
    public List<Account> getAccounts(Customer customer)
    {
        List<Account> result;
        String sql = "SELECT * FROM account WHERE customer_id = ?";
        try (var conn = db.getConnection();
             var ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, customer.getId());
            var rs = ps.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                Account account = new Account(
                    rs.getInt("account_id"),
                    rs.getString("account_type"),
                    rs.getDouble("account_balance"),
                    rs.getInt("branch_id"),
                    customer
                );
                result.add(account);
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
