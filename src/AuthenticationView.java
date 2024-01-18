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
        String phone = Reader.readString("phone");
        String email = Reader.readString("email");
        Customer customer = customerService.createCustomer(name, phone, email);
        String password = Reader.readString("password");
        authenticationService.createRecord(customer, password);
        System.out.println("Registered Successfully...");
    }

    @Override
    public Customer login() {
        String phone = Reader.readString("phone");
        Customer customer = customerService.getCustomerByPhone(phone);
        String password = Reader.readString("password");
        if(authenticationService.isValid(customer, password)) {
            return customer;
        }
        throw new RuntimeException("Invalid credentials");
    }
}
