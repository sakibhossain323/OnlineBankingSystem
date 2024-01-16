public interface IAccount {

    int getAccountNo();
    void setAccountNo(int accountNo);

    Customer getAccountHolder();
    double getBalance();
    void setBalance(double balance);
}

