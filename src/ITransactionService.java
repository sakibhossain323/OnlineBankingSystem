import java.util.List;

public interface ITransactionService {
    void debit(IAccount account, double amount) throws RuntimeException;
    void credit(IAccount account, double amount) throws RuntimeException;
    void transfer(IAccount from, IAccount to, double amount) throws RuntimeException;

    Transaction getTransaction(int id);
    List<Transaction> getTransactions(IAccount account);
}
