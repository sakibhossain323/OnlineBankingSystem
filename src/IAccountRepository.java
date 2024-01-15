public interface IAccountRepository {
    void createAccount(IAccount account);

    IAccount getAccount(int accountNo);
}

