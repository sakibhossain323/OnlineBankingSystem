public class TransactionService implements ITransactionService {

    @Override
    public void transfer(IAccount from, IAccount to, double amount) {
        try {
            from.debit(amount);
            to.credit(amount);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
