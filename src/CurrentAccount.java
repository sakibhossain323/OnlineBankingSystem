public class CurrentAccount extends Account implements IAccount {
    public CurrentAccount(ICustomer accountHolder, double balance, boolean disabled) {
        super(accountHolder, balance, disabled);
    }

    public CurrentAccount(int accountNo, ICustomer accountHolder, double balance, boolean disabled) {
        super(accountNo, accountHolder, balance, disabled);
    }
}

