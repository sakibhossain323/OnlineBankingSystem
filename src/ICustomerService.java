public interface ICustomerService {
    void addCustomer(String name, String phone, String email);
    Customer getCustomer(int id);
}

