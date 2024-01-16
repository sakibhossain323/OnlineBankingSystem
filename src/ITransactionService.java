public interface ITransactionService {
    void debit(IAccount account, double amount) throws RuntimeException;
    void credit(IAccount account, double amount) throws RuntimeException;
    void transfer(IAccount from, IAccount to, double amount) throws RuntimeException;
}
