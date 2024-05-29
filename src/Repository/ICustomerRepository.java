package Repository;

import Model.Customer;

public interface ICustomerRepository {
    void createCustomer(Customer customer);

    Customer getCustomerById(int id);
    Customer getCustomerByPhone(String phone);
    Customer getCustomerByEmail(String email);

    int getNewId();

}

