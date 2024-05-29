package Service;

import Model.Customer;

public interface ICustomerService {
    Customer createCustomer(String name, String phone, String email, String address);
    Customer getCustomerById(int id);
    Customer getCustomerByPhone(String phone);
    Customer getCustomerByEmail(String email);

    int getNewId();
}

