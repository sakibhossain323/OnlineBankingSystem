import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class TransactionRepository implements ITransactionRepository {
    List<Transaction> transactions;

    public TransactionRepository() {
        this.transactions = new ArrayList<Transaction>();
    }

    @Override
    public void createTransaction(Transaction transaction) {
        OptionalInt id = transactions.stream().mapToInt(Transaction::getId).max();
        transaction.setId(id.orElse(0)+1);
        transactions.add(transaction);
    }

    @Override
    public Transaction getTransaction(int id) {
        Optional<Transaction> result = transactions.stream()
                .filter((t)-> t.getId() == id).findFirst();
        return  result.orElse(null);
    }

    @Override
    public List<Transaction> getTransactions(IAccount account) {
        return transactions.stream()
                .filter((t)->t.getFrom() == account || t.getTo() == account)
                .collect(Collectors.toList());
    }
}
