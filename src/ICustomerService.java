public interface ICustomerService {
    void createCustomer(String name, String phone, String email);
    Customer getCustomer(int id);
}

