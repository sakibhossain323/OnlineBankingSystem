package Model;

import Model.Customer;

public class Account {
    public int accountNo;

    public String accountType;
    public double balance;

    public int branchId;
    public Customer accountHolder;


    public Account(Customer accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public Account(int accountNo, String accountType, double balance, int branchId,  Customer accountHolder) {
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.balance = balance;
        this.branchId = branchId;
        this.accountHolder = accountHolder;
    }

    public Account(String accountType, double balance, int branchId,  Customer accountHolder) {
        this.accountType = accountType;
        this.balance = balance;
        this.branchId = branchId;
        this.accountHolder = accountHolder;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public Customer getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(Customer accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
