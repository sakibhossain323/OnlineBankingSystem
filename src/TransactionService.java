public class TransactionService implements ITransactionService {
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
    }
}
