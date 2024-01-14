public abstract class Account {
    public int accountNo;
    public ICustomer accountHolder;
    public double balance;
    public boolean disabled;

    public Account(int accountNo, ICustomer accountHolder, double balance, boolean disabled) {
        this.accountNo = accountNo;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.disabled = disabled;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public ICustomer getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(ICustomer accountHolder) {
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
