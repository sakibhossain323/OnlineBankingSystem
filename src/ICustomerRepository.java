public interface ICustomerRepository {
    Customer getCustomer(int id);
    void addCustomer(Customer customer);
}

