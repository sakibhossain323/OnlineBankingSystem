import java.util.Date;

public class Transaction {
    private int id;
    private IAccount from;
    private IAccount to;
    private double amount;
    private Date date;

    public Transaction(IAccount from, IAccount to, double amount, Date date) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(int id, IAccount from, IAccount to, double amount, Date date) {
        this(from, to, amount, date);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IAccount getFrom() {
        return from;
    }

    public void setFrom(IAccount from) {
        this.from = from;
    }

    public IAccount getTo() {
        return to;
    }

    public void setTo(IAccount to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
