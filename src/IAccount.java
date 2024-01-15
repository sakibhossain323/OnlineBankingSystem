public interface IAccount {
    void debit(double amount) throws Exception;
    void credit(double amount) throws Exception;

    int getAccountNo();
    void setAccountNo(int accountNo);
    double getBalance();
}

