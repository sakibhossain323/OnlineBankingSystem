import java.util.Date;
import java.util.List;

public class TransactionService implements ITransactionService {
    ITransactionRepository transactionRepository;

    public TransactionService(ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void debit(IAccount account, double amount) throws RuntimeException {
        double balance = account.getBalance();
        balance-= amount;
        if(balance < 0) throw new RuntimeException("Insufficient Balance");
        account.setBalance(balance);
    }
    @Override
    public void credit(IAccount account, double amount) throws RuntimeException {
        double balance = account.getBalance();
        balance+= amount;
        account.setBalance(balance);
    }

    @Override
    public void transfer(IAccount from, IAccount to, double amount) throws RuntimeException {
        double balance = from.getBalance();
        try {
            debit(from, amount);
        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }
        try
        {
            credit(to, amount);
        }
        catch (Exception e)
        {
            from.setBalance(balance);
            throw new RuntimeException();
        }
        Transaction trx = new Transaction(from, to, amount, new Date());
        transactionRepository.createTransaction(trx);
    }

    @Override
    public Transaction getTransaction(int id) {
        return transactionRepository.getTransaction(id);
    }

    @Override
    public List<Transaction> getTransactions(IAccount account) {
        return transactionRepository.getTransactions(account);
    }
}
