package Repository;

import Model.Account;
import Model.Transaction;

import java.util.List;

public interface ITransactionRepository {
    void createTransaction(Transaction transaction);
    Transaction getTransaction(int id);

    List<Transaction> getTransactions(Account account);
}

