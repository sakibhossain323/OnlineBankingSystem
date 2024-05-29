package View;

import Model.Customer;
import Service.IAuthenticationService;
import Service.ICustomerService;

public class AuthenticationView implements IAuthenticationView {

    ICustomerService customerService;
    IAuthenticationService authenticationService;

    public AuthenticationView(ICustomerService customerService, IAuthenticationService authenticationService) {
        this.customerService = customerService;
        this.authenticationService = authenticationService;
    }

    @Override
    public void register() {
        String name = Reader.readString("name");
        String address = Reader.readString("address");
        String email = Reader.readString("email");
        String phone = Reader.readString("phone");

        Customer customer = customerService.createCustomer(name, phone, email, address);

        String password = Reader.readString("password");

        int newId = customerService.getNewId();

        customer.setId(newId);

        authenticationService.createRecord(customer, password);

        System.out.println("Registered Successfully. Your ID is: " + newId + "\n");
    }

    @Override
    public Customer login()
    {
        int ID=Reader.readInt("Customer_ID");
        String password = Reader.readString("password");

        //String phone = Reader.readString("phone");
        //Customer customer = customerService.getCustomerByPhone(phone);

        Customer customer = customerService.getCustomerById(ID);

        if(authenticationService.isValid(customer, password))
        {
            return customer;
        }
        throw new RuntimeException("Invalid credentials");
    }
}
