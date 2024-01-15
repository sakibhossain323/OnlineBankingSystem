public interface ITransactionService {
    void transfer(IAccount from, IAccount to, double amount);
}
