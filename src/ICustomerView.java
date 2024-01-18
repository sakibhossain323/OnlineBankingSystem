import java.util.List;

public interface ICustomerView {
    void startSession();
    void addAccount();
    List<IAccount> getAccounts();
    void transfer();
}
