package Service;

import Model.Account;
import Model.Transaction;
import Repository.ITransactionRepository;

import java.util.Date;
import java.util.List;

public class TransactionService implements ITransactionService {
    ITransactionRepository transactionRepository;

    public TransactionService(ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void debit(Account account, double amount) throws RuntimeException {
        double balance = account.getBalance();
        balance-= amount;
        if(balance < 0) throw new RuntimeException("Insufficient Balance");
        account.setBalance(balance);
    }
    @Override
    public void credit(Account account, double amount) throws RuntimeException {
        double balance = account.getBalance();
        balance+= amount;
        account.setBalance(balance);
    }

    @Override
    public void transfer(Account from, Account to, double amount) throws RuntimeException {
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
    public List<Transaction> getTransactions(Account account) {
        return transactionRepository.getTransactions(account);
    }

    @Override
    public void takeLoan(Account account, double amount, int duration)
    {
        try
        {
            credit(account, amount);
        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }

        transactionRepository.takeLoan(account, amount, duration);
    }

    @Override
    public List<String> getLoans(Account account)
    {
        return transactionRepository.getLoans(account);
    }

    @Override
    public void payLoan(int installment_id)
    {
        transactionRepository.payLoan(installment_id);
    }
}
