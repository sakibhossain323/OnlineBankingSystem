public interface IAccount {

    int getAccountNo();
    void setAccountNo(int accountNo);

    ICustomer getAccountHolder();
    double getBalance();
    void setBalance(double balance);
}

