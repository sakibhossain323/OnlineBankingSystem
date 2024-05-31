package Repository;

import Model.Account;
import Model.Transaction;

import java.util.List;

public interface ITransactionRepository {
    void createTransaction(Transaction transaction);
    Transaction getTransaction(int id);

    List<Transaction> getTransactions(Account account);

    void takeLoan(Account account, double amount, int duration);

    List<String> getLoans(Account account);

    void payLoan(int installment_id);
}

