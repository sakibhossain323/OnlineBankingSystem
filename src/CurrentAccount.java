public class CurrentAccount extends Account implements IAccount {
    public CurrentAccount(int accountNo, ICustomer accountHolder, double balance, boolean disabled) {
        super(accountNo, accountHolder, balance, disabled);
    }

    @Override
    public void debit(double amount) throws Exception{
        if(balance - amount < 0) throw new Exception("Insufficient Balance");
        balance-= amount;
    }

    @Override
    public void credit(double amount) {
        balance+= amount;
    }
}

