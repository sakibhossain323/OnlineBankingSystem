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
        String sql = "SELECT * FROM transaction WHERE from_account_id = ? OR to_account_id = ? ORDER BY transaction_date DESC";
        try(var conn = db.getConnection();
            var ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, account.getAccountNo());
            ps.setInt(2, account.getAccountNo());
            var result = ps.executeQuery();
            List<Transaction> transactions = new ArrayList<>();
            while (result.next())
            {
                int id = result.getInt("transaction_id");
                int from = result.getInt("from_account_id");
                Account  ac_from = new Account(from, null, 0, 0, null);
                int to = result.getInt("to_account_id");
                Account  ac_to = new Account(to, null, 0, 0, null);
                double amount = result.getDouble("transaction_amount");
                java.util.Date date = result.getDate("transaction_date");
                transactions.add(new Transaction(id, ac_from, ac_to, amount, date));
            }
            return transactions;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
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

    @Override
    public List<String> getLoans(Account account)
    {
        String sql = "SELECT * FROM loan WHERE account_id = ?";
        String sql2= "Select * from installment where loan_id = ? and status='Unpaid' order by installment_date";
        try(var conn = db.getConnection();
            var ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, account.getAccountNo());
            var result = ps.executeQuery();
            List<String> loans = new ArrayList<>();
            while (result.next())
            {
                try(var ps2 = conn.prepareStatement(sql2))
                {
                    ps2.setInt(1, result.getInt("loan_id"));
                    var result2 = ps2.executeQuery();
                    int loan_id = result.getInt("loan_id");
                    double amount = result.getDouble("loan_amount");
                    int duration = result.getInt("duration");
                    java.util.Date date = result.getDate("loan_date");
                    loans.add( "Loan_ID:" + loan_id + " Amount: " + amount + " Duration: " + duration + " Date: " + date);
                    while(result2.next())
                    {
                        loans.add("Installment_Id: "+ result2.getInt("installment_id") + " Amount: " + result2.getDouble("installment_amount") + " Date: " + result2.getDate("installment_date"));
                    }
                }
//                double amount = result.getDouble("loan_amount");
//                int duration = result.getInt("loan_duration");
//                java.util.Date date = result.getDate("loan_date");
//                loans.add("Amount: " + amount + " Duration: " + duration + " Date: " + date);
            }
            return loans;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void payLoan(int installment_id)
    {
        String sql = "UPDATE installment SET status = 'Paid' WHERE installment_id = ?";
        try (var conn = db.getConnection();
             var ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, installment_id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
