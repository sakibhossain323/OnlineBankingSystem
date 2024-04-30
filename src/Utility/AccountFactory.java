package Utility;

import Model.CurrentAccount;
import Model.Customer;
import Model.IAccount;
import Model.SavingAccount;

public class AccountFactory {
    public IAccount getAccount(AccountType type, Customer customer, double balance, boolean disabled)
    {
        if(type == AccountType.CurrentAccount) return new CurrentAccount(customer,balance, disabled);
        if(type == AccountType.SavingAccount) return  new SavingAccount(customer, balance, disabled);
        return null;
    }
}
