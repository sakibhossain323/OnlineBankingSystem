public interface ICustomerService {
    Customer createCustomer(String name, String phone, String email);
    Customer getCustomerById(int id);
    Customer getCustomerByPhone(String phone);
    Customer getCustomerByEmail(String email);



}

