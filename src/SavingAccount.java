public class SavingAccount extends Account implements IAccount{
    public SavingAccount(ICustomer accountHolder, double balance, boolean disabled) {
        super(accountHolder, balance, disabled);
    }

    public SavingAccount(int accountNo, ICustomer accountHolder, double balance, boolean disabled) {
        super(accountNo, accountHolder, balance, disabled);
    }
}
