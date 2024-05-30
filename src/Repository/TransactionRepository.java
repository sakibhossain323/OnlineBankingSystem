package Repository;

import Model.Account;
import Model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class TransactionRepository implements ITransactionRepository {
    DbContext db;
    List<Transaction> transactions;

    public TransactionRepository(DbContext db) {
        this.db = db;
        this.transactions = new ArrayList<Transaction>();
    }

    @Override
    public void createTransaction(Transaction transaction)
    {
        String sql = "INSERT INTO transaction VALUES (?,?,?,?,?)";

        try (var conn = db.getConnection();
             var ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, 0);
            ps.setDouble(2, transaction.getAmount());
            ps.setDate(3, new java.sql.Date(transaction.getDate().getTime()));
            ps.setInt(4, transaction.getFrom().getAccountNo());
            ps.setInt(5, transaction.getTo().getAccountNo());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        OptionalInt id = transactions.stream().mapToInt(Transaction::getId).max();
//        transaction.setId(id.orElse(0)+1);
//        transactions.add(transaction);
    }

    @Override
    public Transaction getTransaction(int id) {
        Optional<Transaction> result = transactions.stream()
                .filter((t)-> t.getId() == id).findFirst();
        return  result.orElse(null);
    }

    @Override
    public List<Transaction> getTransactions(Account account) {
        return transactions.stream()
                .filter((t)->t.getFrom() == account || t.getTo() == account)
                .collect(Collectors.toList());
    }

    @Override
    public void takeLoan(Account account, double amount, int duration)
    {
        String sql = "INSERT INTO loan VALUES (?,?,?,?,?,?)";

        try (var conn = db.getConnection();
             var ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, 0);
            ps.setDouble(2, amount);
            ps.setInt(3, duration);
            ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
            ps.setInt(5, account.getAccountNo());
            ps.setInt(6, account.getBranchId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
