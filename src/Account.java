public abstract class Account {
    public int accountNo;
    public Customer accountHolder;
    public double balance;
    public boolean disabled;

    public Account(Customer accountHolder, double balance, boolean disabled) {
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.disabled = disabled;
    }

    public Account(int accountNo, Customer accountHolder, double balance, boolean disabled) {
        this(accountHolder, balance, disabled);
        this.accountNo = accountNo;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public Customer getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(Customer accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
