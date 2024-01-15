public interface IAccountService {
    void createAccount(ICustomer accountHolder, double balance, boolean disabled);
    IAccount getAccount(int accountNo);
}

