import java.util.List;

public interface ITransactionRepository {
    void createTransaction(Transaction transaction);
    Transaction getTransaction(int id);

    List<Transaction> getTransactions(IAccount account);
}

