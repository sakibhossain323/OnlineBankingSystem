public class CurrentAccount extends Account implements IAccount {
    public CurrentAccount(Customer accountHolder, double balance, boolean disabled) {
        super(accountHolder, balance, disabled);
    }

    public CurrentAccount(int accountNo, Customer accountHolder, double balance, boolean disabled) {
        super(accountNo, accountHolder, balance, disabled);
    }
}

