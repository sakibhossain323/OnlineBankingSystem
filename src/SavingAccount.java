public class SavingAccount extends Account implements IAccount{
    public SavingAccount(Customer accountHolder, double balance, boolean disabled) {
        super(accountHolder, balance, disabled);
    }

    public SavingAccount(int accountNo, Customer accountHolder, double balance, boolean disabled) {
        super(accountNo, accountHolder, balance, disabled);
    }
}
