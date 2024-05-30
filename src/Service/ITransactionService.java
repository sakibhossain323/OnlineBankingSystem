package Service;

import Model.Account;
import Model.Transaction;

import java.util.List;

public interface ITransactionService {
    void debit(Account account, double amount) throws RuntimeException;
    void credit(Account account, double amount) throws RuntimeException;
    void transfer(Account from, Account to, double amount) throws RuntimeException;

    void takeLoan(Account account, double amount, int duration) throws RuntimeException;

    Transaction getTransaction(int id);
    List<Transaction> getTransactions(Account account);
}
